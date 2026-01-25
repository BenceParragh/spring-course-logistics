package hu.cubix.logistics.bencepar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import hu.cubix.logistics.bencepar.dto.AddressDto;
import hu.cubix.logistics.bencepar.model.Address;

public interface AddressService {

//	public Address save(Address address);
	
	public Address create(Address address);

	public Address update(Address address);

	public List<Address> findAll();

	public Optional<Address> findById(long addressId);

	public void delete(long addressId);
	
	public Page<Address> findAddressesByExample(AddressDto addressDto, Pageable pageable);
	
	public long getTotalMatchingCount(AddressDto example);
}
