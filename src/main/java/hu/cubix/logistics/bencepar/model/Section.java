package hu.cubix.logistics.bencepar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Section {

	@Id
	@GeneratedValue
	private long sectionId;
	@OneToOne
	private Milestone startMilestone;
	@OneToOne
	private Milestone endMilestone;
	private int sectionOrder;
	@ManyToOne
	private TransportPlan transportPlan;

	public Section() {
	}

	public Section(Milestone startMilestone, Milestone endMilestone, int sectionOrder) {
		super();
		this.startMilestone = startMilestone;
		this.endMilestone = endMilestone;
		this.sectionOrder = sectionOrder;
	}

	public long getSectionId() {
		return sectionId;
	}

	public void setSectionId(long sectionId) {
		this.sectionId = sectionId;
	}

	public Milestone getStartMilestone() {
		return startMilestone;
	}

	public void setStartMilestone(Milestone startMilestone) {
		this.startMilestone = startMilestone;
	}

	public Milestone getEndMilestone() {
		return endMilestone;
	}

	public void setEndMilestone(Milestone endMilestone) {
		this.endMilestone = endMilestone;
	}

	public int getSectionOrder() {
		return sectionOrder;
	}

	public void setSectionOrder(int sectionOrder) {
		this.sectionOrder = sectionOrder;
	}

	public TransportPlan getTransportPlan() {
		return transportPlan;
	}

	public void setTransportPlan(TransportPlan transportPlan) {
		this.transportPlan = transportPlan;
	}

}
