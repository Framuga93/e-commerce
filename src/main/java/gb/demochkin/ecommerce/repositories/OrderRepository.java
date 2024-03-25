package gb.demochkin.ecommerce.repositories;

import gb.demochkin.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import gb.demochkin.ecommerce.entities.Order;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
        Optional<Order> findByUser(User user);
}
