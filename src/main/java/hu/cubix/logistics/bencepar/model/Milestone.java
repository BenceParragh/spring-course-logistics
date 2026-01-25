package hu.cubix.logistics.bencepar.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Milestone {

	@Id
	@GeneratedValue
	private Long milestoneId;
	@OneToOne
	private Address address;
	private LocalDateTime plannedTime;
	private int expectedDelay;

	public Milestone() {
		super();
	}

	public Milestone(Long milestoneId, Address address, LocalDateTime plannedTime) {
		super();
		this.milestoneId = milestoneId;
		this.address = address;
		this.plannedTime = plannedTime;
	}

	public Long getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(Long milestoneId) {
		this.milestoneId = milestoneId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public LocalDateTime getPlannedTime() {
		return plannedTime;
	}

	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}

	public int getExpectedDelay() {
		return expectedDelay;
	}

	public void setExpectedDelay(int expectedDelay) {
		this.expectedDelay = expectedDelay;
	}

}
