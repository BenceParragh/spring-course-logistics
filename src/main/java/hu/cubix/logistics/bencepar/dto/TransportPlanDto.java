package hu.cubix.logistics.bencepar.dto;

import java.util.List;

import hu.cubix.logistics.bencepar.model.Section;

public class TransportPlanDto {

	private Long planId;
	private Double expectedIncome;

	private List<Section> sections;

	public TransportPlanDto() {
	}

	public TransportPlanDto(Long planId, Double expectedIncome, List<Section> sections) {
		this.planId = planId;
		this.expectedIncome = expectedIncome;
		this.sections = sections;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public Double getExpectedIncome() {
		return expectedIncome;
	}

	public void setExpectedIncome(Double expectedIncome) {
		this.expectedIncome = expectedIncome;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

}
