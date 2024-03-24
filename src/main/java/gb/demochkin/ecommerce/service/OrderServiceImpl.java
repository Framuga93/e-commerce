package gb.demochkin.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gb.demochkin.ecommerce.entities.Order;
import gb.demochkin.ecommerce.entities.Product;
import gb.demochkin.ecommerce.entities.User;
import gb.demochkin.ecommerce.repositories.OrderRepository;
import gb.demochkin.ecommerce.repositories.ProductRepository;
import gb.demochkin.ecommerce.repositories.UserRepository;
import gb.demochkin.ecommerce.dto.OrderProductResponse;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Order get(long id){
        return orderRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Такого заказа нет"));
    }

    public Order create(OrderProductResponse response){
        User userResponse = userRepository.findById(response.getUserId()).orElseThrow(
                ()-> new NoSuchElementException("Пользователь не найден")
        );;
        Product productResponse = productRepository.findById(response.getProductId()).orElseThrow(
                ()-> new NoSuchElementException("Продукт не найден")
        );
        Order order = new Order();
        order.setUser(userResponse);
        order.setOrderProducts(productResponse);
        order.setDateCreated(LocalDate.now());
        orderRepository.save(order);
        return order;
    }


    public void update(Order order){
        orderRepository.save(order);
    }

    public void delete(long id){
        orderRepository.delete(orderRepository.findById(id).orElseThrow(
                ()-> new NoSuchElementException("Продажа не найдена")
        ));
    }

    public Iterable<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}
