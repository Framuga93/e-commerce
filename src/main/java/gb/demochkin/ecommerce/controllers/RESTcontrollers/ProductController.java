package gb.demochkin.ecommerce.controllers.RESTcontrollers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import gb.demochkin.ecommerce.entities.Product;
import gb.demochkin.ecommerce.service.ProductServiceImpl;

import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product get(@PathVariable("id") long id){
        return productService.get(id);
    }

    @PostMapping
    public Product create(@RequestBody Product product){
        log.info("Продукт "+product.getName()+" создан");
        return productService.create(product);
    }

    @PutMapping("/{id}")
    public Product update(@RequestBody Product product, @PathVariable("id") long id){
        log.info("Продукт ID%"+id+" обновлен");
        return productService.update(product,id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        log.info("Продукт ID%"+id+" удален");
        productService.delete(id);
    }

    @GetMapping
    public Iterable<Product> list(){
        return productService.list();
    }
}
