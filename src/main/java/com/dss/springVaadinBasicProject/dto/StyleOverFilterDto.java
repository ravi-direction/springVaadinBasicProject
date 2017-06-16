package com.dss.springVaadinBasicProject.dto;

import com.dss.springVaadinBasicProject.model.CountryEntity;

public class StyleOverFilterDto {

	private String styleNo;
	private CountryDto country;
	
	public String getStyleNo() {
		return styleNo;
	}
	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}
	public CountryDto getCountry() {
		return country;
	}
	public void setCountry(CountryDto countryDto) {
		this.country = countryDto;
	}
	
	
}
