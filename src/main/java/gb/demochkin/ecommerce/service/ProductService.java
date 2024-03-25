package gb.demochkin.ecommerce.service;

import gb.demochkin.ecommerce.entities.Product;

import java.util.List;

public interface ProductService {
    Product get(long productId);
    Product create(Product product);
    Product update(Product product, long id);
    void delete(long productId);
    List<Product> list();

}
