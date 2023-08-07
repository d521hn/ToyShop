package com.group11.service.file;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.group11.utils.FileManager;

@Service
public class FileService implements IFileService {

	private FileManager fileManager = new FileManager();

	@Value("${myapp.image-folder}")
	private String linkFolder;

	@Override
	public String uploadImage(MultipartFile image) throws IOException {

		String nameImage = new Date().getTime() + "." + fileManager.getFormatFile(image.getOriginalFilename());

		String path = linkFolder + "\\" + nameImage;

		fileManager.createNewMultiPartFile(path, image);

		return nameImage;
	}
}
