package com.dss.springVaadinBasicProject.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.dss.springVaadinBasicProject.dto.ClientDto;
import com.dss.springVaadinBasicProject.dto.CountryDto;
import com.dss.springVaadinBasicProject.dto.SeasonDto;
import com.dss.springVaadinBasicProject.dto.SizeDto;
import com.dss.springVaadinBasicProject.model.CountryEntity;
import com.dss.springVaadinBasicProject.service.MasterService;

@RestController
public class MasterServiceDaoImpl implements MasterServiceDao{

	@Autowired
	private MasterService masterService;
	
	@Override
	public Iterable<SeasonDto> findAllSeason() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<CountryDto> findAllCountry() {
		Iterable<CountryEntity> countries = masterService.findAllCountry();
		List<CountryDto> countriesDto = new ArrayList<CountryDto>();
		for (CountryEntity countryEntity : countries) {
			CountryDto countryDto = new CountryDto();
			countryDto.setId(countryEntity.getId());
			countryDto.setIsoCode(countryEntity.getIsoCode());
			countryDto.setName(countryEntity.getName());
			countriesDto.add(countryDto);
		}
		return countriesDto;
	}

	@Override
	public Iterable<ClientDto> findAllClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<SizeDto> findAllSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SeasonDto findSeasonById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CountryDto findCountryById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientDto findClientById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SizeDto findSizeById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
