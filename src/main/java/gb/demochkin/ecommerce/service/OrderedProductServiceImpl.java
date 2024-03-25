package gb.demochkin.ecommerce.service;

import gb.demochkin.ecommerce.entities.Order;
import gb.demochkin.ecommerce.entities.OrderedProduct;
import gb.demochkin.ecommerce.entities.User;
import gb.demochkin.ecommerce.repositories.OrderedProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderedProductServiceImpl {

    private final OrderedProductsRepository orderedProductsRepository;

    public List<OrderedProduct> list(User user) {
        return orderedProductsRepository.findAllByUser(user);
    }

    public void save(OrderedProduct orderedProduct) {
        List<OrderedProduct> orderedProductList = orderedProductsRepository.findAll();
        for (OrderedProduct product : orderedProductList) {
            if (product.getUser().equals(orderedProduct.getUser()) &&
                    product.getName().equals(orderedProduct.getName()) &&
                    product.getQuantity() > 0) {
                product.setQuantity(product.getQuantity() + orderedProduct.getQuantity());
                orderedProductsRepository.save(product);
                return;
            }
        }
        orderedProductsRepository.save(orderedProduct);
    }

    public void delete(long id) {
        orderedProductsRepository.deleteById(id);
    }
}
