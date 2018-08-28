package com.xpto.cities.payload;

public class StatesDto {
	
	private String uf;
	private Long qtdeCities;
	
	public StatesDto(String uf, Long qtdeCities) {
		super();
		this.uf = uf;
		this.qtdeCities = qtdeCities;
	}
	public StatesDto() {
		super();
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public Long getQtdeCities() {
		return qtdeCities;
	}
	public void setQtdeCities(Long qtdeCities) {
		this.qtdeCities = qtdeCities;
	}
	
	

}
