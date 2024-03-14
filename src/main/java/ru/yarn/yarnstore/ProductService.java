package ru.yarn.yarnstore;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product getProductById(long id){
        return productRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Такого продукта нет"));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll().stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
                    if (result.isEmpty()) throw new NoSuchElementException("Список продуктов пуст");
                    return result;
                }));
    }

    public Product saveNewBookToRepository(Product product) {
        return productRepository.save(product);
    }

    public Product updateProductFromRepository(Product newProduct, long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setPrice(newProduct.getPrice());
                    product.setPictureUrl(newProduct.getPictureUrl());
                    return productRepository.save(product);
                })
                .orElseGet(()-> productRepository.save(newProduct));
    }

    public void deleteProductFromRepository(long id) {
        productRepository.delete(getProductById(id));
    }
}
