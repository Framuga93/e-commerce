package ru.yarn.yarnstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yarn.yarnstore.entities.OrderProduct;
import ru.yarn.yarnstore.repositories.OrderProductRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;

    public OrderProduct create(OrderProduct orderProduct){
        return orderProductRepository.save(orderProduct);
    }

}
