package gb.demochkin.ecommerce.service;

import gb.demochkin.ecommerce.dto.OrderProductResponse;
import gb.demochkin.ecommerce.entities.Order;

import java.util.List;

public interface OrderService {
    Order get(long id);
    Order create(OrderProductResponse orderProductResponse);
    void update(Order order);
    void delete(long id);
    Iterable<Order> list();

}
