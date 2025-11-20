package com.example.simpleProducts.repository;

import com.example.simpleProducts.entity.ProductsJPA;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends CrudRepository<ProductsJPA,Long> {


    @Query(value = "SELECT COUNT(*) " +
            "FROM products p " +
            "LEFT JOIN picture pic ON p.isbn = pic.isbn_picture " +
            "JOIN price pr ON p.isbn = pr.isbn_price " +
            "JOIN stock st ON p.isbn = st.isbn_stock " +
            "WHERE p.isbn = :isbn_product",
            nativeQuery = true)
    Long countProductsInAnotherTable(@Param("isbn_product") Long isbn_product);






}
