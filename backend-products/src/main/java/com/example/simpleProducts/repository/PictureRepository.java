package com.example.simpleProducts.repository;

import com.example.simpleProducts.entity.PictureJPA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends CrudRepository<PictureJPA,Long> {
}
