package com.dss.springVaadinBasicProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sizes")
public class SizeEntity {
	@Column(name = "size_id")
	@Id
	private Integer sizeId;
	@Column(name="size_code")
	private String sizeCode;
	
 
	
	public Integer getSizeId() {
		return sizeId;
	}
	public void setSizeId(Integer sizeId) {
		this.sizeId = sizeId;
	}
	public String getSizeCode() {
		return sizeCode;
	}
	@Override
	public String toString() {
		return "SizeEntity [sizeCode=" + sizeCode + "]";
	}
	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}
	

}
