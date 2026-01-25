package hu.cubix.logistics.bencepar.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.cubix.logistics.bencepar.dto.TransportPlanDto;
import hu.cubix.logistics.bencepar.model.TransportPlan;

@Mapper(componentModel = "spring", uses = {SectionMapper.class})
public interface TransportPlanMapper {

	List<TransportPlanDto> plansToDtos(List<TransportPlan> transportPlan);

	@Mapping(source = "planId", target = "planId")
	@Mapping(source = "expectedIncome", target = "expectedIncome")
	@Mapping(source = "sections", target = "sections")
	TransportPlanDto planToDto(TransportPlan transportPlan);

	@InheritInverseConfiguration
	TransportPlan dtoToPlan(TransportPlanDto transportPlanDto);

	List<TransportPlan> dtosToPlans(List<TransportPlanDto> transportPlanDto);



}
