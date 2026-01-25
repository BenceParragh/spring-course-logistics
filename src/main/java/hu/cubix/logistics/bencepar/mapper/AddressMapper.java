package hu.cubix.logistics.bencepar.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.cubix.logistics.bencepar.dto.AddressDto;
import hu.cubix.logistics.bencepar.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

	List<AddressDto> addressesToDtos(List<Address> addresses);

	@Mapping(source = "addressId", target = "addressId")
	@Mapping(source = "countryCode", target = "countryCode")
	@Mapping(source = "city", target = "city")
	@Mapping(source = "street", target = "street")
	@Mapping(source = "zip", target = "zip")
	@Mapping(source = "houseNumber", target = "houseNumber")
	@Mapping(source = "latitude", target = "latitude")
	@Mapping(source = "longitude", target = "longitude")
	AddressDto addressToDto(Address address);

	@InheritInverseConfiguration
	Address dtoToAddress(AddressDto addressDto);

	List<Address> dtosToAddresses(List<AddressDto> addresses);

}
