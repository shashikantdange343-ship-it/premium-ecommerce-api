package com.example.ecommerceProject.Services;

import com.example.ecommerceProject.DTOs.AddProductDetailsDTO;
import com.example.ecommerceProject.DTOs.AddProductRequestDTO;
import com.example.ecommerceProject.DTOs.ShowProductDetailsDTO;
import com.example.ecommerceProject.DTOs.ShowProductsDTO;
import com.example.ecommerceProject.Entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductServices {
    ShowProductsDTO addProducts(AddProductRequestDTO product, AddProductDetailsDTO productDetails);
    Page<ShowProductsDTO> showProducts(int pageNum);
    void updateProduct(Product product);
    ShowProductDetailsDTO showProductDetails(int id);
    Page<ShowProductsDTO> searchProduct(String keyword, int pageNum);
//    ShowUserDTO toUserDTO(User user);
}
