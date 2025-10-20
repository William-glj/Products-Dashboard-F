package com.example.simpleProducts.repository;

import com.example.simpleProducts.entity.LogJPA;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LogRepository extends CrudRepository<LogJPA,Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM LogJPA  l WHERE l.user.idUser = :id")
    void deleteAllByUserId(@Param("id") Integer id);


}
