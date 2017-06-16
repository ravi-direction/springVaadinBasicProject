package com.dss.springVaadinBasicProject.dao;

import com.dss.springVaadinBasicProject.dto.ClientDto;
import com.dss.springVaadinBasicProject.dto.CountryDto;
import com.dss.springVaadinBasicProject.dto.SeasonDto;
import com.dss.springVaadinBasicProject.dto.SizeDto;

public interface MasterServiceDao {

	public Iterable<SeasonDto> findAllSeason();

	public Iterable<CountryDto> findAllCountry();

	public Iterable<ClientDto> findAllClient();
	
	public Iterable<SizeDto> findAllSize();

	public SeasonDto findSeasonById(Integer id);

	public CountryDto findCountryById(Integer id);

	public ClientDto findClientById(Integer id);
	
	public SizeDto findSizeById(Integer id);
}
