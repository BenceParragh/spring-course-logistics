package hu.cubix.logistics.bencepar.dto;

import java.time.LocalDateTime;

import hu.cubix.logistics.bencepar.model.Address;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MilestoneDto {

	@NotNull
	private Long milestoneId;
	private Address address;
	private LocalDateTime plannedTime;
	@NotNull
	@Min(1)
	private int expectedDelay;

	public MilestoneDto() {
	}

	public MilestoneDto(Long milestoneId, Address address, LocalDateTime plannedTime, int expectedDelay) {
		this.milestoneId = milestoneId;
		this.address = address;
		this.plannedTime = plannedTime;
		this.expectedDelay = expectedDelay;
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
