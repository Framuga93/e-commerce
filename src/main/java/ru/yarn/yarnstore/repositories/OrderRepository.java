package ru.yarn.yarnstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yarn.yarnstore.entities.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
