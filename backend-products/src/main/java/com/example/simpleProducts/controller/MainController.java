package com.example.simpleProducts.controller;


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
    @Autowired
    private UsersService usersService;

    private static String userName;
    /*
    @GetMapping()
    public String redirectToMainPath() {
        return "redirect:api/verifierUser";
    }

    */



/*
    @GetMapping("/api")
    public String beforeUseForm (){





    }*/

    //Path: /api/verifierUser
    @GetMapping("/api/verifierUser")
    public String exampleNoName(@RequestParam("mail") String mail,
                                           @RequestParam("password") String password)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Optional<UsersJPA> newObject = usersService.verifierPassword(mail, password);

        if (newObject.isPresent()) {
            String userName = newObject.get().getFirstName();
            return "redirect:/api/" + userName +"/MainHome";
        }

        return  "<html>" +
                "<body>" +
                "<script>" +
                "alert('Credenciales inválidas. Intenta de nuevo.');" +
                "window.location.href = '/api/verifierUser';" +
                "</script>" +
                "</body>" +
                "</html>";
    }















}
