package com.example.ecommerceProject.Controller;

import com.example.ecommerceProject.DTOs.*;
import com.example.ecommerceProject.Services.ProductServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/Product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServices productServices;

    @GetMapping("/Products")
    public ResponseEntity<Page<ShowProductsDTO>> showProduct(@RequestParam int pageNum){
       return ResponseEntity.ok( productServices.showProducts(pageNum));
    }

    @PostMapping("/Product")
    public ResponseEntity<ShowProductsDTO> addProduct(@Valid @RequestBody AddFullProductDTO fullProduct){
       return ResponseEntity.status(HttpStatus.CREATED).body(productServices.addProducts(fullProduct.product(),fullProduct.details()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowProductDetailsDTO> showProductDetails(@PathVariable int id){
        return ResponseEntity.ok(productServices.showProductDetails(id));
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<Page<ShowProductsDTO>> searchedProducts(@PathVariable String keyword , @RequestParam int pageNum){
        return ResponseEntity.ok(productServices.searchProduct(keyword, pageNum));
    }

}
