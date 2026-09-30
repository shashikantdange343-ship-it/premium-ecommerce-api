package com.example.ecommerceProject.Repository;

import com.example.ecommerceProject.Entity.Product;
import com.example.ecommerceProject.Entity.ProductDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails , Integer> {
    Page<ProductDetails> findByCategoryContainingIgnoreCase(String category , Pageable pageable);
}
