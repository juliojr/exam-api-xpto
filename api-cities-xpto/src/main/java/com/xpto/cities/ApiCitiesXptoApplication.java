package com.xpto.cities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.xpto.cities.property.CitiesFileProperties;


@SpringBootApplication
@EnableConfigurationProperties({ CitiesFileProperties.class })
@EnableJpaAuditing
@EnableAutoConfiguration
public class ApiCitiesXptoApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiCitiesXptoApplication.class, args);
	}
}
  