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

    //Las consultas con la anotación de Spring funcionan bajo la lógica de las entidades de java.
    //SELECT nombrenuevodelaentidad=(l) CLASEDEJAVA=(LogJPA) (l) WHERE (l).ELEMENTOJAVA = Atributo que envies.
    //l.user.idUser referencia (l) a los datos de LogJPA (user) al atributo de la clase LogJPA (idUser) a la id de la clase UsersJPA.
    @Query("SELECT COUNT(l) FROM LogJPA l WHERE l.user.idUser = :id")
    Integer countUserLogs(@Param("id") Integer id);

    /*
    @Query("SELECT  COUNT(l) > 0 FROM UsersJPA l WHERE l.firstName = :nameEx AND l.lastName = lastNameEx AND l.companyMail = mailEx")
    boolean userMatchesAnotherUser (@Param("nameEx") String nameEx, @Param("lastNameEx") String lastNameEx, @Param("mailEx") String mailEx);
    */

    UsersJPA findByCompanyMail(String mail);
    /*
    @Query("SELECT u FROM UsersJPA u WHERE u.companyMail = :mail")
    UsersJPA userExist(@Param("mail") String mail);
    */
    UsersJPA findByRol(Rol rol);









}
