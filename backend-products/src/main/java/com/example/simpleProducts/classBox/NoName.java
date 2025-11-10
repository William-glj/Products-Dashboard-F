package com.example.simpleProducts.classBox;



import java.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;


//A lo mejor esta clase es innecesaria.
//REVISAR!!! Y TESTEAR CUANDO ESTE LA INTERFAZ
public class NoName {

    private  String path;

    public NoName (String pathEx){

        this.path = pathEx;

    }

    public byte[] convertImageToBinary() throws IOException {

        Path filePath = Path.of(this.path);
        return  Files.readAllBytes(filePath);
    }


    // Convierte binario a imagen y la guarda
    public String  convertBinaryToBase64(byte[] data) throws IOException {

        return  Base64.getEncoder().encodeToString(data);

    }


          //NoName nmA = new NoName("C:\\Users\\guill\\IdeaProjects\\simpleProducts\\src\\main\\resources\\sofa.jpg");
          //NoName nmB = new NoName("C:\\Users\\guill\\IdeaProjects\\simpleProducts\\src\\main\\resources\\copiaSofa.jpg");


















}
