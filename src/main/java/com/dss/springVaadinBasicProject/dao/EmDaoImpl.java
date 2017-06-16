package com.dss.springVaadinBasicProject.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dss.springVaadinBasicProject.model.ClientEntity;
import com.dss.springVaadinBasicProject.model.CountryEntity;
import com.dss.springVaadinBasicProject.model.SeasonEntity;
import com.dss.springVaadinBasicProject.model.StyleEntity;
import com.dss.springVaadinBasicProject.model.StyleOverFilter;

@Repository("emDaoImpl")
public class EmDaoImpl implements EmDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private DataSource dataSource;
	

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	/*public List<StyleEntity> checkByStyleNo(StyleEntity styleEntity,
			SeasonEntity seasonEntity, ClientEntity clientEntity) 
	{
		Query query=entityManager.createQuery("SELECT s FROM StyleEntity s WHERE s.styleNo=:styleNumber AND s.season=:seasonId AND s.client=:clientId");
		query.setParameter("styleNumber", styleEntity.getStyleNo());
		query.setParameter("seasonId", seasonEntity);
		query.setParameter("clientId", clientEntity);
		List<StyleEntity> styleEntities=query.getResultList();
		return styleEntities;
	}*/
	
	public List<StyleEntity> checkByStyleNo(StyleEntity styleEntity)
	{
		Query query = entityManager.createQuery("SELECT s FROM StyleEntity s WHERE s.styleNo=:styleNumber ");
		query.setParameter("styleNumber", styleEntity.getStyleNo());
		List<StyleEntity> styleEntities=query.getResultList();
		return styleEntities;
	}

	@Override
	public Iterable<StyleEntity> filterByStyleNoAndCountry(StyleOverFilter filterEntity) {
		String styleNo=filterEntity.getStyleNo();
	    CountryEntity country=filterEntity.getCountry();
			StringBuffer sb = new StringBuffer();
			Query query = null;
			sb.append("SELECT s  FROM StyleEntity s WHERE  1 = 1 ");
			
			if (styleNo != null && !styleNo.equals("")) {
				sb.append(" AND s.styleNo=:styleNumber");
			}
			if (country != null) {
				sb.append(" AND s.country=:country  ");
			}
			query = entityManager.createQuery(sb.toString());
			if (styleNo != null && !styleNo.equals("")) {
				query.setParameter("styleNumber", styleNo);
			}
			if (country != null) {
				query.setParameter("country", country);
			}

			List<StyleEntity> styles = query.getResultList();
			return styles;
	}

}
