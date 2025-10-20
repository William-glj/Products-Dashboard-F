package com.example.simpleProducts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "providers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProvidersJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 80)
    private String nickname;

    @Column(length = 200)
    private String mail;

    @Column(length = 40)
    private String phone;

    private String address;

}
