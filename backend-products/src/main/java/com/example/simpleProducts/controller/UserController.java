package com.example.simpleProducts.controller;

import com.example.simpleProducts.classBox.Rol;
import com.example.simpleProducts.classBox.UserDTO;
import com.example.simpleProducts.entity.UsersJPA;
import com.example.simpleProducts.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    /*
         Problemas intercomunicando Spring boot en localhost:8080
         con React en localhost:3000 / 3030
         Dividir las ramas de los controlladores
          */
    @Autowired
    private UsersService usersService;



    //Verificación de las credenciales de usuario.
    // Ruta: localhost:8080/api/user/verifyUser
    // Parámetros email y contraseña.
    @PostMapping("verifyUser")
    public ResponseEntity<?> verifyUser(@RequestParam("mail") String mail,
                                        @RequestParam("password") String password)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Optional<UsersJPA> newObject = usersService.verifierPassword(mail, password);

        if (newObject.isPresent()) {
            UserDTO userOnline =
                    new UserDTO(
                            newObject.get().getFirstName()
                            ,newObject.get().getCompanyMail()
                            ,newObject.get().getRol().toString()
                    );

            System.out.println("Ejecución prevista");
            return ResponseEntity.ok(Map.of(
                    "userOnline",userOnline
            ));
        }
        System.out.println("Error de formulario");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Credenciales inválidas"));
    }




    //Obtención de usuarios bajo control de Rol.
    //Ruta: localhost:8080/api/user/allUsers
    @GetMapping("allUsers")
    public ResponseEntity<?> getAllUsersByRolControl (@RequestParam Rol rol){
        if (rol.equals(Rol.Administrador)){
            return ResponseEntity.ok(Map.of("ListUsers",usersService.readAllUsers()));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "No tienes autorización para esta función"));
    }


    //Obtención de un conjunto de usuarios por rol.
    //Ruta: localhost:8080/api/user/rolUser
    @GetMapping("rolUser")
    public ResponseEntity<?> getAllUserOFSpecicallyRol (@RequestParam Rol rol, @RequestParam Rol rolEx ){
        if (rol.equals(Rol.Administrador)){
            return ResponseEntity.ok(Map.of("ListUsers",usersService.readOnlyRolUsers(rolEx)));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "No tienes autorización para esta función"));
    }


    //Obtención de un usuario en específico.
    //Ruta: localhost:8080/api/user/searchUser
    @GetMapping("searchUser")
    public ResponseEntity<?> findOneUser (@RequestParam Rol rol, @RequestParam Integer id){
        if (rol.equals(Rol.Administrador)){
            return ResponseEntity.ok(usersService.readUserById(id));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "No tienes autorización para esta función"));
    }



    //Crear un nuevo usuario en la entidad users/UsersJPA.
    //Ruta: localhost:8080/api/user/create
    @PostMapping("create")
    public ResponseEntity<?> insertNewUser (@RequestParam Rol rol,@RequestBody UsersJPA userBody){
        if (rol.equals(Rol.Administrador)){
            usersService.createUserByObj(userBody);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "No tienes autorización para esta función"));
    }


    //Crear un nuevo usuario en la entidad users/UsersJPA.
    //Ruta: localhost:8080/api/user/createBy
    @PostMapping("createBy")
    public ResponseEntity<?> insertOtherUser (@RequestParam Rol rol
            ,@RequestParam String nameEx
            ,@RequestParam String lastNameEx
            ,@RequestParam Integer ageEx
            ,@RequestParam Rol rolEx
            ,@RequestParam String psswrdEx

    ){
        if (rol.equals(Rol.Administrador)){

            usersService.createUserByParams(nameEx,lastNameEx,ageEx,rolEx,psswrdEx);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "No tienes autorización para esta función"));
    }

}
