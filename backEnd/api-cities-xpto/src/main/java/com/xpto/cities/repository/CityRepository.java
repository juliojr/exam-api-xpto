package com.xpto.cities.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xpto.cities.model.CityModel;
import com.xpto.cities.payload.StatesDto;

public interface CityRepository extends JpaRepository<CityModel, Long>{
	
	@Query("select c from CityModel c where c.capital = 'true'")
	List<CityModel> findCapitals();
	
	@Query("select new com.xpto.cities.payload.StatesDto(c.uf, count(c)) from CityModel c group by c.uf")
	List<StatesDto> getQtdeCitiesUf();
	
	@Query("select c from CityModel c where c.uf = :uf")
	List<CityModel> getCitiesByUf( String uf);
	
	@Query("select count(c) from CityModel c")
	Long getTotalRecords();
	

}
