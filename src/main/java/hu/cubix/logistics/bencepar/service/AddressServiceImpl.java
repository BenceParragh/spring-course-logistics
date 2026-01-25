package hu.cubix.logistics.bencepar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static hu.cubix.logistics.bencepar.service.AddressSpecifications.*;

import hu.cubix.logistics.bencepar.dto.AddressDto;
import hu.cubix.logistics.bencepar.model.Address;
import hu.cubix.logistics.bencepar.repository.AddressRepository;
import jakarta.transaction.Transactional;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Transactional
	public Address create(Address address) {
		address.setAddressId(null);
		return addressRepository.save(address);

	}

	@Override
	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	@Override
	public Optional<Address> findById(long id) {
		return addressRepository.findById(id);
	}

	@Override
	public Address update(Address address) {
		if (findById(address.getAddressId()) == null) {
			return null;
		}
		return addressRepository.save(address);
	}

	@Override
	@Transactional
	public void delete(long id) {
		addressRepository.deleteById(id);
	}

	@Override
	public Page<Address> findAddressesByExample(AddressDto example, Pageable pageable) {
		Long id = example.getAddressId();
		String countryCode = example.getCountryCode();
		String city = example.getCity();
		String street = example.getStreet();
		Long zip = example.getZip();
		Integer houseNumber = example.getHouseNumber();
		Double latitude = example.getLatitude();
		Double longitude = example.getLongitude();

		Specification<Address> spec = Specification.where(null);

		if (id != null && id > 0)
			spec = spec.and(hasAddressId(id));

		if (StringUtils.hasText(countryCode))
			spec = spec.and(hasCountryCode(countryCode));

		if (StringUtils.hasText(city))
			spec = spec.and(hasCity(city));

		if (StringUtils.hasText(street))
			spec = spec.and(hasStreet(street));

		if (zip != null && zip > 0)
			spec = spec.and(hasZip(zip));

		if (houseNumber != null && houseNumber > 0)
			spec = spec.and(hasHouseNumber(houseNumber));

		if (latitude != null && latitude > 0)
			spec = spec.and(hasLatitude(latitude));

		if (longitude != null && longitude > 0)
			spec = spec.and(hasLongitude(longitude));

		return addressRepository.findAll(spec, pageable);
	}

	public long getTotalMatchingCount(AddressDto example) {

		Specification<Address> spec = Specification.where(null);

		return addressRepository.count(spec);
	}
}
