package com.example.simpleProducts.service;

import com.example.simpleProducts.classBox.Category;
import com.example.simpleProducts.entity.ProductsJPA;
import com.example.simpleProducts.entity.UsersJPA;
import com.example.simpleProducts.repository.ProductsRepository;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ProductsService {

    @Autowired
    private ProductsRepository object;
      /*Operaciones CRUD
    Todo aún sin probar
    Tareas - ¿Acabadas?
    Create - Si
    Read - Si
    Update - Si
    Delete - No funcional, necesito borrar también la FK en los demás pedidos
     */

    public ResponseEntity<ProductsJPA> createProduct (ProductsJPA value){
        return ResponseEntity.ok(object.save(value));
    }
    public List<ProductsJPA> readAll(){
        return List.copyOf((Collection<? extends ProductsJPA>) object.findAll());
    }
    public Optional<ProductsJPA> readById(Long isbn){
        return object.findById(isbn);
    }
    public ResponseEntity<ProductsJPA> updateProducts (ProductsJPA values){

        return object.findById(values.getIsbn()).map(

                newObject -> {
                    newObject.setProductName(values.getProductName());
                    newObject.setInformation(values.getInformation());
                    newObject.setCategory(values.getCategory());

                   ProductsJPA updateCase = object.save(newObject);
                   return ResponseEntity.ok(updateCase);
                }).orElseGet(()-> ResponseEntity.notFound().build());
    }

    public ResponseEntity<ProductsJPA>deleteById(Long id){

        object.deleteById(id);
        return ResponseEntity.ok().build();




    }







}
