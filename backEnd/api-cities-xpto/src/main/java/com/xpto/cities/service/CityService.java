package com.xpto.cities.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpto.cities.model.CityModel;
import com.xpto.cities.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repo;


	public List<CityModel> returnCapitais() {
		return null;
	}

	public void saveAllCities(List<CityModel> cities) {
		repo.deleteAll();
		repo.saveAll(cities);
	}

	public List<CityModel> findAll() {

		return repo.findAll();
	}

	public List<CityModel> getCapitais() {
		return repo.getCapitais().stream().sorted(Comparator.comparing(CityModel::getName))
				.collect(Collectors.toList());
	}

}
