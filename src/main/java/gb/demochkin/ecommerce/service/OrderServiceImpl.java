package gb.demochkin.ecommerce.service;

import gb.demochkin.ecommerce.entities.OrderedProduct;
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
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    public Order get(User user){
        return orderRepository.findByUser(user).
                orElseThrow(()-> new NoSuchElementException("Такого заказа нет"));
    }

//    public Order create(OrderProductResponse response){
//        User userResponse = userRepository.findById(response.getUserId()).orElseThrow(
//                ()-> new NoSuchElementException("Пользователь не найден")
//        );;
//        Product productResponse = productRepository.findById(response.getProductId()).orElseThrow(
//                ()-> new NoSuchElementException("Продукт не найден")
//        );
//        Order order = new Order();
//        order.setUser(userResponse);
//        order.setOrderProducts(productResponse);
//        order.setDateCreated(LocalDate.now());
//        orderRepository.save(order);
//        return order;
//    }  //todo: переделать на OrderProduct
    public Order create(User user, OrderedProduct product){
        Order order = new Order();
        order.setUser(user);
        order.setOrderProducts(product);
        order.setDateCreated(LocalDate.now());
        orderRepository.save(order);
        return order;
    }


    public void update(Order order){
        orderRepository.save(order);
    }

    public void delete(long orderId){
        orderRepository.delete(get(orderId));
    }

    public List<Order> list(){
        return orderRepository.findAll().stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
                    if (result.isEmpty()) throw new NoSuchElementException("Список заказов пуст");
                    return result;
                }));
    }
}
