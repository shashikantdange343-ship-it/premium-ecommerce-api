package com.example.ecommerceProject.Services;

import com.example.ecommerceProject.DTOs.OrderPlaceRequestDTO;
//import com.example.ecommerceProject.DTOs.OrderResponseDTO;
import com.example.ecommerceProject.DTOs.OrdersDTO;
import com.example.ecommerceProject.Entity.Order;
import com.example.ecommerceProject.Entity.Product;
import com.example.ecommerceProject.Entity.User;
import com.example.ecommerceProject.GlobalExceptions.ResourceNotFoundException;
import com.example.ecommerceProject.Repository.OrderRepository;
import com.example.ecommerceProject.Repository.ProductRepository;
import com.example.ecommerceProject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderServices{

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ConverterClass converterClass;
    private final ProductServices productServices;
    private final OrderRepository orderRepository;

    @Override
    public boolean placeOrder(OrderPlaceRequestDTO order) {
        Optional<User> user = userRepository.findByUsername(order.userName());
        if (user.isEmpty()){
            throw new ResourceNotFoundException("User Name : "+ order.userName() + " Not Found In Data");
        }
        User u = user.get();
        Optional<Product> product = productRepository.findById(order.productId());
        if (product.isEmpty()){
            throw new ResourceNotFoundException("Product Name : "+ order.productId() + " Not Found In Data");
        }
        Product p = product.get();
        if (0 < p.getStock()){
            productServices.updateProduct(p);
            Order order1 = new Order();
            order1.setProduct(p);
            order1.setUser(u);
            orderRepository.save(order1);

            return true;
        }
        else {
          return false;
        }
    }

    @Override
    public List<OrdersDTO> getOrders(int id) {
        return orderRepository.findByUserId(id).stream().map(converterClass::toOrdersDTO).collect(Collectors.toList());
    }
}
