package com.example.simpleProducts.repository;

import com.example.simpleProducts.classBox.Rol;
import com.example.simpleProducts.entity.UsersJPA;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<UsersJPA,Integer> {


    @Query("SELECT COUNT(l) FROM LogJPA l WHERE l.user.idUser = :id")
    Integer countUserLogs(@Param("id") Integer id);


    UsersJPA findByCompanyMail(String mail);
    /*
    @Query("SELECT u FROM UsersJPA u WHERE u.companyMail = :mail")
    UsersJPA userExist(@Param("mail") String mail);
    */
    UsersJPA findByRol(Rol rol);


}
