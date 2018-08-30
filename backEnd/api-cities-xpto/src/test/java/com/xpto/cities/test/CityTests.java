package com.xpto.cities.test;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xpto.cities.dto.GreaterDistanceDto;
import com.xpto.cities.model.CityModel;
import com.xpto.cities.repository.CityRepository;
import com.xpto.cities.service.ICityService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityTests {

	@Autowired
	private CityRepository repo;
	@Autowired
	private ICityService serv;

	@Test
	public void repositoryTest() {
		// CityModel city = this.createCity();
		// repo.save(city);
		// List<CityModel> cities = findAll();
		// List<CityModel> cities = getCapitais();
		// List<CityModel> cities = repo.findAll();
		testGreaterDistanceWithStream();
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

	private void testGreaterDistanceWithStream() {
		// test with stream it is slower than intelligent for implemented 
		List<CityModel> cities = repo.findAll();

		List<GreaterDistanceDto> ds = cities.parallelStream()
				.flatMap(c -> cities.stream().filter(c1 -> !c.equals(c1)).map(x -> {
					double aux = serv.getDistance(x.getLat().doubleValue(), x.getLon().doubleValue(),
							c.getLat().doubleValue(), c.getLon().doubleValue(), "K");
					return new GreaterDistanceDto(x, c, aux);
				})).collect(Collectors.toList());

		System.out.println(ds.stream().max(Comparator.comparing(GreaterDistanceDto::getDistanceKM)).get());
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
