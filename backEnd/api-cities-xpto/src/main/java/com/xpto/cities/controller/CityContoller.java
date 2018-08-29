package com.xpto.cities.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.xpto.cities.payload.DefaultResponse;
import com.xpto.cities.payload.FileResponse;
import com.xpto.cities.service.ICitiesFileService;
import com.xpto.cities.service.ICityService;

@RestController
public class CityContoller {

	@Autowired
	private ICitiesFileService citiesFileService;

	@Autowired
	private ICityService cityService;

	/*
	 * Ler o arquivo CSV das cidades para a base de dados
	 */
	@PostMapping("/upload")
	public ResponseEntity<FileResponse> uploadFile(@RequestParam("file") MultipartFile file) {
		return new ResponseEntity<FileResponse>(citiesFileService.storeFile(file), HttpStatus.ACCEPTED);
	}

	/*
	 * Retornar somente as cidades que são capitais ordenadas por nome
	 */
	@GetMapping("/capital")
	public ResponseEntity<DefaultResponse> getCapitals() {
		DefaultResponse response = cityService.findCapitals();
		return new ResponseEntity<DefaultResponse>(response, response.getStatus());

	}

	/*
	 * Retornar o nome do estado com a maior e menor quantidade de cidades e a
	 * quantidade de cidades;
	 */
	@GetMapping("/largest-smallest-ufs")
	public ResponseEntity<DefaultResponse> getLargestSmallestUfs() {
		DefaultResponse response = cityService.getLargestSmallestUfs();
		return new ResponseEntity<DefaultResponse>(response, response.getStatus());

	}

	/*
	 * Retornar a quantidade de cidades por estado;
	 */
	@GetMapping("/number-cities-uf")
	public ResponseEntity<DefaultResponse> getNumberCitiesUf() {
		DefaultResponse response = cityService.getNumberCitiesUf();
		return new ResponseEntity<DefaultResponse>(response, response.getStatus());
	}

	/*
	 * Obter os dados da cidade informando o id do IBGE
	 */
	@GetMapping("/city/{ibgeId}")
	public ResponseEntity<DefaultResponse> getCityByIbge(@PathVariable("ibgeId") Long ibgeId) {
		DefaultResponse response = cityService.findByIbgeId(ibgeId);
		return new ResponseEntity<DefaultResponse>(response, response.getStatus());
	}

	/*
	 * Retornar o nome das cidades baseado em um estado selecionado;
	 */
	@GetMapping("/uf/{uf}")
	public ResponseEntity<DefaultResponse> getCitiesByUf(@PathVariable("uf") String uf) {
		DefaultResponse response = cityService.getCitiesByUf(uf.toUpperCase());
		return new ResponseEntity<DefaultResponse>(response, response.getStatus());
	}

	/*
	 * Permitir adicionar uma nova Cidade
	 */
	@PostMapping("/city")
	public ResponseEntity<DefaultResponse> saveCity(@Valid @RequestBody CityModel city) {
		DefaultResponse response = cityService.saveCity(city);
		return new ResponseEntity<DefaultResponse>(response, response.getStatus());
	}

	/*
	 * Permitir deletar uma cidade;
	 */
	@DeleteMapping("/city/{ibgeId}")
	public ResponseEntity<DefaultResponse> deleteCity(@PathVariable("ibgeId") Long ibgeId) {
		DefaultResponse response = cityService.deleteCity(ibgeId);
		return new ResponseEntity<DefaultResponse>(response, response.getStatus());
	}

	/*
	 * Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string
	 * para filtrar. retornar assim todos os objetos que contenham tal string;
	 * http://localhost:8080/filter?a=ibgeId&b=1200336 (required = false)
	 */

	@GetMapping("/filter")
    public Page<CityModel> list(@RequestParam Map<String, String> filters,
                             @RequestParam(defaultValue = "0") Integer page,
                             @RequestParam(defaultValue = "10") Integer size) {
        return cityService.list(filters,  PageRequest.of(page, size));
    }

	/*
	 * Retornar a quantidade de registro baseado em uma coluna. Não deve contar
	 * itens iguais;
	 */

	// public

	/*
	 * Retornar a quantidade de registros total
	 */
	@GetMapping("/total-records")
	public ResponseEntity<DefaultResponse> getTotalRecords() {
		return new ResponseEntity<DefaultResponse>(cityService.getTotalRecords(), HttpStatus.OK);

	}

	/*
	 * Dentre todas as cidades, obter as duas cidades mais distantes uma da outra
	 * com base na localização (distância em KM em linha reta);
	 */
	@GetMapping("/greater-distance")
	public ResponseEntity<DefaultResponse> getGreaterDistance() {
		DefaultResponse response = cityService.getGreaterDistance();
		return new ResponseEntity<DefaultResponse>(response, response.getStatus());

	}
	
//	@GetMapping("/greater-distance2")
//	public ResponseEntity<DefaultResponse> getGreaterDistance2() {
//		DefaultResponse response = cityService.getGreaterDistance2();
//		return new ResponseEntity<DefaultResponse>(response, response.getStatus());
//
//	}

}
