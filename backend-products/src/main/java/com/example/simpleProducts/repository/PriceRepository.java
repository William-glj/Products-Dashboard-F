package com.example.simpleProducts.repository;

import com.example.simpleProducts.entity.PriceJPA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends CrudRepository<PriceJPA,Long> {
}
    