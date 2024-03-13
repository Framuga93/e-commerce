package ru.yarn.yarnstore;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product getProductById(long id){
        return productRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Такого нет"));
    }
}
