package com.xpto.cities.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xpto.cities.model.CityModel;
import com.xpto.cities.payload.CitiesFileResponse;
import com.xpto.cities.service.CitiesFileService;
import com.xpto.cities.service.CityService;

@RestController
public class CityContoller {

	@Autowired
	private CitiesFileService citiesFileService;
	
	@Autowired
	private CityService cityService;
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@PostMapping("/uploadFile")
	public CitiesFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		LOG.info("Upload all cities in mongodb please wait.");
		return new CitiesFileResponse(citiesFileService.storeFile(file), file.getContentType(), file.getSize());
	}
	
	@GetMapping("/capitais")
	public ResponseEntity<List<CityModel>> getCapitais() {
		LOG.info("Load all capitals sorted by name.");
		return new ResponseEntity<List<CityModel>> (cityService.getCapitais(), HttpStatus.OK);
	}

}
