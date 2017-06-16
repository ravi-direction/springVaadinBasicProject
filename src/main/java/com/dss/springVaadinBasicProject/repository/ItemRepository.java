package com.dss.springVaadinBasicProject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dss.springVaadinBasicProject.model.ItemEntity;
@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, Integer> {

}
