package hu.cubix.logistics.bencepar.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.cubix.logistics.bencepar.dto.SectionDto;
import hu.cubix.logistics.bencepar.model.Section;

@Mapper(componentModel = "spring")
public interface SectionMapper {

	List<SectionDto> sectionsToDtos(List<Section> section);
	
	@Mapping(source = "sectionId", target = "sectionId")
	@Mapping(source = "startMilestone", target = "startMilestone")
	@Mapping(source = "endMilestone", target = "endMilestone")
	@Mapping(source = "sectionOrder", target = "sectionOrder")
	SectionDto sectionToDto(Section section);
	
	@InheritInverseConfiguration
	Section dtoToSection(SectionDto sectionDto);
	
	List<Section> dtosToSections(List<SectionDto> sectionDto);
}
