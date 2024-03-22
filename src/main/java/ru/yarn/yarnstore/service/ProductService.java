package ru.yarn.yarnstore.service;

import ru.yarn.yarnstore.entities.Product;

public interface ProductService {
    Product get(long productId);
    Product create(Product product);
    Product update(Product product, long id);
    void delete(long productId);

}
