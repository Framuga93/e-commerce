package gb.demochkin.ecommerce.controllers.views;


import gb.demochkin.ecommerce.entities.Order;
import gb.demochkin.ecommerce.entities.OrderedProduct;
import gb.demochkin.ecommerce.entities.Product;
import gb.demochkin.ecommerce.entities.User;
import gb.demochkin.ecommerce.service.OrderedProductServiceImpl;
import gb.demochkin.ecommerce.service.ProductServiceImpl;
import gb.demochkin.ecommerce.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/view/orders")
public class OrdersController {

    private final ProductServiceImpl productService;
    private final UserServiceImpl userService;
    private final OrderedProductServiceImpl orderService;

    @PostMapping
    public String saveOrderedProduct(@RequestParam("quantity") int quantity, @RequestParam("productId") int productId,
                       @RequestParam("email") String userEmail) {
        Product product = productService.get(productId);
        User user = userService.findUserByEmail(userEmail);

        product.setQuantity(product.getQuantity()-quantity);

        OrderedProduct orderedProducts = new OrderedProduct(); // <-- todo: вынести в map?
        orderedProducts.setName(product.getName());
        orderedProducts.setUser(user);
        orderedProducts.setPrice(product.getPrice());
        orderedProducts.setQuantity(quantity);

        productService.update(product,product.getId());
        orderService.save(orderedProducts);

        log.info("\nЗаказ:" +
                "\nПользователь: "+ userEmail+
                "\nПродукт: "+product.getName()+
                "\nКоличество: " + quantity+
                "\nСоздан");
        return "redirect:/view/products/list";
    }

    @GetMapping("/cart")
    public String showCart(Principal principal , Model model){
        User user = userService.findUserByEmail(principal.getName());
        List<OrderedProduct> orderedProduct = orderService.list(user);
        Double fullPrice = orderedProduct.stream()
                .mapToDouble(OrderedProduct::getPrice)
                .sum();

        model.addAttribute("orderedProduct", orderedProduct);
        model.addAttribute("fullPrice", fullPrice);
        return "cart";
    }

    @GetMapping("/{id}")
    public String deleteOrderedProduct(@PathVariable("id") long id){
        orderService.delete(id);
        return "redirect:/view/orders/cart?success";
    }

}
