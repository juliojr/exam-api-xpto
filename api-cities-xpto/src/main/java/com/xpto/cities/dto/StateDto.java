package com.xpto.cities.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class StateDto {

	private String uf;
	private Long numberCities;
	private String obs;

	public StateDto() {
		super();
	}

	public StateDto(String uf, Long numberCities, String obs) {
		super();
		this.uf = uf;
		this.numberCities = numberCities;
		this.obs = obs;
	}

	public StateDto(String uf, Long numberCities) {
		super();
		this.uf = uf;
		this.numberCities = numberCities;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Long getNumberCities() {
		return numberCities;
	}

	public void setNumberCities(Long numberCities) {
		this.numberCities = numberCities;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

}