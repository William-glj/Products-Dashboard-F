package com.example.simpleProducts.controller;

import com.example.simpleProducts.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequestMapping("/api/products/")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductsService productsService;




    //Obtener todos los productos presentes en la entidad.
    //Ruta: localhost:8080/api/products/all
    @GetMapping("all")
    public ResponseEntity<?> getProducts (){

        if (productsService.readAll().isEmpty()) {
            System.out.println("Mala ejecución");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha encontrado");
        }

        System.out.println(productsService.readAll());

        return ResponseEntity.ok(Map.of("List",productsService.readAll()));

    }




    //Buscar un producto en específico.
    //Ruta: localhost:8080/api/products/searchOne
    @GetMapping("searchOne")
    public ResponseEntity<?> findOneProduct (Long ISBNEX){

        if (productsService.readById(ISBNEX).isPresent()) {

            return ResponseEntity.ok(productsService.readById(ISBNEX));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha encontrado");




    }
}
