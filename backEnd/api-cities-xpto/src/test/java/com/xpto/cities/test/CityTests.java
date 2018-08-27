package com.xpto.cities.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xpto.cities.model.CityModel;
import com.xpto.cities.repository.CityRepository;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityTests {
	
	@Autowired
	private CityRepository repo;

	@Test
	public void repositoryTest() {
		CityModel city = this.createCity();
		repo.save(city);
	}

	private CityModel createCity() {
		//ibge_id,uf,name,capital,lon,lat,no_accents,alternative_names,microregion,mesoregion
		//1100023,RO,Ariquemes,,-63.033269278,-9.9084628666,Ariquemes,,Ariquemes,Leste Rondoniense
		CityModel city = new CityModel();
		city.setIbgeId(1100023L);
		city.setUf("RO");
		city.setName("Ariquemes");
		city.setLon(new BigDecimal("-63.033269278"));
		city.setLat(new BigDecimal("-9.9084628666"));
		city.setNoAccents("Ariquemes");
		city.setMicroregion("Ariquemes");
		city.setMesoregion("Leste Rondoniense");
		
		
		return null;
	}

	public CityRepository getRepo() {
		return repo;
	}

	public void setRepo(CityRepository repo) {
		this.repo = repo;
	}
	
	
	
}
