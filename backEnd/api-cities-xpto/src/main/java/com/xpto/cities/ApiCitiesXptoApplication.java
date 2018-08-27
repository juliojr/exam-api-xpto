package com.xpto.cities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.xpto.cities.property.CitiesFileProperties;
import com.xpto.cities.repository.CityRepository;


@SpringBootApplication
@EnableConfigurationProperties({ CitiesFileProperties.class })
@EnableAutoConfiguration
@EnableMongoRepositories(basePackageClasses=CityRepository.class )
public class ApiCitiesXptoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCitiesXptoApplication.class, args);
	}
}
