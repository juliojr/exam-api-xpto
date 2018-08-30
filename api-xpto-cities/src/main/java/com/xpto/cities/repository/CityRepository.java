package com.xpto.cities.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.xpto.cities.dto.StateDto;
import com.xpto.cities.model.CityModel;

public interface CityRepository extends JpaRepository<CityModel, Long>, JpaSpecificationExecutor<CityModel> {

	@Query("select c from CityModel c where c.capital = 'true'")
	List<CityModel> findCapitals();
	
	@Query("select new com.xpto.cities.dto.StateDto(c.uf, count(c)) from CityModel c group by c.uf")
	List<StateDto> getQtdeCitiesUf();
	
	@Query("select c from CityModel c where c.uf = :uf")
	List<CityModel> getCitiesByUf( String uf);
	
	@Query("select count(c) from CityModel c")
	Long getTotalRecords();
	

}