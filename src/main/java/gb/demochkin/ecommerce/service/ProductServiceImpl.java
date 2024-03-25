package gb.demochkin.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import gb.demochkin.ecommerce.entities.Product;
import gb.demochkin.ecommerce.repositories.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public Product get(long productId){
        return productRepository.findById(productId).orElseThrow(
                ()-> new NoSuchElementException("Такого продукта нет"));
    }

    public Product create(Product product) {
        Product findSameProduct = productRepository.findByName(product.getName());
        if(findSameProduct != null){
            findSameProduct.setQuantity(findSameProduct.getQuantity()+product.getQuantity());
            return productRepository.save(findSameProduct);
        }
        return productRepository.save(product);
    }

    public Product update(Product newProduct, long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setPrice(newProduct.getPrice());
                    return productRepository.save(product);
                })
                .orElseGet(()-> productRepository.save(newProduct));
    }

    public void delete(long productId) {
        productRepository.delete(get(productId));
    }

    public List<Product> list(){
        return productRepository.findAll().stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
                    if (result.isEmpty())
                        throw new NoSuchElementException("Список продуктов пуст");
                    return result;
                }));
    }
}
