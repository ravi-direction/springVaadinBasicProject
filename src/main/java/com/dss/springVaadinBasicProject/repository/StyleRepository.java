package com.dss.springVaadinBasicProject.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dss.springVaadinBasicProject.model.ClientEntity;
import com.dss.springVaadinBasicProject.model.SeasonEntity;
import com.dss.springVaadinBasicProject.model.StyleEntity;

@Repository
public interface StyleRepository extends CrudRepository<StyleEntity, Integer> {
	@EntityGraph(value = "graph.style.items", type = EntityGraphType.LOAD)
	public StyleEntity findById(Integer styleId);

	@Query("select s from StyleEntity s left join fetch s.items i left join fetch i.itemSizes where s.id=:sid")
	public StyleEntity findByIdByJpql(@Param("sid") Integer styleId);

	@Query("select s from StyleEntity s where s.styleNo=:styleno AND s.season=:season AND s.client=:client ")
	public Iterable<StyleEntity> findByStyleNoSeasonClient(
			@Param("styleno") String styleNo,
			@Param("season") SeasonEntity season,
			@Param("client") ClientEntity client);

	/*
	 * @Query("select newStyle(s.id,s.) from StyleEntity s where s.id=:sid")
	 * public StyleEntity findByIdByJpql(@Param("sid")Integer styleId);
	 */
	@Query("SELECT s FROM StyleEntity s LEFT JOIN FETCH s.items i LEFT JOIN FETCH i.itemSizes WHERE s.id =:sid")
	StyleEntity findByIdUsingJpql(@Param("sid") Integer styleId);

	//@Query("SELECT * FROM StyleEntity")
	//StyleEntity findAll();
}
