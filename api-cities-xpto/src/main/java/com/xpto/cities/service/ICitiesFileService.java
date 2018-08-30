package com.xpto.cities.service;

import org.springframework.web.multipart.MultipartFile;

import com.xpto.cities.payload.FileResponse;

public interface ICitiesFileService {
	
	 FileResponse storeFile(MultipartFile file);
}
