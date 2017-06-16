package com.dss.springVaadinBasicProject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dss.springVaadinBasicProject.model.CountryEntity;

@Repository
public interface CountryRepository extends CrudRepository<CountryEntity	, Integer>{

}
