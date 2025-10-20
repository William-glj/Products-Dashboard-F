package com.example.simpleProducts.entity;


import com.example.simpleProducts.classBox.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsJPA {

    @Id
    private Long isbn;

    @Column(name = "product_name")
    private String productName;

    private String information;

    @Enumerated(EnumType.STRING)
    private Category category;
}
