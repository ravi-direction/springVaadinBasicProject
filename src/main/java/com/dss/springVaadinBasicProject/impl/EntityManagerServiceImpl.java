package com.dss.springVaadinBasicProject.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.dss.springVaadinBasicProject.dao.EmDao;
import com.dss.springVaadinBasicProject.model.ClientEntity;
import com.dss.springVaadinBasicProject.model.ItemEntity;
import com.dss.springVaadinBasicProject.model.ItemSizeEntity;
import com.dss.springVaadinBasicProject.model.LoginEntity;
import com.dss.springVaadinBasicProject.model.SeasonEntity;
import com.dss.springVaadinBasicProject.model.StyleEntity;
import com.dss.springVaadinBasicProject.model.StyleOverFilter;
import com.dss.springVaadinBasicProject.service.Service;
import com.vaadin.spring.annotation.SpringComponent;

public class EntityManagerServiceImpl implements Service {

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private DataSource dataSource;
	@Autowired
	@Qualifier("emDaoImpl")
	private EmDao emDao;

	/*
	 * @Autowired private StyleRepository styleRepository;
	 * 
	 * @Autowired private ItemSizeRepository itemSizeRepository;
	 * 
	 * @Autowired private ItemRepository itemRepository;
	 */

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	@Transactional
	// (propagation=Propagation.SUPPORTS)
	public void saveStyle(StyleEntity styleEntity) {
		if (styleEntity.getId() == null) {
			entityManager.persist(styleEntity);
		} else {
			entityManager.merge(styleEntity);
		}
	}

	// Retrival using constructor
	/*
	 * @Override public Iterable<StyleEntity> findAllStyles() { Query query =
	 * entityManager.createQuery("select s from StyleEntity s");
	 * Iterable<StyleEntity> styleEntity = query.getResultList(); return
	 * styleEntity; }
	 */

	// Retrival using Native Query
	public Iterable<StyleEntity> findAllStyles() {
		List<StyleEntity> styleEntity = entityManager.createNativeQuery(
				"SELECT * from style", StyleEntity.class).getResultList();
		for (StyleEntity style : styleEntity) {

		}
		return styleEntity;
	}

	// Rettrival using named query
	/*
	 * @Override public Iterable<StyleEntity> findAllStyles() { Query query =
	 * entityManager.createNamedQuery("findStyleUsingID"); Iterable<StyleEntity>
	 * styleEntity = query.getResultList(); return styleEntity; }
	 */

	@Override
	// @Transactional(propagation=Propagation.NEVER)
	public StyleEntity findByStyleId(Integer styleid) {

		/*
		 * StringBuilder sb = new StringBuilder();
		 * sb.append(" SELECT s FROM StyleEntity s ");
		 * sb.append(" LEFT JOIN FETCH s.items i ");
		 * sb.append(" LEFT JOIN FETCH i.itemSizes  ");
		 * sb.append("  WHERE s.id =:sid "); Query query =
		 * entityManager.createQuery(sb.toString()); //Query query =
		 * entityManager.createNamedQuery("findStyleUsingID");
		 * 
		 * Query query = entityManager.createNamedQuery("getStyle");
		 * query.setParameter("sid", styleid); StyleEntity style = (StyleEntity)
		 * query.getSingleResult(); return style;
		 */
		/*
		 * StyleEntity styleEntity=entityManager.find(StyleEntity.class,
		 * styleid); Hibernate.initialize(styleEntity.getItems());
		 * Set<ItemEntity> itemEntities=styleEntity.getItems(); for (ItemEntity
		 * itemEntity : itemEntities) {
		 * Hibernate.initialize(itemEntity.getItemSizes()); }
		 */

		// StyleEntity styleEntity=(StyleEntity)
		// entityManager.createNativeQuery("SELECT * from style where style_id=50",StyleEntity.class).getSingleResult();

		StyleEntity styleEntity = (StyleEntity) entityManager
				.createNativeQuery(
						"SELECT s.* from style s join item i on s.style_id=i.style_id where s.style_id=50",
						StyleEntity.class).getSingleResult();
		ItemEntity findItem = entityManager.find(ItemEntity.class, 51);
		System.out.println(findItem.getItemNo());

		// StyleEntity styleEntity=(StyleEntity)
		// entityManager.createNamedQuery("findStyleUsingID").setParameter("sid",
		// styleid).getSingleResult();
		return styleEntity;

	}

	@Override
	// @Transactional(propagation=Propagation.NEVER)
	public void saveItemSize(ItemSizeEntity itemSizeEntity) {
		if (itemSizeEntity.getItemsizeId() == null) {
			entityManager.persist(itemSizeEntity);
		} else {
			entityManager.merge(itemSizeEntity);
		}
	}

	@Override
	public Iterable<ItemSizeEntity> findAllItemSize() {
		Query query = entityManager
				.createQuery("select is from ItemSizeEntity is");
		Iterable<ItemSizeEntity> itemSizeEntity = query.getResultList();
		return itemSizeEntity;
	}

	@Override
	public ItemSizeEntity findByItemSizeId(Integer itemSizeId) {
		return entityManager.find(ItemSizeEntity.class, itemSizeId);
	}

	@Override
	public void saveItem(ItemEntity itemEntity) {
		if (itemEntity.getItemId() == null) {
			entityManager.persist(itemEntity);
		} else {
			entityManager.merge(itemEntity);
		}
	}

	@Override
	public Iterable<ItemEntity> findAllItems() {
		Query query = entityManager.createQuery("select i from ItemEntity i");
		Iterable<ItemEntity> itemEntity = query.getResultList();
		return itemEntity;
	}

	@Override
	// @Transactional(propagation = Propagation.MANDATORY)
	public ItemEntity findByItemId(Integer itemId) {
		// TODO Auto-generated method stub
		return entityManager.find(ItemEntity.class, itemId);
	}

	@Override
	public void deleteStyle(Integer styleId) {
		// TODO Auto-generated method stub
		entityManager.remove(styleId);
	}

	@Override
	public StyleEntity findByStyleIdWithItems(Integer styleid) {
		return null;
	}

	/*@Override
	public boolean isStyleExist(StyleEntity styleEntity,
			SeasonEntity seasonEntity, ClientEntity clientEntity) {
		List<StyleEntity> styleEntities = emDao.checkByStyleNo(styleEntity,
				seasonEntity, clientEntity);
		if (styleEntities.size() > 0)
			return true;

		return false;
	}*/

	@Override
	public boolean isStyleExist(StyleEntity styleEntity) {
		List<StyleEntity> styleEntities = emDao.checkByStyleNo(styleEntity);
		if (styleEntities.size() > 0)
			return true;

		return false;
	}
	
	@Override
	public Iterable<LoginEntity> findAllLogins() {
		Query query = entityManager
				.createQuery("select l from LoginEntity l");
		Iterable<LoginEntity> loginEntity = query.getResultList();
		return loginEntity;
		}

	@Override
	public void saveLogin(LoginEntity loginEntity) {
		if (loginEntity.getId() == null) {
			entityManager.persist(loginEntity);
		} else {
			entityManager.merge(loginEntity);
		}
		
	}

	@Override
	public Iterable<StyleEntity> filterByStyleNoAndCountry(StyleOverFilter filterEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean loginValidator(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * public List<StyleEntity> checkByStyleNo(StyleEntity styleEntity,
	 * SeasonEntity seasonEntity, ClientEntity clientEntity) { Query
	 * query=entityManager.createQuery(
	 * "SELECT s FROM StyleEntity s WHERE s.styleNo=:styleNumber AND s.season=:seasonId AND s.client=:clientId"
	 * ); query.setParameter("styleNumber", styleEntity.getStyleNo());
	 * query.setParameter("seasonId", seasonEntity);
	 * query.setParameter("clientId", clientEntity); List<StyleEntity>
	 * styleEntities=query.getResultList(); return styleEntities; }
	 */
}
