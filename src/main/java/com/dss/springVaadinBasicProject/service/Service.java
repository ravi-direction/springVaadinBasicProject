package com.dss.springVaadinBasicProject.service;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.dss.springVaadinBasicProject.model.ClientEntity;
import com.dss.springVaadinBasicProject.model.ItemEntity;
import com.dss.springVaadinBasicProject.model.ItemSizeEntity;
import com.dss.springVaadinBasicProject.model.LoginEntity;
import com.dss.springVaadinBasicProject.model.SeasonEntity;
import com.dss.springVaadinBasicProject.model.StyleEntity;
import com.dss.springVaadinBasicProject.model.StyleOverFilter;


@Component
public interface Service {

	public void saveStyle(StyleEntity styleEntity);

	public Iterable<StyleEntity> findAllStyles();

	public StyleEntity findByStyleId(Integer id);
	
	public void deleteStyle(Integer styleId);
	
	public void saveItemSize(ItemSizeEntity itemSizeEntity);

	public Iterable<ItemSizeEntity> findAllItemSize();

	public ItemSizeEntity findByItemSizeId(Integer id);

	public void saveItem(ItemEntity itemEntity);

	public Iterable<ItemEntity> findAllItems();

	public ItemEntity findByItemId(Integer id);
	
	public Iterable<LoginEntity> findAllLogins();
	
	public void saveLogin(LoginEntity loginEntity);

	StyleEntity findByStyleIdWithItems(Integer styleid);
	
	public boolean loginValidator(String username,String password);
	
	public boolean isStyleExist(StyleEntity styleEntity);
	
	public Iterable<StyleEntity> filterByStyleNoAndCountry( StyleOverFilter filterEntity);
}
