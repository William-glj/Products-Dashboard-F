package com.example.simpleProducts.repository;

import com.example.simpleProducts.entity.StockJPA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository <StockJPA, Long> {
}
