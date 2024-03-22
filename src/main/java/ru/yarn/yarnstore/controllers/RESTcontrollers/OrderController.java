package ru.yarn.yarnstore.controllers.RESTcontrollers;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.yarn.yarnstore.dto.OrderProductResponse;
import ru.yarn.yarnstore.entities.Order;
import ru.yarn.yarnstore.service.OrderServiceImpl;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    private final OrderServiceImpl orderService;
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Order get(@PathVariable("id") long id){
        return orderService.get(id);
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderProductResponse orderProductResponse) {
        Order order = orderService.create(orderProductResponse);
        log.info("Продажа создана\nID продукта:"+ orderProductResponse.getProductId()+
                "\nID пользователя: "+orderProductResponse.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable("id") long id, @RequestBody Order requestOrder){
        Order order = orderService.get(id);
        order.setUser(requestOrder.getUser());
        order.setOrderProducts(requestOrder.getOrderProducts());
        order.setDateCreated(LocalDate.now());
        orderService.update(order);
        log.info("Продажа создана\n"+
                "Пользователь: "+order.getUser()+"\n"+
                "Продукты: "+order.getOrderProducts());
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Order> delete(@PathVariable("id") long id){
        orderService.delete(id);
        log.info("Продукт "+id+" удален");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}
