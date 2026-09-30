package com.example.ecommerceProject.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String color;

    @Column
    private String material;

    @Column
    private Double weight;

    @Column(name = "warranty_info")
    private String warrantyInfo;

    @OneToOne
    @JoinColumn(name = "product_id" , nullable = false)
    private Product product;
}

//id INT AUTO_INCREMENT PRIMARY KEY,
//        --     product_id INT UNIQUE NOT NULL,
//        --     description TEXT,
//--     brand VARCHAR(100),
//--     category VARCHAR(100),
//--     color VARCHAR(50),
//--     material VARCHAR(100),
//--     weight DECIMAL(5,2),
//--     warranty_info VARCHAR(255),
