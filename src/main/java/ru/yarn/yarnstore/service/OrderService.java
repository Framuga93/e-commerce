package ru.yarn.yarnstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yarn.yarnstore.dto.OrderProductResponse;
import ru.yarn.yarnstore.entities.Order;
import ru.yarn.yarnstore.entities.Product;
import ru.yarn.yarnstore.entities.User;
import ru.yarn.yarnstore.repositories.OrderRepository;
import ru.yarn.yarnstore.repositories.ProductRepository;
import ru.yarn.yarnstore.repositories.UserRepository;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Order get(long id){
        return orderRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Такого заказа нет"));
    }

    public Iterable<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order create(OrderProductResponse response){
        Product productResponse = productRepository.findProductById(response.getProductId());
        User userResponse = userRepository.findUserById(response.getUserId());
        Order order = new Order();
        order.setOrderProducts(productResponse);
        order.setUser(userResponse);
        order.setDateCreated(LocalDate.now());
        orderRepository.save(order);
        return order;
    } //TODO exceptions

    public void update(Order order){
        orderRepository.save(order);
    }   // TODO: Поменять на апдейт

    public void delete(long id){
        orderRepository.delete(orderRepository.findById(id).get());  // TODO: Добавить команду в реп
    }
}
