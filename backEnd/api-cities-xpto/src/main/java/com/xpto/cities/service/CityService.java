package com.xpto.cities.service;

import static com.xpto.cities.specification.CitySpecification.filterWithOptions;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xpto.cities.dto.GreaterDistanceDto;
import com.xpto.cities.dto.StateDto;
import com.xpto.cities.exception.CitiesFileException;
import com.xpto.cities.exception.DefaultXptoException;
import com.xpto.cities.model.CityModel;
import com.xpto.cities.payload.DefaultResponse;
import com.xpto.cities.repository.CityRepository;
import com.xpto.cities.util.Util;

@Service
public class CityService implements ICityService {
	@Autowired
	private CityRepository cityRepository;

	public CityRepository getCityRepository() {
		return cityRepository;
	}

	@Override
	public Page<CityModel> list(Map<String, String> filters, Pageable pageable) {
		return cityRepository.findAll(filterWithOptions(filters), pageable);
	}

	@Override
	public void saveAllCities(List<CityModel> cities) {
		try {
			cityRepository.saveAll(cities);
		} catch (Exception e) {
			throw new DefaultXptoException(e.getMessage(), e, HttpStatus.SERVICE_UNAVAILABLE);
		}

	}

	@Override
	public List<CityModel> findAll() {
		try {
			return cityRepository.findAll();
		} catch (Exception e) {
			throw new DefaultXptoException(e.getMessage(), e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@Override
	public DefaultResponse findCapitals() {
		try {
			List<Object> cities = Util.castCollection(cityRepository.findCapitals(), Object.class);
			HttpStatus status = HttpStatus.OK;
			if (cities.isEmpty()) {
				status = HttpStatus.NO_CONTENT;
			}
			DefaultResponse response = new DefaultResponse(status, "application/json", new Long(cities.size()), cities);

			return response;
		} catch (Exception e) {
			throw new DefaultXptoException(e.getMessage(), e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@Override
	public DefaultResponse getNumberCitiesUf() {
		try {
			List<StateDto> ufsDto = cityRepository.getQtdeCitiesUf().stream()
					.sorted(Comparator.comparing(StateDto::getUf)).collect(Collectors.toList());
			List<Object> ufs = Util.castCollection(ufsDto, Object.class);
			HttpStatus status = HttpStatus.OK;
			if (ufs.isEmpty()) {
				status = HttpStatus.NO_CONTENT;
			}
			DefaultResponse response = new DefaultResponse(status, "application/json", new Long(ufs.size()), ufs);
			return response;
		} catch (Exception e) {
			throw new DefaultXptoException(e.getMessage(), e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@Override
	public DefaultResponse getLargestSmallestUfs() {
		try {
			List<StateDto> ufsDto = new ArrayList<>();
			List<StateDto> aux = cityRepository.getQtdeCitiesUf().stream().sorted(Comparator.comparing(StateDto::getUf))
					.collect(Collectors.toList());
			StateDto max = aux.stream().max((p1, p2) -> Long.compare(p1.getNumberCities(), p2.getNumberCities())).get();
			max.setObs("UF com maior numero de cidades");
			ufsDto.add(max);
			StateDto min = aux.stream().min((p1, p2) -> Long.compare(p1.getNumberCities(), p2.getNumberCities())).get();
			min.setObs("UF com menor numero de cidades");
			ufsDto.add(min);
			List<Object> ufs = Util.castCollection(ufsDto, Object.class);
			HttpStatus status = HttpStatus.OK;
			if (ufs.isEmpty()) {
				status = HttpStatus.NO_CONTENT;
			}
			DefaultResponse response = new DefaultResponse(status, "application/json", new Long(ufs.size()), ufs);
			return response;
		} catch (Exception e) {
			throw new DefaultXptoException(e.getMessage(), e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@Override
	public DefaultResponse findByIbgeId(Long ibgeId) {
		try {
			List<Optional<CityModel>> city = new ArrayList<>();
			city.add(cityRepository.findById(ibgeId));
			List<Object> objCity = Util.castCollection(city, Object.class);
			HttpStatus status = HttpStatus.OK;
			if (objCity.isEmpty()) {
				status = HttpStatus.NO_CONTENT;
			}
			DefaultResponse response = new DefaultResponse(status, "application/json", new Long(objCity.size()),
					objCity);
			return response;
		} catch (Exception e) {
			throw new DefaultXptoException("city not found", e, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public DefaultResponse getCitiesByUf(String uf) {
		try {
			List<String> cities = cityRepository.getCitiesByUf(uf).stream()
					.sorted(Comparator.comparing(CityModel::getName)).map(x -> x.getName() + " - " + x.getUf())
					.collect(Collectors.toList());
			List<Object> objcities = Util.castCollection(cities, Object.class);
			HttpStatus status = HttpStatus.OK;
			if (objcities.isEmpty()) {
				status = HttpStatus.NO_CONTENT;
			}
			DefaultResponse response = new DefaultResponse(status, "application/json", new Long(objcities.size()),
					objcities);
			return response;
		} catch (Exception e) {
			throw new DefaultXptoException(e.getMessage(), e, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public DefaultResponse saveCity(CityModel city) {
		try {
			CityModel cityModel = cityRepository.save(city);
			List<Object> objCity = new ArrayList<>();
			objCity.add(cityModel);
			return new DefaultResponse(HttpStatus.ACCEPTED, "application/json", new Long(objCity.size()), objCity);
		} catch (Exception e) {
			throw new DefaultXptoException(e.getMessage(), e, HttpStatus.BAD_GATEWAY);
		}
	}

	@Override
	public DefaultResponse deleteCity(Long ibgeId) {
		try {
			CityModel city = new CityModel();
			city.setIbgeId(ibgeId);
			cityRepository.delete(city);
			return new DefaultResponse(HttpStatus.OK, null, null, null);
		} catch (Exception e) {
			throw new DefaultXptoException("City with ibge_id: " + ibgeId + " not found.", e, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public DefaultResponse getTotalRecords() {
		try {
			return new DefaultResponse(HttpStatus.OK, null, cityRepository.getTotalRecords(), null);
		} catch (Exception e) {
			throw new DefaultXptoException(e.getMessage(), e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	// return distance of 2 cities
	private double getDistance(double lat1, double lon1, double lat2, double lon2, String unit) {
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

	@Override
	// return greater Distance of all cities
	public DefaultResponse getGreaterDistance() {

		try {

			List<CityModel> cities = cityRepository.findAll();

			double greaterDistanceKm = 0;
			CityModel cA = null;
			CityModel cB = null;
			for (CityModel c : cities) {
				for (CityModel c1 : cities) {
					if (!c1.equals(c)) {
						double aux = getDistance(c.getLat().doubleValue(), c.getLon().doubleValue(),
								c1.getLat().doubleValue(), c1.getLon().doubleValue(), "K");
						if (aux > greaterDistanceKm) {
							greaterDistanceKm = aux;
							cA = c;
							cB = c1;
						}
					}
				}
			}
			GreaterDistanceDto greaterDistance = new GreaterDistanceDto(cA, cB, greaterDistanceKm);
			List<Object> objGreater = new ArrayList<>();
			objGreater.add(greaterDistance);
			HttpStatus status = HttpStatus.OK;
			if (objGreater.isEmpty()) {
				status = HttpStatus.NO_CONTENT;
			}
			return new DefaultResponse(status, "application/json", new Long(objGreater.size()), objGreater);
		} catch (Exception e) {
			throw new DefaultXptoException(e.getMessage(), e, HttpStatus.BAD_GATEWAY);
		}

	}

	// convert file in list of cities
	public List<CityModel> getCitiesByFile(MultipartFile file, Path citiesFileLocation) {
		List<CityModel> cities = null;
		try (Stream<String> lines = Files
				.lines(citiesFileLocation.resolve(file.getOriginalFilename().trim()).normalize())) {
			cities = lines.skip(1).map(mapToCity).collect(Collectors.toList());
		} catch (Exception e) {
			throw new CitiesFileException(
					"Desculpe, não foi possivel converter o arquivo, verifique e tente novamente!");
		}
		return cities;
	}

	// converts decimal degrees to radians
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	// converts radians to decimal degrees
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	// map a line of file to a CityModel
	public static Function<String, CityModel> mapToCity = (line) -> {
		String[] c = line.split(",");
		return new CityModel(new Long(c[0]), c[1], c[2], c[3], new BigDecimal(c[4]), new BigDecimal(c[5]), c[6], c[7],
				c[8], c[9]);
	};
}
