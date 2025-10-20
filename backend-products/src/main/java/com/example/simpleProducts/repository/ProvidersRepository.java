package com.example.simpleProducts.repository;

import com.example.simpleProducts.entity.ProvidersJPA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvidersRepository extends CrudRepository<ProvidersJPA,Integer> {
}
