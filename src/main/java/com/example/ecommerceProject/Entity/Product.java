package com.example.ecommerceProject.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name", nullable = false, length = 200)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private int productPrice;

    @Column(nullable = false)
    private int stock;

    // The Soft Delete Flag (Default value true)
    @Column(name = "is_active")
    private boolean isActive = true;

    // Ek product bohot saare orders me ho sakta hai.
    // WARNING: Yahan CascadeType.ALL mat lagana!
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToOne(mappedBy = "product" )
    private ProductDetails productDetails;
}
