package com.dss.springVaadinBasicProject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dss.springVaadinBasicProject.model.SizeEntity;
@Repository
public interface SizeRepository extends CrudRepository<SizeEntity, Integer>{

}
