package com.dss.springVaadinBasicProject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dss.springVaadinBasicProject.model.LoginEntity;

@Repository
public interface LoginRepository extends CrudRepository<LoginEntity, Integer> {

}
