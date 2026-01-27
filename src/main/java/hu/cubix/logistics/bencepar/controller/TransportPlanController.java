package hu.cubix.logistics.bencepar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.cubix.logistics.bencepar.dto.MilestoneDto;
import hu.cubix.logistics.bencepar.dto.TransportPlanDto;
import hu.cubix.logistics.bencepar.mapper.MilestoneMapper;
import hu.cubix.logistics.bencepar.mapper.TransportPlanMapper;
import hu.cubix.logistics.bencepar.model.TransportPlan;
import hu.cubix.logistics.bencepar.service.TransportPlanService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transportPlans")
public class TransportPlanController {

	@Autowired
	private TransportPlanService transportPlanService;
	
	@Autowired
	MilestoneMapper milestoneMapper;
	
	@Autowired
	TransportPlanMapper transportPlanMapper;
	
	@PostMapping
	public TransportPlanDto create(@RequestBody @Valid TransportPlanDto transportPlanDto) {

		if (transportPlanDto.getPlanId() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		TransportPlan plan = transportPlanMapper.dtoToPlan(transportPlanDto);
		TransportPlan savedPlan = transportPlanService.create(plan);

		if (savedPlan == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		return transportPlanMapper.planToDto(savedPlan);
		
	}

	@PreAuthorize("hasAuthority('TransportManager')")
	@PostMapping("/{planId}/delay")
	@Transactional
	public ResponseEntity<Void> registerExpectedDelay(@PathVariable Long planId,
			@RequestBody @Valid MilestoneDto milestoneDto) {

		transportPlanService.registerExpectedDelay(planId, milestoneDto.getMilestoneId(),
				milestoneDto.getExpectedDelay());

		return ResponseEntity.ok().build();
	}

}
