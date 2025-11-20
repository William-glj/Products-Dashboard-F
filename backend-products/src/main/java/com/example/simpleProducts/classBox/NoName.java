package com.example.simpleProducts.classBox;



import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

//A lo mejor esta clase es innecesaria.
//REVISAR!!! Y TESTEAR CUANDO ESTE LA INTERFAZ
public class NoName {

    private  String path;



    public NoName (){



    }

    //Métodos extras
    public byte[] convertImageToBinary(MultipartFile filePath) throws IOException {
        return  Files.readAllBytes((Path) filePath);
    }
    //Métodos extras
    public String  convertBinaryToBase64(byte[] data) throws IOException {
        return  Base64.getEncoder().encodeToString(data);
    }

    public static void main(String[] args) throws Exception {


        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/ProductsDB?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("toor");

        NoName nm = new NoName();

        // Obtener conexión
        try (Connection conn = dataSource.getConnection()) {
            // Leer archivo como bytes
            Path pt = Paths.get("C:\\Users\\guill\\IdeaProjects\\simpleProducts\\src\\main\\resources\\sofa.jpg");
            byte[] imageBytes = Files.readAllBytes(pt);

            // Insertar en la tabla picture
            String sql = "INSERT INTO picture (isbn_picture, image) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, 9780596009205L);
                ps.setBytes(2, imageBytes);
                ps.executeUpdate();
            }



            /*
            PreparedStatement ps = conn.prepareStatement("select * from picture where isbn_picture = "+ 9780596009205L);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    long isbn = rs.getLong("isbn_picture");
                     imageBytes = rs.getBytes("image");

                    System.out.println("ISBN: " + isbn);
                    System.out.println("Imagen recuperada, tamaño: " + nm.convertBinaryToBase64(imageBytes) + " bytes");
                }

            }
             */

        }













    }















}
