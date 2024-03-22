package ru.yarn.yarnstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yarn.yarnstore.repositories.ProductRepository;
import ru.yarn.yarnstore.entities.Product;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl {

    private final ProductRepository productRepository;

    public Product getProductById(long productId){
        return productRepository.findById(productId).orElseThrow(()-> new NoSuchElementException("Такого продукта нет"));
    }

    public Product addProduct(Product product) {
        Product findSameProduct = productRepository.findByName(product.getName());
        if(findSameProduct != null){
            findSameProduct.setQuantity(findSameProduct.getQuantity()+product.getQuantity());
            return productRepository.save(findSameProduct);
        }
        return productRepository.save(product);
    }

    public Product updateProductFromRepository(Product newProduct, long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setPrice(newProduct.getPrice());
                    return productRepository.save(product);
                })
                .orElseGet(()-> productRepository.save(newProduct));
    }

    public void deleteProductFromRepository(long id) {
        productRepository.delete(getProductById(id));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll().stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
                    if (result.isEmpty()) throw new NoSuchElementException("Список продуктов пуст");
                    return result;
                }));
    }
}
