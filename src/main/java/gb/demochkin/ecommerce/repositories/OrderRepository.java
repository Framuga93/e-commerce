package gb.demochkin.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import gb.demochkin.ecommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
