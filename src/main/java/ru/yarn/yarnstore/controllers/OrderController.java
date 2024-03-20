package ru.yarn.yarnstore.controllers;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.yarn.yarnstore.dto.OrderProductDto;
import ru.yarn.yarnstore.entities.Order;
import ru.yarn.yarnstore.entities.OrderProduct;
import ru.yarn.yarnstore.service.OrderProductService;
import ru.yarn.yarnstore.service.OrderService;
import ru.yarn.yarnstore.service.ProductService;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final ProductService productService;
    private final OrderService orderService;
    private final OrderProductService orderProductService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Order get(@PathVariable("id") long id){
        return orderService.get(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable<Order> list() {
        return orderService.getAllOrders();
    }


    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderForm form) {
        List<OrderProductDto> formDto = form.getProductDtoList();
        validateProductsExistence(formDto);
        Order order;
        order = orderService.create(new Order());

        List<OrderProduct> orderProducts = new ArrayList<>();
        formDto
                .forEach(dto -> {
                    orderProducts.add(orderProductService.create(
                            new OrderProduct(order,
                                    productService.getProductById(dto
                                            .getProduct()
                                            .getId()),
                                    dto.getQuantity())));
                });
        order.setOrderProducts(orderProducts);
        orderService.update(order);

        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);

    }


    private void validateProductsExistence(List<OrderProductDto> orderProducts) {
        List<OrderProductDto> list = orderProducts
                .stream()
                .filter(op -> Objects.isNull(productService.getProductById(op
                        .getProduct()
                        .getId())))
                .toList();

        if (!CollectionUtils.isEmpty(list)) {
            throw new NoSuchElementException("Продукты не найдены");
        }
    }

    //TODO: удаление и апдейт



    @Data
    public static class OrderForm {
        private List<OrderProductDto> productDtoList;
    }
}
