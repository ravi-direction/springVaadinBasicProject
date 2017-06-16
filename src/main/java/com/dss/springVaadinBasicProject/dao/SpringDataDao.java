package com.dss.springVaadinBasicProject.dao;

import java.util.List;

import com.dss.springVaadinBasicProject.dto.LoginDto;
import com.dss.springVaadinBasicProject.dto.StyleDto;
import com.dss.springVaadinBasicProject.dto.StyleOverFilterDto;
import com.dss.springVaadinBasicProject.model.ItemEntity;
import com.dss.springVaadinBasicProject.model.ItemSizeEntity;
import com.dss.springVaadinBasicProject.model.LoginEntity;
import com.dss.springVaadinBasicProject.model.StyleEntity;


public interface SpringDataDao {
	
	public void createStyle(StyleDto styleDto);

	public void updateStyle(StyleDto styleDto);

	public List<StyleDto> findAllStyles();

	public StyleDto findByStyleId(Integer id);
	
	public void deleteStyle(Integer styleId);
	
	public void saveLogin(LoginDto loginDto);
	
	public boolean isStyleExist(StyleDto styleEntity);
	
	public boolean validateUser(String username,String password);
	
	public List<StyleDto> filterByStyleNoAndCountry(StyleOverFilterDto filterEntity);

}
