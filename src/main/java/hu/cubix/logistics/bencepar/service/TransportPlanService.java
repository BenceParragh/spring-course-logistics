package hu.cubix.logistics.bencepar.service;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.cubix.logistics.bencepar.config.LogisticsConfigurationProperties;
import hu.cubix.logistics.bencepar.config.LogisticsConfigurationProperties.Delay.Penalty;
import hu.cubix.logistics.bencepar.model.Milestone;
import hu.cubix.logistics.bencepar.model.Section;
import hu.cubix.logistics.bencepar.model.TransportPlan;
import hu.cubix.logistics.bencepar.repository.MilestoneRepository;
import hu.cubix.logistics.bencepar.repository.TransportPlanRepository;
import jakarta.transaction.Transactional;

@Service
public class TransportPlanService {

	@Autowired
	LogisticsConfigurationProperties config;

	@Autowired
	private TransportPlanRepository transportPlanRepository;

	@Autowired
	private MilestoneRepository milestoneRepository;
	
	@Transactional
	public TransportPlan create(TransportPlan transportPlan) {
		transportPlan.setPlanId(null);
		return transportPlanRepository.save(transportPlan);

	}

	public void registerExpectedDelay(Long planId, Long milestoneId, int expectedDelay) {

		// 1. Validate transport plan exists
		TransportPlan plan = transportPlanRepository.findById(planId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		// 2. Validate milestone exists and belongs to plan
		Milestone milestone = milestoneRepository.findById(milestoneId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		if (!isMilestoneInPlan(plan, milestone)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		milestone.setPlannedTime(milestone.getPlannedTime().plusMinutes(expectedDelay));

		addDelayToNextMilestone(plan, milestone, expectedDelay);

		double penaltyPercent = calculatePenaltyPercent(expectedDelay);
		plan.setExpectedIncome(plan.getExpectedIncome() * (1 - penaltyPercent / 100.0));

		transportPlanRepository.save(plan);
	}

	private boolean isMilestoneInPlan(TransportPlan plan, Milestone milestone) {
		return plan.getSections().stream()
				.flatMap(section -> Stream.of(section.getStartMilestone(), section.getEndMilestone()))
				.anyMatch(m -> m.getMilestoneId().equals(milestone.getMilestoneId()));
	}

	private void addDelayToNextMilestone(TransportPlan plan, Milestone milestone, int expectedDelay) {
		List<Section> sections = plan.getSections();

		for (int i = 0; i < sections.size(); i++) {
			Section currentSection = sections.get(i);

			if (currentSection.getStartMilestone().equals(milestone)) {
				currentSection.getEndMilestone()
						.setPlannedTime(currentSection.getEndMilestone().getPlannedTime().plusMinutes(expectedDelay));
			}

			if (currentSection.getEndMilestone().equals(milestone)) {
				if (i + 1 < sections.size()) {
					Section nextSection = sections.get(i + 1);
					nextSection.getStartMilestone().setPlannedTime(
							nextSection.getStartMilestone().getPlannedTime().plusMinutes(expectedDelay));
				}
			}
		}
	}

	private int calculatePenaltyPercent(int expectedDelay) {

		Penalty appliedPenalty = config.getDelay().getPenalty();

		if (expectedDelay <= 30)
			return appliedPenalty.getPenalty30min();
		if (expectedDelay <= 60)
			return appliedPenalty.getPenalty60min();
		if (expectedDelay <= 120)
			return appliedPenalty.getPenalty120min();
		return appliedPenalty.getPenalty120min();
	}

}
