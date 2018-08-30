package com.xpto.cities.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@Table(name = "cities")
@EntityListeners(AuditingEntityListener.class)
public class CityModel {
	@Id
	private Long ibgeId;
	@NotBlank
	private String uf;
	@NotBlank
	private String name;
	private String capital;
	@NotNull
	@Digits(integer = 10, fraction = 10)
	private BigDecimal lon;
	@NotNull
	@Digits(integer = 10, fraction = 10)
	private BigDecimal lat;
	@NotBlank
	@Column(name = "no_accents")
	private String noAccents;
	private String alternativeNames;
	@NotBlank
	private String microregion;
	@NotBlank
	private String mesoregion;

	public CityModel() {
		super();
	}

	public CityModel(Long ibgeId, String uf, String name, String capital, BigDecimal lon, BigDecimal lat,
			String noAccents, String alternativeNames, String microregion, String mesoregion) {
		super();
		this.ibgeId = ibgeId;
		this.uf = uf;
		this.name = name;
		this.capital = capital;
		this.lon = lon;
		this.lat = lat;
		this.noAccents = noAccents;
		this.alternativeNames = alternativeNames;
		this.microregion = microregion;
		this.mesoregion = mesoregion;
	}

	public Long getIbgeId() {
		return ibgeId;
	}

	public void setIbgeId(Long ibgeId) {
		this.ibgeId = ibgeId;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public BigDecimal getLon() {
		return lon;
	}

	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public String getNoAccents() {
		return noAccents;
	}

	public void setNoAccents(String noAccents) {
		this.noAccents = noAccents;
	}

	public String getAlternativeNames() {
		return alternativeNames;
	}

	public void setAlternativeNames(String alternativeNames) {
		this.alternativeNames = alternativeNames;
	}

	public String getMicroregion() {
		return microregion;
	}

	public void setMicroregion(String microregion) {
		this.microregion = microregion;
	}

	public String getMesoregion() {
		return mesoregion;
	}

	public void setMesoregion(String mesoregion) {
		this.mesoregion = mesoregion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alternativeNames == null) ? 0 : alternativeNames.hashCode());
		result = prime * result + ((capital == null) ? 0 : capital.hashCode());
		result = prime * result + ((ibgeId == null) ? 0 : ibgeId.hashCode());
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((lon == null) ? 0 : lon.hashCode());
		result = prime * result + ((mesoregion == null) ? 0 : mesoregion.hashCode());
		result = prime * result + ((microregion == null) ? 0 : microregion.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((noAccents == null) ? 0 : noAccents.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CityModel other = (CityModel) obj;
		if (alternativeNames == null) {
			if (other.alternativeNames != null)
				return false;
		} else if (!alternativeNames.equals(other.alternativeNames))
			return false;
		if (capital == null) {
			if (other.capital != null)
				return false;
		} else if (!capital.equals(other.capital))
			return false;
		if (ibgeId == null) {
			if (other.ibgeId != null)
				return false;
		} else if (!ibgeId.equals(other.ibgeId))
			return false;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (lon == null) {
			if (other.lon != null)
				return false;
		} else if (!lon.equals(other.lon))
			return false;
		if (mesoregion == null) {
			if (other.mesoregion != null)
				return false;
		} else if (!mesoregion.equals(other.mesoregion))
			return false;
		if (microregion == null) {
			if (other.microregion != null)
				return false;
		} else if (!microregion.equals(other.microregion))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (noAccents == null) {
			if (other.noAccents != null)
				return false;
		} else if (!noAccents.equals(other.noAccents))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CityModel [ibgeId=" + ibgeId + ", uf=" + uf + ", name=" + name + ", capital=" + capital + ", lon=" + lon
				+ ", lat=" + lat + ", noAccents=" + noAccents + ", alternativeNames=" + alternativeNames
				+ ", microregion=" + microregion + ", mesoregion=" + mesoregion + "]";
	}

}
