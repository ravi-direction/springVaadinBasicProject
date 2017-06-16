package com.dss.springVaadinBasicProject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dss.springVaadinBasicProject.model.SeasonEntity;

@Repository
public interface SeasonRepository extends CrudRepository<SeasonEntity, Integer>{

}
