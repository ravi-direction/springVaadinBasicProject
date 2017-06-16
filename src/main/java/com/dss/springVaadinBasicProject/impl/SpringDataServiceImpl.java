package com.dss.springVaadinBasicProject.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.dss.springVaadinBasicProject.dao.EmDao;
import com.dss.springVaadinBasicProject.model.ItemEntity;
import com.dss.springVaadinBasicProject.model.ItemSizeEntity;
import com.dss.springVaadinBasicProject.model.LoginEntity;
import com.dss.springVaadinBasicProject.model.StyleEntity;
import com.dss.springVaadinBasicProject.model.StyleOverFilter;
import com.dss.springVaadinBasicProject.repository.ItemRepository;
import com.dss.springVaadinBasicProject.repository.ItemSizeRepository;
import com.dss.springVaadinBasicProject.repository.LoginRepository;
import com.dss.springVaadinBasicProject.repository.StyleRepository;
import com.dss.springVaadinBasicProject.service.Service;


public class SpringDataServiceImpl implements Service {

	@Autowired
	private StyleRepository styleRepository;

	@Autowired
	private ItemSizeRepository itemSizeRepository;

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private EmDao emDao;

	public Set<ItemEntity> items = new HashSet<ItemEntity>();

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void saveStyle(StyleEntity styleEntity) {
		styleRepository.save(styleEntity);
	}

	@Override
	public Iterable<StyleEntity> findAllStyles() {
		return styleRepository.findAll();
	}

	@Override
	public StyleEntity findByStyleId(Integer styleid) {
		StyleEntity styleEntity = styleRepository.findOne(styleid);
		return styleEntity;

	}
	

	@Override
	public void deleteStyle(Integer id) {
		styleRepository.delete(id);
		// StyleEntity style=styleRepository.findOne(styleid);
		//StyleEntity style = styleRepository.findById(styleid);
		 //StyleEntity style=styleRepository.findByIdUsingJpql(id);
		// System.out.println("size is "+style.getItems());
	}

	@Override
	public void saveItemSize(ItemSizeEntity itemSizeEntity) {
		itemSizeRepository.save(itemSizeEntity);
	}

	@Override
	public Iterable<ItemSizeEntity> findAllItemSize() {
		return itemSizeRepository.findAll();
	}

	@Override
	public ItemSizeEntity findByItemSizeId(Integer itemSizeId) {
		// System.out.println("Hello");
		// TODO Auto-generated method stub
		return itemSizeRepository.findOne(itemSizeId);
	}

	@Override
	public void saveItem(ItemEntity itemEntity) {
		// System.out.println("Item Save");
		itemRepository.save(itemEntity);
	}

	@Override
	public Iterable<ItemEntity> findAllItems() {
		return itemRepository.findAll();
	}

	@Override
	public ItemEntity findByItemId(Integer itemId) {

		return itemRepository.findOne(itemId);
		//return itemRepository.findByitemId(itemId);
		//return itemRepository.findByIdUsingJpql(itemId);
	}

	/*@Override
	public boolean isStyleExist(StyleEntity styleEntity,
			SeasonEntity seasonEntity, ClientEntity clientEntity) {
		// TODO Auto-generated method stub
		return false;
	}
*/
	@Override
	public StyleEntity findByStyleIdWithItems(Integer styleid) {
		// StyleEntity styleEntity = styleRepository.findById(styleid);
		StyleEntity styleEntity = styleRepository.findByIdUsingJpql(styleid);
		return styleEntity;
	}

	@Override
	public Iterable<LoginEntity> findAllLogins() {
		// TODO Auto-generated method stub
		return loginRepository.findAll();
	}

	
	public boolean loginValidator(String userName, String password)
	{
		int count = 0;
		Iterable<LoginEntity> loginlist = loginRepository.findAll();
		for (LoginEntity loginEntity : loginlist) {
			if(loginEntity.getUserName().equals(userName)&&loginEntity.getPassword().equals(password))
			{
				count++;
			}
		}
		if(count>0)
		return true;
		return false;
		
	}

	@Override
	public void saveLogin(LoginEntity loginEntity) {
		loginRepository.save(loginEntity);	}

	@Override
	public boolean isStyleExist(StyleEntity styleEntity) {
		List<StyleEntity> styles = emDao.checkByStyleNo(styleEntity);
		if (styles.size()>0)
			return true;
		return false;
	}
	
	
	@Override
	public Iterable<StyleEntity> filterByStyleNoAndCountry(StyleOverFilter filterEntity) {
		// TODO Auto-generated method stub
		return emDao.filterByStyleNoAndCountry(filterEntity);
	}
}