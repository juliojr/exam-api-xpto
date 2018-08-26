package com.xpto.cities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.xpto.cities.property.CitiesFileProperties;

@SpringBootApplication
@EnableConfigurationProperties({ CitiesFileProperties.class })
public class ApiCitiesXptoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCitiesXptoApplication.class, args);
	}
}
