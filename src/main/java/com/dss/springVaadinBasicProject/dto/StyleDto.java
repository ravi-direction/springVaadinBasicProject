package com.dss.springVaadinBasicProject.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.dss.springVaadinBasicProject.model.ClientEntity;
import com.dss.springVaadinBasicProject.model.CountryEntity;
import com.dss.springVaadinBasicProject.model.ItemEntity;
import com.dss.springVaadinBasicProject.model.SeasonEntity;

public class StyleDto {
	
	private Integer id;
	private String styleNo;
	private String desc;
	private SeasonDto season;
	private CountryDto country;
	private Set<ItemDto> items;
	private ClientDto client;	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStyleNo() {
		return styleNo;
	}
	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public SeasonDto getSeason() {
		return season;
	}
	public void setSeason(SeasonDto season) {
		this.season = season;
	}
	public CountryDto getCountry() {
		return country;
	}
	public void setCountry(CountryDto country) {
		this.country = country;
	}
	public Set<ItemDto> getItems() {
		return items;
	}
	public void setItems(Set<ItemDto> items) {
		this.items = items;
	}
	public ClientDto getClient() {
		return client;
	}
	public void setClient(ClientDto client) {
		this.client = client;
	}
	
}
