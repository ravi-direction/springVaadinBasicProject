package com.dss.springVaadinBasicProject.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.dss.springVaadinBasicProject.model.ItemSizeEntity;
import com.dss.springVaadinBasicProject.model.StyleEntity;

public class ItemDto {
	
	private Integer itemId;
	private String color;
	private String itemNo;
	private StyleDto style;
	private Set<ItemSizeDto> itemSizes;
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public StyleDto getStyle() {
		return style;
	}
	public void setStyle(StyleDto style) {
		this.style = style;
	}
	public Set<ItemSizeDto> getItemSizes() {
		return itemSizes;
	}
	public void setItemSizes(Set<ItemSizeDto> itemSizes) {
		this.itemSizes = itemSizes;
	}
	
	

}
