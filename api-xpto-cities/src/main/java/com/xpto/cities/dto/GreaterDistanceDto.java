package com.xpto.cities.dto;

import com.xpto.cities.model.CityModel;

public class GreaterDistanceDto {
	private CityModel cityA;
	private CityModel cityB;
	private double distanceKM;

	public GreaterDistanceDto(CityModel cityA, CityModel cityB, double distanceKM) {
		super();
		this.cityA = cityA;
		this.cityB = cityB;
		this.distanceKM = distanceKM;
	}

	public CityModel getCityA() {
		return cityA;
	}

	public void setCityA(CityModel cityA) {
		this.cityA = cityA;
	}

	public CityModel getCityB() {
		return cityB;
	}

	public void setCityB(CityModel cityB) {
		this.cityB = cityB;
	}

	public double getDistanceKM() {
		return distanceKM;
	}

	public void setDistanceKM(double distanceKM) {
		this.distanceKM = distanceKM;
	}

	@Override
	public String toString() {
		return "GreaterDistanceDto [cityA=" + cityA + ", cityB=" + cityB + ", distanceKM=" + distanceKM + "]";
	}

}
