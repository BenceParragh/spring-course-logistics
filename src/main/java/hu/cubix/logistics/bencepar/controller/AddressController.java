package hu.cubix.logistics.bencepar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.cubix.logistics.bencepar.dto.AddressDto;
import hu.cubix.logistics.bencepar.mapper.AddressMapper;
import hu.cubix.logistics.bencepar.model.Address;
import hu.cubix.logistics.bencepar.repository.AddressRepository;
import hu.cubix.logistics.bencepar.service.AddressService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@Autowired
	private AddressMapper addressMapper;

	@Autowired
	AddressRepository addressRepository;

	@GetMapping
	public List<AddressDto> findAll() {
		List<Address> allAddresses = addressService.findAll();
		return addressMapper.addressesToDtos(allAddresses);
	}

	@GetMapping("/{addressId}")
	public AddressDto findById(@PathVariable Long addressId) {
		Address address = findByIdOrThrow(addressId);
		return addressMapper.addressToDto(address);
	}

	private Address findByIdOrThrow(Long addressId) {
		return addressService.findById(addressId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PreAuthorize("hasAuthority('AddressManager')")
	@PostMapping
	public AddressDto create(@RequestBody @Valid AddressDto addressDto) {

		if (addressDto.getAddressId() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		Address address = addressMapper.dtoToAddress(addressDto);
		Address savedAddress = addressService.create(address);

		if (savedAddress == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		return addressMapper.addressToDto(savedAddress);
	}

	@PreAuthorize("hasAuthority('AddressManager')")
	@PutMapping("/{addressId}")
	public AddressDto update(@PathVariable Long addressId, @RequestBody @Valid AddressDto addressDto) {
		if (addressDto.getAddressId() != null && !addressDto.getAddressId().equals(addressId)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		addressDto.setAddressId(addressId);
		Address address = addressMapper.dtoToAddress(addressDto);
		Address updatedAddress = addressService.update(address);

		if (updatedAddress == null || !updatedAddress.getAddressId().equals(addressId))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		return addressMapper.addressToDto(updatedAddress);
	}

	@DeleteMapping("/{addressId}")
	public ResponseEntity<Void> delete(@PathVariable Long addressId) {
		Optional<Address> address = addressRepository.findById(addressId);
		address.ifPresent(add -> addressRepository.delete(add));

		return ResponseEntity.ok().build();
	}

	@PostMapping("/search")
	public ResponseEntity<List<AddressDto>> findByExample(@RequestBody AddressDto example,
			@PageableDefault(size = Integer.MAX_VALUE, sort = "addressId", direction = Sort.Direction.ASC) Pageable pageable) {

		Page<Address> page = addressService.findAddressesByExample(example, pageable);

		long totalCount = addressService.getTotalMatchingCount(example);

		List<AddressDto> addresses = addressMapper.addressesToDtos(page.getContent());

		return ResponseEntity.ok().header("X-Total-Count", String.valueOf(totalCount)).body(addresses);
	}

}
