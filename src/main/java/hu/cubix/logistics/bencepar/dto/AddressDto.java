package hu.cubix.logistics.bencepar.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AddressDto {

	private Long addressId;
	@NotEmpty
	private String countryCode;
	@NotEmpty
	private String city;
	@NotEmpty
	private String street;
	@NotNull
	private Long zip;
	@NotNull
	private Integer houseNumber;
	private Double latitude;
	private Double longitude;

	public AddressDto() {
	}

	public AddressDto(Long addressId, String countryCode, String city, String street, Long zip, Integer houseNumber,
			Double latitude, Double longitude) {
		super();
		this.addressId = addressId;
		this.countryCode = countryCode;
		this.street = street;
		this.zip = zip;
		this.houseNumber = houseNumber;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Long getZip() {
		return zip;
	}

	public void setZip(Long zip) {
		this.zip = zip;
	}

	public Integer getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
