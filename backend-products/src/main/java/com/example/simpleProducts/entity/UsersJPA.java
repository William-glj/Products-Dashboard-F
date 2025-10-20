package com.example.simpleProducts.entity;

import com.example.simpleProducts.classBox.Rol;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "first_name", length = 60)
    private String firstName;

    @Column(name = "last_name", length = 60)
    private String lastName;

    @Column(name = "company_mail")
    private String companyMail;

    private int age;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Column(length = 500)
    private String psswrd;

    private Integer mobile;

    public UsersJPA(String firstName, String lastName, Integer age, Rol rol, String psswrd){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.rol = rol;
        this.psswrd = psswrd;
    }
    public UsersJPA(String mail, String psswrd){
        this.companyMail = mail;
        this.psswrd = psswrd;
    }









}
