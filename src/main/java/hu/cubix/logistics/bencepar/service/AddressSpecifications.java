package hu.cubix.logistics.bencepar.service;

import org.springframework.data.jpa.domain.Specification;

import hu.cubix.logistics.bencepar.model.Address;
import hu.cubix.logistics.bencepar.model.Address_;

public class AddressSpecifications {

	public static Specification<Address> hasAddressId(Long addressId) {
		
		return (root, cq, cb) -> cb
				.equal(root.get(Address_.addressId), addressId);
	}
	
	public static Specification<Address> hasCountryCode(String countryCode) {
		
		return (root, cq, cb) -> cb
				.equal(root.get(Address_.countryCode), countryCode);
	}
	
	public static Specification<Address> hasCity(String prefix) {
		
		return (root, cq, cb) -> cb
				.like(cb.lower(root.get(Address_.city)), prefix.toLowerCase() + "%");
	}
	
	public static Specification<Address> hasStreet(String prefix) {
		
		return (root, cq, cb) -> cb
				.like(cb.lower(root.get(Address_.street)), prefix.toLowerCase() + "%");
	}
	
	public static Specification<Address> hasZip(long zip) {
		
		return (root, cq, cb) -> cb
				.equal(root.get(Address_.zip), zip);
	}
	
	public static Specification<Address> hasHouseNumber(int houseNumber) {
		
		return (root, cq, cb) -> cb
				.equal(root.get(Address_.houseNumber), houseNumber);
	}
	
	public static Specification<Address> hasLatitude(double latitude) {
		
		return (root, cq, cb) -> cb
				.between(root.get(Address_.latitude), (double)(latitude - 0.1), (double)(latitude + 0.1));
	}
	
	public static Specification<Address> hasLongitude(double longitude) {
	
		return (root, cq, cb) -> cb
				.between(root.get(Address_.longitude), (double)(longitude - 0.1), (double)(longitude + 0.1));
	}
	
}
