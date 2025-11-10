package com.example.simpleProducts.repository;

import com.example.simpleProducts.entity.ProductsJPA;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends CrudRepository<ProductsJPA,Long> {


}
