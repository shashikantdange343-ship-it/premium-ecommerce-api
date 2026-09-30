package com.example.ecommerceProject.Services;

import com.example.ecommerceProject.DTOs.AddProductDetailsDTO;
import com.example.ecommerceProject.DTOs.AddProductRequestDTO;
import com.example.ecommerceProject.DTOs.ShowProductDetailsDTO;
import com.example.ecommerceProject.DTOs.ShowProductsDTO;
import com.example.ecommerceProject.Entity.Product;
import com.example.ecommerceProject.Entity.ProductDetails;
import com.example.ecommerceProject.Repository.ProductDetailsRepository;
import com.example.ecommerceProject.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

import java.awt.print.PageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductServices{

    private final ProductRepository productRepository;
    private final ConverterClass converterClass;
    private final ProductDetailsRepository productDetailsRepository;
//    private final OrderServices orderServices;

    @Override
    public ShowProductsDTO addProducts(AddProductRequestDTO product, AddProductDetailsDTO productDetails) {
       Product savedProduct = productRepository.save(converterClass.toProduct(product));
       ProductDetails productDetails1 = new ProductDetails();
       productDetails1.setProduct(savedProduct);
       productDetails1.setDescription(productDetails.description());
       productDetails1.setBrand(productDetails.brand());
       productDetails1.setCategory(productDetails.category());
       productDetails1.setColor(productDetails.color());
       productDetails1.setMaterial(productDetails.material() );
       productDetails1.setWeight(productDetails.weight());
       productDetails1.setWarrantyInfo(productDetails.warranty());
       productDetailsRepository.save(productDetails1);
        return converterClass.toProductDTO(savedProduct);
    }
    public Page<ShowProductsDTO> showProducts(int pageNum){
        Pageable page = PageRequest.of(pageNum,12);
        Page<Product> product = productRepository.findAll(page);
        return product.map(converterClass::toProductDTO);
    }

    @Override
    public void updateProduct(Product product) {
        int q = product.getStock();
        product.setStock(q-1);
        productRepository.save(product);
    }

    public ShowProductDetailsDTO showProductDetails(int id){
       Optional<ProductDetails> optionalProductDetails = productDetailsRepository.findById(id);
       Optional<Product> optionalProduct = productRepository.findById(id);
       Product product = optionalProduct.get();
       ProductDetails productDetails = optionalProductDetails.get();

       return converterClass.toProductDetailsDTO(productDetails, product);
    }

    @Override
    public Page<ShowProductsDTO> searchProduct(String keyword , int pageNum) {
        Pageable page = PageRequest.of( pageNum, 12 );
        Page<Product> product = productRepository.searchAdvanced(keyword,page);
        return product.map(converterClass::toProductDTO);
    }


}
