package com.example.ecommerceProject.Services;

import com.example.ecommerceProject.DTOs.*;
import com.example.ecommerceProject.Entity.Order;
import com.example.ecommerceProject.Entity.Product;
import com.example.ecommerceProject.Entity.ProductDetails;
import com.example.ecommerceProject.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConverterClass {


    public User toUser(UserAddRequestDTO user){
        if (user == null) return null;
        String name = user.name();
        String email = user.email();
        String pass = user.password();
        int age = user.age();
        User users = new User();
        users.setUsername(name);
        users.setEmail(email);
        users.setPassword(pass);
        users.setAge(age);
        return users;
    }

    public Product toProduct(AddProductRequestDTO product){
        if (product == null)return null;
       String name = product.name();
       int price = product.price();
       int quantity = product.quantity();
        Product newProduct = new Product();
        newProduct.setProductName(name);
        newProduct.setProductPrice(price);
        newProduct.setStock(quantity);
        return newProduct;
    }
    public ShowProductsDTO toProductDTO(Product product){
        if (product == null) return null;
        return new ShowProductsDTO(product.getProductId(),product.getProductName(),product.getProductPrice(),product.getStock());
    }

    public OrdersDTO toOrdersDTO(Order order){
        if (order == null) return null;
        return new OrdersDTO(order.getOrderId(),toProductDTO(order.getProduct()),order.getPurchasedDate());
    }

    public ShowProductDetailsDTO toProductDetailsDTO(ProductDetails productDetails , Product product){
        if (product== null) return null;
        return new ShowProductDetailsDTO
                (toProductDTO(product) ,
                        productDetails.getDescription(),
                        productDetails.getBrand(),
                        productDetails.getCategory(),
                        productDetails.getColor(),
                        productDetails.getMaterial(),
                        productDetails.getWeight(),
                        productDetails.getWarrantyInfo());
    }

    public ProductDetails toProductDetails(AddProductDetailsDTO productDetails){
        if (productDetails== null) return null;
        ProductDetails productDetails1 = new ProductDetails();
        productDetails1.setDescription(productDetails.description());
        productDetails1.setBrand(productDetails.brand());
        productDetails1.setCategory(productDetails.category());
        productDetails1.setColor(productDetails.color());
        productDetails1.setMaterial(productDetails.material());
        productDetails1.setWeight(productDetails.weight());
        productDetails1.setWarrantyInfo(productDetails.warranty());
        return productDetails1;
    }

}
