package com.xpto.cities.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.xpto.cities.exception.CitiesFileException;
import com.xpto.cities.exception.CitiesFileNotFoundException;
import com.xpto.cities.property.CitiesFileProperties;


@Service
public class CitiesFileService {
	
	 private final Path citiesFileLocation;

	    @Autowired
	    public CitiesFileService(CitiesFileProperties citiesFileProperties) {
	        this.citiesFileLocation = Paths.get(citiesFileProperties.getUploadDir())
	                .toAbsolutePath().normalize();
	        try {
	            Files.createDirectories(this.citiesFileLocation);
	        } catch (Exception ex) {
	            throw new CitiesFileException("Não foi possível criar o diretório onde os arquivos enviados serão armazenados.", ex);
	        }
	    }

	    public String storeFile(MultipartFile file) {
	        // Normalize file name
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

	        try {
	            // Check if the file's name contains invalid characters
	            if(fileName.contains("..")) {
	                throw new CitiesFileException("Desculpa! O nome do arquivo contém um caminho inválido " + fileName);
	            }

	            // Copy file to the target location (Replacing existing file with the same name)
	            Path targetLocation = this.citiesFileLocation.resolve(fileName);
	            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
	            return fileName;
	        } catch (IOException ex) {
	            throw new CitiesFileException("Não foi possível armazenar o arquivo" + fileName + ". Por favor, tente novamente!", ex);
	        }
	    }

	    public Resource loadFileAsResource(String fileName) {
	        try {
	            Path filePath = this.citiesFileLocation.resolve(fileName).normalize();
	            Resource resource = new UrlResource(filePath.toUri());
	            if(resource.exists()) {
	                return resource;
	            } else {
	                throw new CitiesFileNotFoundException("Arquivo não encontrado" + fileName);
	            }
	        } catch (MalformedURLException ex) {
	            throw new CitiesFileNotFoundException("Arquivo não encontrado " + fileName, ex);
	        }
	    }

}
