package com.example.simpleProducts.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class StockJPA {

    @Id
    @Column(name = "isbn_stock")
    private Long isbnStock;

    @Column(name = "stock_available", nullable = false)
    private Integer stockAvailable = 0;

    @Column(name = "stock_min", nullable = false)
    private Integer stockMin = 0;

    @Column(length = 200)
    private String location;

    @Column(length = 60, name = "id_providers")
    private Integer maker;

    @OneToOne
    @JoinColumn(name = "isbn_stock",
            referencedColumnName = "isbn",
            insertable = false, updatable = false)
    private ProductsJPA product;

    @OneToOne
    @JoinColumn(name = "id_providers",
            referencedColumnName = "id",
            insertable = false, updatable = false)
    private ProvidersJPA providers;

    // Getters and setters




}

