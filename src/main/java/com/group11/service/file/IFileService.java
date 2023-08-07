package com.group11.service.file;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
	String uploadImage(MultipartFile image) throws IOException;

}
