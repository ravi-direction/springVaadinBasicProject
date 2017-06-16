package com.dss.springVaadinBasicProject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dss.springVaadinBasicProject.model.ItemSizeEntity;

@Repository
public interface ItemSizeRepository extends CrudRepository<ItemSizeEntity, Integer>{

}
