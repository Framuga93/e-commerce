package ru.yarn.yarnstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yarn.yarnstore.entities.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
