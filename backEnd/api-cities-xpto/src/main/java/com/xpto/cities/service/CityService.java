package com.xpto.cities.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpto.cities.model.CityModel;
import com.xpto.cities.payload.StatesDto;
import com.xpto.cities.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repo;

	public List<CityModel> returnCapitais() {
		return null;
	}

	public void saveAllCities(List<CityModel> cities) {
		repo.saveAll(cities);
	}

	public List<CityModel> findAll() {
		return repo.findAll();
	}

	public List<CityModel> findCapitals() {
		return repo.findCapitals();
	}

	public List<StatesDto> getQtdeCitiesUf() {
		return repo.getQtdeCitiesUf().stream().sorted(Comparator.comparing(StatesDto::getUf))
				.collect(Collectors.toList());
	}
	
	public List<StatesDto> getLargestAndSmallestOfUfs(){
		
		List<StatesDto> ret = new ArrayList<>();
		
		List<StatesDto> aux = getQtdeCitiesUf();

		ret.add(aux.stream().max((p1, p2) -> Long.compare(p1.getQtdeCities(), p2.getQtdeCities())).get());
		ret.add(aux.stream().min((p1, p2) -> Long.compare(p1.getQtdeCities(), p2.getQtdeCities())).get());
		
		return ret;
	}

	public Optional<CityModel> findById(Long ibgeId) {
		return repo.findById(ibgeId);
	}

	public List<String> getCitiesByUf(String uf) {
		return repo.getCitiesByUf(uf).stream().sorted(Comparator.comparing(CityModel::getName)).map(x -> x.getName()+"-"+x.getUf())
				.collect(Collectors.toList());
	}

	public CityModel saveCity(CityModel city) {
		return repo.save(city);
	}

	public String deleteCity(Long ibgeId) {
		CityModel city = new CityModel();
		city.setIbgeId(ibgeId);
		repo.delete(city);
		return "City: " + ibgeId + " was removed";
	}

	public Long getTotalRecords() {
		return repo.getTotalRecords();
	}

	public double getDistance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}

		return (dist);
	}

	// This function converts decimal degrees to radians
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	// This function converts radians to decimal degrees
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	public String getGreaterDistance() {

		List<CityModel> cities = repo.findAll();

		double greaterDistance = 0;
		CityModel cA = null;
		CityModel cB = null;
		for (CityModel c : cities) {
			for (CityModel c1 : cities) {
				if (!c1.equals(c)) {
					double aux = getDistance(c.getLat().doubleValue(), c.getLon().doubleValue(),
							c1.getLat().doubleValue(), c1.getLon().doubleValue(), "K");
					if (aux > greaterDistance) {
						greaterDistance = aux;
						cA = c;
						cB = c1;
					}
				}
			}
		}

		return "A maior distância entre as cidades cadastradas é de :" + cA.getName() + "-" + cA.getUf() + " até "
				+ cB.getName() + "-" + cB.getUf() + " Distância em linha reta de: " + greaterDistance + " KM";

	}

}
