package ru.yarn.yarnstore.service;

import ru.yarn.yarnstore.dto.OrderProductResponse;
import ru.yarn.yarnstore.entities.Order;

public interface OrderService {
    Order get(long id);
    Order create(OrderProductResponse orderProductResponse);
    void update(Order order);
    void delete(long id);

}
