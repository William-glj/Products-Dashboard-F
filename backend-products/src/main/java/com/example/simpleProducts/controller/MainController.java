package com.example.simpleProducts.controller;


import com.example.simpleProducts.classBox.Rol;
import com.example.simpleProducts.classBox.UserDTO;
import com.example.simpleProducts.entity.UsersJPA;
import com.example.simpleProducts.service.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class MainController {

    /*
    Problemas intercomunicando Spring boot en localhost:8080
    con React en localhost:3000 / 3030
     */


    @Autowired
    private UsersService usersService;

    /* Borrar a futuro
    @Autowired
    private static String userName;
    */

    //"/api/"+ userName +"/allUsers"
    //Obtención de usuarios bajo control de Rol.
    //Ruta: localhost:8080/api/User/allUsers/vone
    @GetMapping("api/User/allUsers/vone")
    public ResponseEntity<?> getAllUsersByRolControl (@RequestParam Rol rol){
        if (rol.equals(Rol.Administrador)){
            return ResponseEntity.ok(Map.of("ListUsers",usersService.readAllUsers()));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "No tienes autorización para esta función"));
    }





/*

    //Obtención de los usuarios presentes.
    //Clase restringida por SecurityClass
    //Ruta: localhost:8080/api/User/allUsers/vtwo
    @GetMapping("api/User/allUsers/vtwo")
    public ResponseEntity<?> getAllUsersBySecurityControl (){

            return ResponseEntity.ok(Map.of("ListUsers",usersService.readAllUsers()));

    }


*/



    //Verificación de las credenciales de usuario.
    // Ruta: localhost:8080/api/User/verifierUser
    // Parámetros email y contraseña.
    @GetMapping("api/User/verifierUser")
    public ResponseEntity<?> exampleNoName(@RequestParam("mail") String mail,
                                           @RequestParam("password") String password)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Optional<UsersJPA> newObject = usersService.verifierPassword(mail, password);

        if (newObject.isPresent()) {
            UserDTO userJS = new UserDTO();

            userJS.setFirstName(newObject.get().getFirstName());
            userJS.setCompanyMail((newObject.get().getCompanyMail()));
            userJS.setRol(newObject.get().getRol().toString());

            System.out.println("Ejecución prevista");
            return ResponseEntity.ok(Map.of(
                    "firstName", userJS.getFirstName(),
                    "email", userJS.getCompanyMail(),
                    "rol", userJS.getRol()
            ));

        }
        System.out.println("Error de formulario");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Credenciales inválidas"));
    }





















}
