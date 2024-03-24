package gb.demochkin.ecommerce.controllers.views;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import gb.demochkin.ecommerce.entities.Product;
import gb.demochkin.ecommerce.service.ProductServiceImpl;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/view/products")
public class ProductsController {

    private final ProductServiceImpl productService;

    @GetMapping("/form")
    public String showAddProductsAddForm(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        List<Product> products;
        try {
            products = (List<Product>) productService.list();
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
        productService.create(product);
        return "redirect:/products?success";
    }


    @GetMapping("/list")
    public String products(Model model) {
        List<Product> products;
        try {
            products = (List<Product>) productService.list();
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", "Список продуктов пуст");
            return "products";
        }
        model.addAttribute("products", products);
        model.addAttribute("productToDto", new Product());
        return "products";
    }
}
