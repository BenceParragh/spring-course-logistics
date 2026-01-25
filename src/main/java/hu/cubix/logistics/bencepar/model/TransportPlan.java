package hu.cubix.logistics.bencepar.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TransportPlan {

	@Id
	@GeneratedValue
	private Long planId;
	private double expectedIncome;
	@OneToMany(mappedBy = "transportPlan")
	private List<Section> sections;

	public TransportPlan() {
	}

	public TransportPlan(Long expectedIncome, List<Section> sections) {
		super();
		this.expectedIncome = expectedIncome;
		this.sections = sections;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public double getExpectedIncome() {
		return expectedIncome;
	}

	public void setExpectedIncome(double expectedIncome) {
		this.expectedIncome = expectedIncome;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

}
