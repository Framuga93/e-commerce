package gb.demochkin.ecommerce.repositories;

import gb.demochkin.ecommerce.entities.OrderedProduct;
import gb.demochkin.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderedProductsRepository extends JpaRepository<OrderedProduct,Long> {
    List<OrderedProduct> findAllByUser(User user);
    OrderedProduct findByName(String name);
}
