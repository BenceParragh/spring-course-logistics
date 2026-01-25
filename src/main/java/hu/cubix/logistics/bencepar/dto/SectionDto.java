package hu.cubix.logistics.bencepar.dto;

import hu.cubix.logistics.bencepar.model.Milestone;

public class SectionDto {

	private long sectionId;
	private Milestone startMilestone;
	private Milestone endMilestone;
	private int sectionOrder;

	public SectionDto() {
	}

	public SectionDto(long sectionId, Milestone startMilestone, Milestone endMilestone, int sectionOrder) {
		super();
		this.sectionId = sectionId;
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

}
