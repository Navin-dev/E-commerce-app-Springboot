package com.ecommerce.project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //filename of the current /original file
        String originalFileName = file.getOriginalFilename();
        //generate a unique file name
        String randomId = UUID.randomUUID().toString();
        //oirginl fil name mat.jpg ; random id -> 1234 then after it trims and gives like 1234.jpg
        String fileName = randomId.concat(originalFileName.substring(originalFileName.lastIndexOf('.')));
        String filePath = path + File.separator + fileName;


        //check if path exist and create
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        //upload to the server

        Files.copy(file.getInputStream(), Paths.get(filePath));
        //returning the file name
        return fileName;

    }
}

