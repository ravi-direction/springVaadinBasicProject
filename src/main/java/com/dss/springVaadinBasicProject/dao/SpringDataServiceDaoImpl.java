package com.dss.springVaadinBasicProject.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import com.dss.springVaadinBasicProject.dto.CountryDto;
import com.dss.springVaadinBasicProject.dto.ItemDto;
import com.dss.springVaadinBasicProject.dto.ItemSizeDto;
import com.dss.springVaadinBasicProject.dto.LoginDto;
import com.dss.springVaadinBasicProject.dto.SeasonDto;
import com.dss.springVaadinBasicProject.dto.SizeDto;
import com.dss.springVaadinBasicProject.dto.StyleDto;
import com.dss.springVaadinBasicProject.dto.StyleOverFilterDto;
import com.dss.springVaadinBasicProject.model.ClientEntity;
import com.dss.springVaadinBasicProject.model.CountryEntity;
import com.dss.springVaadinBasicProject.model.ItemEntity;
import com.dss.springVaadinBasicProject.model.ItemSizeEntity;
import com.dss.springVaadinBasicProject.model.LoginEntity;
import com.dss.springVaadinBasicProject.model.SeasonEntity;
import com.dss.springVaadinBasicProject.model.SizeEntity;
import com.dss.springVaadinBasicProject.model.StyleEntity;
import com.dss.springVaadinBasicProject.model.StyleOverFilter;
import com.dss.springVaadinBasicProject.service.Service;


@RestController
public class SpringDataServiceDaoImpl implements SpringDataDao{

	@Autowired
	@Qualifier("springDataServiceImpl")
	private Service service;
	
	@Override
	public List<StyleDto> findAllStyles() {
		
		Iterable<StyleEntity> styles = service.findAllStyles();
		List<StyleDto> styleDtos = new ArrayList<StyleDto>();
		
		for (StyleEntity styleEntity : styles) {
			StyleDto styleDto = new StyleDto();
			
			styleDto.setId(styleEntity.getId());
			styleDto.setStyleNo(styleEntity.getStyleNo());
			styleDto.setDesc(styleEntity.getDesc());
			
			/*SeasonDto season = new SeasonDto();
			season.setId(styleEntity.getSeason().getId());
			season.setDescription(styleEntity.getSeason().getDescription());
			season.setName(styleEntity.getSeason().getName());*/
			
			CountryDto country = new CountryDto();
			country.setId(styleEntity.getCountry().getId());
			country.setIsoCode(styleEntity.getCountry().getIsoCode());
			country.setName(styleEntity.getCountry().getName());
			
			/*styleDto.setSeason(season);*/
			styleDto.setCountry(country);
			styleDtos.add(styleDto);
			
		}
		return styleDtos;
	}

	@Override
	public void deleteStyle(Integer styleId) {
		service.deleteStyle(styleId);
		
	}
	
	@Override
	public void createStyle(StyleDto styleDto) {
		
		StyleEntity style = convertStyleDtoToEntity(styleDto);		
		service.saveStyle(style);
		
	}

	
	@Override
	public StyleDto findByStyleId(Integer id) {
		
		StyleEntity styleEntity = service.findByStyleId(id);
		StyleDto styleDto = convertStyleEntityToDTO(styleEntity);
		return styleDto;	
	}

	@Override
	public void updateStyle(StyleDto styleDto) {
		StyleEntity styleEntity = service.findByStyleId(styleDto.getId());

		styleEntity.setStyleNo(styleDto.getStyleNo());
		styleEntity.setDesc(styleDto.getDesc());
		styleEntity.setCountry(convertCountryDtoToEntity(styleDto.getCountry()));
		
		service.saveStyle(styleEntity);
	}

		@Override
	public boolean isStyleExist(StyleDto styleDto) {
		StyleEntity style = new StyleEntity();
		style.setId(styleDto.getId());
		style.setStyleNo(styleDto.getStyleNo());
		style.setDesc(styleDto.getDesc());
		if (style.getSeason() != null) 
		{
			SeasonEntity season = new SeasonEntity();
			season.setId(styleDto.getSeason().getId());
			season.setDescription(styleDto.getSeason().getDescription());
			season.setName(styleDto.getSeason().getName());
			style.setSeason(season);
		}
		
		style.setCountry(convertCountryDtoToEntity(styleDto.getCountry()));
		if (style.getClient() != null) 
		{
			ClientEntity client = new ClientEntity();
			client.setId(styleDto.getClient().getId());
			client.setClientName(styleDto.getClient().getClientName());
			style.setClient(client);
		}
		return service.isStyleExist(style);
	}

	@Override
	public List<StyleDto> filterByStyleNoAndCountry(StyleOverFilterDto filterEntity) {
		StyleOverFilter filterStyleEntity = new StyleOverFilter();
		filterStyleEntity.setStyleNo(filterEntity.getStyleNo());
		
		CountryEntity countryEntity = new CountryEntity();
		if(filterEntity.getCountry()!=null) {
			countryEntity.setId(filterEntity.getCountry().getId());
			countryEntity.setIsoCode(filterEntity.getCountry().getIsoCode());
			countryEntity.setName(filterEntity.getCountry().getName());
			filterStyleEntity.setCountry(countryEntity);
		}
		
		Iterable<StyleEntity> styles = service.filterByStyleNoAndCountry(filterStyleEntity);
		List<StyleDto> styleDto = new ArrayList<StyleDto>();
		
		for (StyleEntity styleEntity : styles) {
			StyleDto style = new StyleDto();
			style.setId(styleEntity.getId());
			style.setStyleNo(styleEntity.getStyleNo());
			style.setDesc(styleEntity.getDesc());
			/*CountryDto country = new CountryDto();
			country.setId(styleEntity.getCountry().getId());
			country.setIsoCode(styleEntity.getCountry().getIsoCode());
			country.setName(styleEntity.getCountry().getName());*/
			style.setCountry(convertCountryEntityToDTO(styleEntity.getCountry()));
			styleDto.add(style);
		}
		return styleDto;
	}

	
	@Override
	public boolean validateUser(String username, String password) {
		if(service.loginValidator(username,password))
			return true;
		return false;
	}
	
	@Override
	public void saveLogin(LoginDto loginDto) {
		
		LoginEntity loginEntity = convertLoginDtoToEntity(loginDto);
		service.saveLogin(loginEntity);
		
	}


	public LoginEntity convertLoginDtoToEntity(LoginDto loginDto) {
		
		LoginEntity loginEntity = new LoginEntity();
		
		loginEntity.setId(loginDto.getId());
		loginEntity.setUserName(loginDto.getUserName());
		loginEntity.setPassword(loginDto.getPassword());
		
		return loginEntity;
	}

	public StyleDto convertStyleEntityToDTO(StyleEntity styleEntity)
	{
		StyleDto styleDto = new StyleDto();
		
		styleDto.setId(styleEntity.getId());
		styleDto.setDesc(styleEntity.getDesc());
		styleDto.setStyleNo(styleEntity.getStyleNo());
		styleDto.setCountry(convertCountryEntityToDTO(styleEntity.getCountry()));
		
		return styleDto;
	}

	public CountryDto convertCountryEntityToDTO(CountryEntity countryEntity) {
		
		CountryDto countryDto = new CountryDto();
		
		countryDto.setId(countryEntity.getId());
		countryDto.setIsoCode(countryEntity.getIsoCode());
		countryDto.setName(countryEntity.getName());
		
		return countryDto;
	}
	
	public StyleEntity convertStyleDtoToEntity(StyleDto styleDto)
	{
		StyleEntity styleEntity = new StyleEntity();
		
		styleEntity.setId(styleDto.getId());
		styleEntity.setStyleNo(styleDto.getStyleNo());
		styleEntity.setDesc(styleDto.getDesc());
		styleEntity.setCountry(convertCountryDtoToEntity(styleDto.getCountry()));
		
		return styleEntity;
	}

	public CountryEntity convertCountryDtoToEntity(CountryDto countryDto) {
		CountryEntity countryEntity = new CountryEntity();

		countryEntity.setId(countryDto.getId());
		countryEntity.setIsoCode(countryDto.getIsoCode());
		countryEntity.setName(countryDto.getName());
		
		return countryEntity ;
	}

	
	

}
