package com.example.simpleProducts.repository;

import com.example.simpleProducts.entity.PictureJPA;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends CrudRepository<PictureJPA,Long> {
    @Query("SELECT l.image FROM PictureJPA l WHERE l.product.isbn = :isbd_ex")
    List<String> collectImage(@Param("isbd_ex") Long isbd_ex);


}
