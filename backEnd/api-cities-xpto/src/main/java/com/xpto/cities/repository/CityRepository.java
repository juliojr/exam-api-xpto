package com.xpto.cities.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.xpto.cities.model.CityModel;

@Repository
public interface CityRepository extends MongoRepository<CityModel, Long>{
	
	@Query("{ 'capital' : 'true' }")
	List<CityModel> getCapitais();

}
