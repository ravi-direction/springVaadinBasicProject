package com.dss.springVaadinBasicProject.dao;

import java.util.List;

import com.dss.springVaadinBasicProject.model.ClientEntity;
import com.dss.springVaadinBasicProject.model.SeasonEntity;
import com.dss.springVaadinBasicProject.model.StyleEntity;
import com.dss.springVaadinBasicProject.model.StyleOverFilter;

public interface EmDao {
	
	//List<StyleEntity> checkByStyleNo(StyleEntity styleEntity, SeasonEntity seasonEntity, ClientEntity clientEntity);
	List<StyleEntity> checkByStyleNo(StyleEntity styleEntity);

	Iterable<StyleEntity> filterByStyleNoAndCountry(StyleOverFilter filterEntity);

}
