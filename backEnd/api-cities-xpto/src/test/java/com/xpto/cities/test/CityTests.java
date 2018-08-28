package com.xpto.cities.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xpto.cities.model.CityModel;
import com.xpto.cities.repository.CityRepository;
import com.xpto.cities.service.CityService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityTests {

	@Autowired
	private CityRepository repo;
	@Autowired
	private CityService serv;

	@Test
	public void repositoryTest() {
		//CityModel city = this.createCity();
		//repo.save(city);
		//List<CityModel> cities = findAll(); 
		//List<CityModel> cities = getCapitais();
		List<CityModel> cities = repo.findAll();
		
		double maiorDistancia = 0;
		CityModel cA = null;
		CityModel cB = null;
		for(CityModel c : cities) {
			for(CityModel c1 : cities) {
				if(!c1.equals(c)) {
					double aux = serv.getDistance(c.getLat().doubleValue(), c.getLon().doubleValue(), c1.getLat().doubleValue(), c1.getLon().doubleValue(), "K");
					if(aux > maiorDistancia) {
						maiorDistancia = aux;
						cA = c;
						cB = c1;
					}
				}
			}
			System.out.println(cA);
			System.out.println(cB);
			System.out.println(maiorDistancia);
		}
		System.out.println(cA);
		System.out.println(cB);
		System.out.println(maiorDistancia);

	}

	@SuppressWarnings("unused")
	private CityModel createCity() {
		CityModel city = new CityModel();
		city.setIbgeId(1100023L);
		city.setUf("RO");
		city.setName("Ariquemes");
		city.setLon(new BigDecimal("-63.033269278"));
		city.setLat(new BigDecimal("-9.9084628666"));
		city.setNoAccents("Ariquemes");
		city.setMicroregion("Ariquemes");
		city.setMesoregion("Leste Rondoniense");

		return city;
	}

	@SuppressWarnings("unused")
	private List<CityModel> getCapitais() {
		return serv.findCapitals();
	}
	@SuppressWarnings("unused")
	private List<CityModel> findAll() {
		return serv.findAll();
	}
	public CityRepository getRepo() {
		return repo;
	}

	public void setRepo(CityRepository repo) {
		this.repo = repo;
	}

}
