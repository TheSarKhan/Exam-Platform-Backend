package com.exam.examapp.service.impl;

import com.exam.examapp.exception.custom.DirectoryException;
import com.exam.examapp.exception.custom.FileException;
import com.exam.examapp.service.interfaces.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Slf4j
@Service
public class LocalFileServiceImpl implements FileService {
    @Override
    public String uploadFile(String directory, MultipartFile file) {
        Path uploadDir = Paths.get(directory);
        if (!Files.exists(uploadDir)) {
            try {
                Files.createDirectories(uploadDir);
            } catch (IOException e) {
                throw new DirectoryException("Directory cannot created. Io exception: " + e.getMessage());
            }
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = uploadDir.resolve(fileName);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileException("File cannot be uploaded. Io exception: " + e.getMessage());
        }

        return fileName;
    }

    @Override
    public void deleteFile(String directory, String fileName) {
        Path uploadDir = Paths.get(directory);
        Path filePath = uploadDir.resolve(fileName);

        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new FileException("File cannot be deleted. Io exception: " + e.getMessage());
        }
    }
}
