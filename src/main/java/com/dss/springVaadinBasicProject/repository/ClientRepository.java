package com.dss.springVaadinBasicProject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dss.springVaadinBasicProject.model.ClientEntity;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Integer> {

}
