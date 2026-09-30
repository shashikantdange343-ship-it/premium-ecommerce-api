package com.example.ecommerceProject.Repository;

import com.example.ecommerceProject.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
   Optional<Product> findByProductName(String name);

   @EntityGraph(attributePaths = {"productDetails"})
   @Query("SELECT p FROM Product p " +
           "WHERE LOWER(p.productDetails.category) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(p.productDetails.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
   Page<Product> searchAdvanced(@Param("keyword") String keyword, Pageable pageable);

}
