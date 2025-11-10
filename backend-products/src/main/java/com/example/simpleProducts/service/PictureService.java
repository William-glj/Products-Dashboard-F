package com.example.simpleProducts.service;

import com.example.simpleProducts.entity.PictureJPA;
import com.example.simpleProducts.entity.ProductsJPA;
import com.example.simpleProducts.repository.PictureRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PictureService {
      /*Operaciones CRUD
    Todo aún sin probar
    Tareas - ¿Acabadas?
    Create - Si
    Read -
    Update -
    Delete -
     */

    @Autowired
    private PictureRepository pictureRepository;

    //Métodos extras
    public byte[] convertImageToBinary(MultipartFile filePath) throws IOException {
        return  Files.readAllBytes((Path) filePath);
    }
    //Métodos extras
    public String  convertBinaryToBase64(byte[] data) throws IOException {
        return  Base64.getEncoder().encodeToString(data);
    }


    public ResponseEntity<PictureJPA> createImageFromPicture(MultipartFile filePath, Long isbn) throws IOException {
        PictureJPA newPicture = new PictureJPA(isbn, convertImageToBinary(filePath));
        return ResponseEntity.ok(pictureRepository.save(newPicture));
    }









}
