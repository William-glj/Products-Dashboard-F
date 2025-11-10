package com.example.simpleProducts.controller;

import com.example.simpleProducts.entity.PictureJPA;
import com.example.simpleProducts.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/picture/")
@CrossOrigin(origins = "http://localhost:3000")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    //Probarlo con el cliente
    //localhost:8080/api/picture/createImage
    @PostMapping("/createImage")
    public ResponseEntity<?> createImage(@RequestParam("isbn") Long isbn,
                                         @RequestParam("file") MultipartFile file) throws IOException {

        byte[] imageBytes = file.getBytes(); // convertir el archivo a byte[]
        PictureJPA newPicture = new PictureJPA(isbn, imageBytes);

        return ResponseEntity.ok(newPicture);
    }











}
