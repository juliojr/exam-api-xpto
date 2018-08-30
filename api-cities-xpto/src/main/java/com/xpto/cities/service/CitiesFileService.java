package com.xpto.cities.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.xpto.cities.exception.CitiesFileException;
import com.xpto.cities.model.CityModel;
import com.xpto.cities.payload.FileResponse;
import com.xpto.cities.property.CitiesFileProperties;

@Service
public class CitiesFileService implements ICitiesFileService {

	private final Path citiesFileLocation;

	@Autowired
	private ICityService cityService;

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

	public FileResponse storeFile(MultipartFile file) {
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

			// trasform file in a List
			List<CityModel> cities = cityService.getCitiesByFile(file, this.citiesFileLocation);

			// Save
			// cityService.getCityRepository().deleteAll();
			cityService.saveAllCities(cities);

			// Delete file
			Files.delete(this.citiesFileLocation.resolve(file.getOriginalFilename().trim()).normalize());

			FileResponse response = new FileResponse(HttpStatus.ACCEPTED, "Uploaded And Saved Successful",
					file.getContentType(), file.getSize());
			return response;
		} catch (Exception ex) {
			throw new CitiesFileException(ex.getMessage(), ex);
		}
	}

}
