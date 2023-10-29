package com.example.esdemo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilestorageService {

    private final Path root = Path.of("F:\\Project\\Learning\\Java\\elasticsearch\\ES-demo\\uploads");

//    init folder
    public void init() {
        try {
            if (!root.toFile().exists()) {
                root.toFile().mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    save file
    public String saveFile(MultipartFile file) {
        try {
            init();
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return file.getOriginalFilename();
    }

//    get content file
    public String getContentFile(String fileName) {
        try {
            return Files.readString(this.root.resolve(fileName));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
