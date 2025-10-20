package com.example.simpleProducts.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "price")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PriceJPA {

    @Id
    @Column(name = "isbn_price")
    private Long isbnPrice;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(precision = 10, scale = 2)
    private BigDecimal sale = BigDecimal.valueOf(0.00);

    @Column(precision = 5, scale = 2)
    private BigDecimal taxes = BigDecimal.valueOf(0.00);

    @Column(length = 10)
    private String coin = "USD";

    @OneToOne
    @JoinColumn(name = "isbn_price",
            referencedColumnName = "isbn",
            insertable = false, updatable = false)
    private ProductsJPA product;


}
