package gb.demochkin.ecommerce.service;

import gb.demochkin.ecommerce.dto.OrderProductResponse;
import gb.demochkin.ecommerce.entities.Order;
import gb.demochkin.ecommerce.entities.OrderedProduct;
import gb.demochkin.ecommerce.entities.User;

import java.util.List;

public interface OrderService {
    Order get(long id);
    Order create(User user, OrderedProduct product);
    void update(Order order);
    void delete(long id);
    List<Order> list();

}
