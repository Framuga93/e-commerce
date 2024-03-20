package ru.yarn.yarnstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yarn.yarnstore.entities.Order;
import ru.yarn.yarnstore.repositories.OrderRepository;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order get(long id){
        return orderRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Такого заказа нет"));
    }

    public Iterable<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order create(Order order){
        order.setDateCreated(LocalDate.now());
        return order;
    }

    public void update(Order order){
        orderRepository.save(order);
    }   // TODO: Поменять на апдейт

    public void delete(long id){
        orderRepository.delete(orderRepository.findById(id).get());  // TODO: Добавить команду в реп
    }
}
