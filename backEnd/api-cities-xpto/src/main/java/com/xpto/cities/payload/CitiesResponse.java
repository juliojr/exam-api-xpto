package com.xpto.cities.payload;

import java.util.List;

import com.xpto.cities.model.CityModel;

public class CitiesResponse {
	public String message;
	public Long records;
	public List<CityModel> cities;

	public CitiesResponse(String message, List<CityModel> cities) {
		super();
		this.message = message;
		this.records = new Long(cities.size());
		this.cities = cities;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getRecords() {
		return records;
	}

	public void setRecords(Long records) {
		this.records = records;
	}

	public List<CityModel> getCities() {
		return cities;
	}

	public void setCities(List<CityModel> cities) {
		this.cities = cities;
	}

	
}
