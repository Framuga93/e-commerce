package ru.yarn.yarnstore.service;

import ru.yarn.yarnstore.entities.playLoadClasses.ProductResponse;

public interface ProductService {
    ProductResponse getProductById(long productId);


}
