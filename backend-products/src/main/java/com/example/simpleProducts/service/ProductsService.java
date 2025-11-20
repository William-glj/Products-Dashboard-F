package com.example.simpleProducts.service;

import com.example.simpleProducts.entity.ProductsJPA;
import com.example.simpleProducts.repository.PictureRepository;
import com.example.simpleProducts.repository.PriceRepository;
import com.example.simpleProducts.repository.ProductsRepository;
import com.example.simpleProducts.repository.StockRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private StockRepository stockRepository;




      /*Operaciones CRUD
    Todo aún sin probar
    Tareas - ¿Acabadas?
    Create - Si
    Read - Si
    Update - Si
    Delete - si
     */

    public ResponseEntity<ProductsJPA> createProduct (ProductsJPA value){
        return ResponseEntity.ok(productsRepository.save(value));
    }


    public List<ProductsJPA> readAll(){

        return List.copyOf((Collection<? extends ProductsJPA>) productsRepository.findAll());

    }


    public List<Map<String, Object>> productAddImage() {

        Iterable<ProductsJPA> products = productsRepository.findAll();

        List<Map<String, Object>> result = new ArrayList<>();


        //foreach donde un product/producto vacío recorre cada products/producto de la lista.
        for (ProductsJPA product : products) {
            Map<String, Object> map = new HashMap<>();
            map.put("isbn", product.getIsbn());
            map.put("productName", product.getProductName());
            map.put("information", product.getInformation());
            map.put("category", product.getCategory());

            // Obtener imágenes asociadas
            byte [] images = pictureRepository.getBlob(product.getIsbn());

            System.out.println("---------- IMAGEN ------------");
            System.out.println( Base64.getEncoder().encodeToString(images));
            System.out.println("----------***FIN***------------");



            map.put("images", Base64.getEncoder().encodeToString(images) );

            result.add(map);
        }

        return result;
    }



    public Optional<ProductsJPA> readById(Long isbn){
        return productsRepository.findById(isbn);
    }
    public ResponseEntity<ProductsJPA> updateProducts (ProductsJPA values){

        return productsRepository.findById(values.getIsbn()).map(

                newObject -> {
                    newObject.setProductName(values.getProductName());
                    newObject.setInformation(values.getInformation());
                    newObject.setCategory(values.getCategory());

                   ProductsJPA updateCase = productsRepository.save(newObject);
                   return ResponseEntity.ok(updateCase);
                }).orElseGet(()-> ResponseEntity.notFound().build());
    }

    public ResponseEntity<?>deleteById(Long isbn){
        if (productsRepository.findById(isbn).isPresent())
        if (productsRepository.countProductsInAnotherTable(isbn) == 1){

            pictureRepository.deleteById(isbn);
            stockRepository.deleteById(isbn);
            priceRepository.deleteById(isbn);
            productsRepository.deleteById(isbn);

            return ResponseEntity.ok().build();

       }
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha encontrado el producto: " +isbn);
    }







}
