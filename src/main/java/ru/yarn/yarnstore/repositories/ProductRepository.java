package ru.yarn.yarnstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yarn.yarnstore.entities.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductById(Long id);

}
