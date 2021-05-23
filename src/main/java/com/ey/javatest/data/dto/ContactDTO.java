package com.ey.javatest.data.dto;

public class ContactDTO {

	private String number;
	private String citycode;
	private String countrycode;

	public ContactDTO(String number, String citycode, String countrycode) {
		super();
		this.number = number;
		this.citycode = citycode;
		this.countrycode = countrycode;
	}

	public ContactDTO() {
		super();
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

}
