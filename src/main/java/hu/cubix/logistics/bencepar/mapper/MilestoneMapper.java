package hu.cubix.logistics.bencepar.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.cubix.logistics.bencepar.dto.MilestoneDto;
import hu.cubix.logistics.bencepar.model.Milestone;

@Mapper(componentModel = "spring")
public interface MilestoneMapper {

	List<MilestoneDto> milestonesToDtos(List<Milestone> milestones);

	@Mapping(source = "milestoneId", target = "milestoneId")
	@Mapping(source = "address", target = "address")
	@Mapping(source = "plannedTime", target = "plannedTime")
	@Mapping(source = "expectedDelay", target = "expectedDelay")
	MilestoneDto milestoneToDto(Milestone milestone);

	@InheritInverseConfiguration
	Milestone dtoToMilestone(MilestoneDto milestoneDto);

	List<Milestone> dtosToMilestones(List<MilestoneDto> milestoneDto);

}
