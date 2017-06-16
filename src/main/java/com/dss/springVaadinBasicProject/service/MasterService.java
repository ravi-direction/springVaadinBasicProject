package com.dss.springVaadinBasicProject.service;

import com.dss.springVaadinBasicProject.model.ClientEntity;
import com.dss.springVaadinBasicProject.model.CountryEntity;
import com.dss.springVaadinBasicProject.model.SeasonEntity;
import com.dss.springVaadinBasicProject.model.SizeEntity;

public interface MasterService {
	public Iterable<SeasonEntity> findAllSeason();

	public Iterable<CountryEntity> findAllCountry();

	public Iterable<ClientEntity> findAllClient();
	
	public Iterable<SizeEntity> findAllSize();

	public SeasonEntity findSeasonById(Integer id);

	public CountryEntity findCountryById(Integer id);

	public ClientEntity findClientById(Integer id);
	
	public SizeEntity findSizeById(Integer id);

}
