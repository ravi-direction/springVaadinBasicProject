package com.dss.springVaadinBasicProject.dto;

import javax.persistence.Column;

public class ClientDto {
	
	private Integer id;
	private String clientName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
}
