package ru.yarn.yarnstore.controllers.RESTcontrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yarn.yarnstore.entities.Product;
import ru.yarn.yarnstore.service.ProductServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prdct")
public class ProductController {

    private final ProductServiceImpl productService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable("id") long id){
        return productService.getProductById(id);
    }

    @GetMapping
    public List<Product> getProductList(){
        return productService.getAllProducts();
    }

    @PostMapping
    public Product postNewProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable("id") long id){
        return productService.updateProductFromRepository(product,id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") long id){
        productService.deleteProductFromRepository(id);
    }

    
}
