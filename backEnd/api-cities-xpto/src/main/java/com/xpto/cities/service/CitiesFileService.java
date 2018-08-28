package com.xpto.cities.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.xpto.cities.exception.CitiesFileException;
import com.xpto.cities.model.CityModel;
import com.xpto.cities.property.CitiesFileProperties;

@Service
public class CitiesFileService {

	private final Path citiesFileLocation;
	
	@Autowired
	private CityService cityService;

	@Autowired
	public CitiesFileService(CitiesFileProperties citiesFileProperties) {
		this.citiesFileLocation = Paths.get(citiesFileProperties.getUploadDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.citiesFileLocation);
		} catch (Exception ex) {
			throw new CitiesFileException(
					"Não foi possível criar o diretório onde os arquivos enviados serão armazenados.", ex);
		}
	}

	public String storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename().trim());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new CitiesFileException("Desculpa! O nome do arquivo contém um caminho inválido " + fileName);
			}
			if (!file.getContentType().equals("text/csv")) {
				throw new CitiesFileException(
						"Desculpa! O tipo do arquivo não é suportado, insira arquivos .csv - " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.citiesFileLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			// repository

			List<CityModel> cities = getCitiesByFile(file);
			cityService.saveAllCities(cities);
			Files.delete(this.citiesFileLocation.resolve(file.getOriginalFilename().trim()).normalize());
			String message = cities.size() + " Cidades Inseridas com sucesso";
			return message;
		} catch (IOException ex) {
			throw new CitiesFileException(
					"Não foi possível armazenar o arquivo" + fileName + ". Por favor, tente novamente!", ex);
		}
	}

	public List<CityModel> getCitiesByFile(MultipartFile file) {
		List<CityModel> cities = null;
		try (Stream<String> lines = Files
				.lines(this.citiesFileLocation.resolve(file.getOriginalFilename().trim()).normalize())) {
			cities = lines.skip(1).map(mapToCity).collect(Collectors.toList());
		} catch (Exception e) {
			throw new CitiesFileException(
					"Desculpe, não foi possivel converter o arquivo, verifique e tente novamente!");
		}
		return cities;
	}

	public static Function<String, CityModel> mapToCity = (line) -> {
		String[] c = line.split(",");
		return new CityModel(new Long(c[0]), c[1], c[2], c[3], new BigDecimal(c[4]), new BigDecimal(c[5]), c[6], c[7],
				c[8], c[9]);
	};

}