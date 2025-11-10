package com.example.simpleProducts.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "picture")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PictureJPA {
    //Check - si
    @Id
    @Column(name = "isbn_picture")
    private Long isbnPicture;

    @Lob
    @Column(nullable = false)
    private byte[] image;

    @OneToOne
    @JoinColumn(name = "isbn_picture",
            referencedColumnName = "isbn",
            insertable = false, updatable = false)
    private ProductsJPA product;



    public PictureJPA (Long isbnPictures, byte [] images){

        this.isbnPicture = isbnPictures;
        this.image = images;

    }



}
