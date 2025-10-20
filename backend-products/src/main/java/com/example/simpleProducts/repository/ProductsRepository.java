package com.example.simpleProducts.repository;

import com.example.simpleProducts.entity.ProductsJPA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends CrudRepository<ProductsJPA,Long> {
}
