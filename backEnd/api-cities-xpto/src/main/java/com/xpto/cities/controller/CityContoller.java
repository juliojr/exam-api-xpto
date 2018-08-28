package com.xpto.cities.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xpto.cities.model.CityModel;
import com.xpto.cities.payload.CitiesFileResponse;
import com.xpto.cities.payload.CitiesResponse;
import com.xpto.cities.payload.StatesDto;
import com.xpto.cities.service.CitiesFileService;
import com.xpto.cities.service.CityService;

@RestController
public class CityContoller {

	@Autowired
	private CitiesFileService citiesFileService;

	@Autowired
	private CityService cityService;

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	/*
	 * Ler o arquivo CSV das cidades para a base de dados
	 */
	@PostMapping("/uploadFile")
	public CitiesFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		LOG.info("Uploading all cities in mongodb please wait.");
		return new CitiesFileResponse(citiesFileService.storeFile(file), file.getContentType(), file.getSize());
	}

	/*
	 * Retornar somente as cidades que são capitais ordenadas por nome
	 */
	@GetMapping("/capitals")
	public ResponseEntity<CitiesResponse> getCapitals() {
		LOG.info("Loading all capitals sorted by name.");
		List<CityModel> cities = cityService.findCapitals();
		CitiesResponse response = new CitiesResponse("capitals", cities);
		return new ResponseEntity<CitiesResponse>(response, HttpStatus.OK);
	}
	/*
	 * Retornar o nome do estado com a maior e menor quantidade de cidades e a
	 * quantidade de cidades;
	 */
	@GetMapping("/largerAndSmallerUf")
	public ResponseEntity<List<StatesDto>> getLargerAndSmallerOfUfs() {
		return new ResponseEntity<List<StatesDto>>(cityService.getLargestAndSmallestOfUfs(), HttpStatus.OK);
	}

	/*
	 * Retornar a quantidade de cidades por estado;
	 */
	@GetMapping("/qtdeCitiesUf")
	public ResponseEntity<List<StatesDto>> getQtdeCitiesUf() {
		LOG.info("Loading cities per state.");
		return new ResponseEntity<List<StatesDto>>(cityService.getQtdeCitiesUf(), HttpStatus.OK);
	}

	/*
	 * Obter os dados da cidade informando o id do IBGE
	 */
	@GetMapping("/cityByIbge/{ibgeId}")
	public ResponseEntity<Optional<CityModel>> getCityByIbge(@PathVariable("ibgeId") Long ibgeId) {
		LOG.info("Loading city by ibge_id.");
		return new ResponseEntity<Optional<CityModel>>(cityService.findById(ibgeId), HttpStatus.OK);
	}

	/*
	 * Retornar o nome das cidades baseado em um estado selecionado;
	 */
	@GetMapping("/citiesByUf/{uf}")
	public ResponseEntity<List<String>> getCitiesByUf(@PathVariable("uf") String uf) {
		LOG.info("Loading cities by uf.");
		List<String> cities = cityService.getCitiesByUf(uf);
		return new ResponseEntity<List<String>>(cities, HttpStatus.OK);
	}

	/*
	 * Permitir adicionar uma nova Cidade
	 */
	@PostMapping("/saveCity")
	public ResponseEntity<CityModel> saveCity(@Valid @RequestBody CityModel city) {
		LOG.info("saving a city");
		return new ResponseEntity<CityModel>(cityService.saveCity(city), HttpStatus.OK);
	}

	/*
	 * Permitir deletar uma cidade;
	 */
	@DeleteMapping("/deleteCity/{ibgeId}")
	public ResponseEntity<String> deleteCity(@PathVariable("ibgeId") Long ibgeId) {
		LOG.info("deleting a city");
		return new ResponseEntity<String>(cityService.deleteCity(ibgeId), HttpStatus.OK);
	}

	/*
	 * Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string
	 * para filtrar. retornar assim todos os objetos que contenham tal string;
	 */

//	@GetMapping("/citiesByColumn/{column}/{filter}")
//	public ResponseEntity<CitiesResponse> getCitiesByColumn(@PathVariable("column") String column,
//			@PathVariable("filter") String filter) {
//		LOG.info("Load cities by uf.");
//		List<CityModel> cities = cityService.getCitiesByUf(uf);
//		CitiesResponse response = new CitiesResponse("Cities of " + uf, cities);
//		return new ResponseEntity<CitiesResponse>(response, HttpStatus.OK);
//	}

	/*
	 * Retornar a quantidade de registro baseado em uma coluna. Não deve contar
	 * itens iguais;
	 */

	// public

	/*
	 * Retornar a quantidade de registros total
	 */
	@GetMapping("/totalRecords")
	public ResponseEntity<String> getTotalRecords() {
		LOG.info("Counting cities records.");
		String ret = cityService.getTotalRecords() + ": is the total of city records";
		return new ResponseEntity<String>(ret, HttpStatus.OK);

	}

	/*
	 * Dentre todas as cidades, obter as duas cidades mais distantes uma da outra
	 * com base na localização (distância em KM em linha reta);
	 */
	@GetMapping("/greaterDistance")
	public ResponseEntity<String> getGreaterDistance() {
		String ret = cityService.getGreaterDistance();
		return new ResponseEntity<String>(ret, HttpStatus.OK);

	}

}
