package ru.yarn.yarnstore.controllers.cnt;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.yarn.yarnstore.entities.Product;
import ru.yarn.yarnstore.service.ProductService;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductsController {

    private final ProductService productService;

    @GetMapping
    public String showAddProductsAddForm(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        List<Product> products;
        try {
            products = productService.getAllProducts();
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", "Список продуктов пуст");
            return "addProduct";
        }
        model.addAttribute("products", products);
        return "addProduct";
    }

    @PostMapping("/save")
    public String productsAdd(@Valid @ModelAttribute("product") Product product,
                              BindingResult result,
                              Model model){
        if(result.hasErrors()){
            model.addAttribute("product", product);
            return "/addProduct";
        }
        productService.saveProductToRepository(product);
        return "redirect:/products?success";
    }

    @GetMapping("/cartSave/{id}")
    public String productsSavetoCart(
            @PathVariable Long id,
            @RequestParam int input
            ){
        System.out.println(productService.getProductById(id)+ String.valueOf(input));
        return "redirect:/products/list?success";
    }

    @GetMapping("/list")
    public String products(Model model) {
        List<Product> products;
        try {
            products = productService.getAllProducts();
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", "Список продуктов пуст");
            return "products";
        }
        model.addAttribute("products", products);
        model.addAttribute("productToDto", new Product());
        return "products";
    }
}
