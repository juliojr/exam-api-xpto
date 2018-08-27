package com.xpto.cities.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xpto.cities.model.CityModel;

public interface CityRepository extends MongoRepository<CityModel, Long>{
	
	

}
