package com.example.simpleProducts.classBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;


//A lo mejor esta clase es innecesaria.
//REVISAR!!! Y TESTEAR CUANDO ESTE LA INTERFAZ
public class NoName {

    private  String path;

    public NoName (String pathEx){

        this.path = pathEx;

    }

    public byte[] convertImageToBinary() throws IOException {
        File imageFile = new File(path);
        byte[] imageBytes = new byte[(int) imageFile.length()];

        try (FileInputStream fis = new FileInputStream(imageFile)) {
            fis.read(imageBytes);

        }


        return imageBytes;
    }


    // Convierte binario a imagen y la guarda
    public void convertBinaryToImage(byte[] data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(data);
        }
    }

    public static void main(String[] args) {

        try {
          NoName nmA = new NoName("C:\\Users\\guill\\IdeaProjects\\simpleProducts\\src\\main\\resources\\sofa.jpg");
          NoName nmB = new NoName("C:\\Users\\guill\\IdeaProjects\\simpleProducts\\src\\main\\resources\\copiaSofa.jpg");
          byte[] example = nmA.convertImageToBinary();//Recojo los bytes
            System.out.println("Imagen convertida a binario. Tamaño: " + example.length + " bytes");

            String binary = new BigInteger(1, example).toString(2);//Los convierto en binario

            System.out.println(binary);

            nmB.convertBinaryToImage(example);


        } catch (IOException e) {
            System.err.println("Error al leer la imagen: " + e.getMessage());
        }
    }









}
