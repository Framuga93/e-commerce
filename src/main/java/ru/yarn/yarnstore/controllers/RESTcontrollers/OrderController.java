package ru.yarn.yarnstore.controllers.RESTcontrollers;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.yarn.yarnstore.dto.OrderProductResponse;
import ru.yarn.yarnstore.entities.Order;
import ru.yarn.yarnstore.service.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    private final OrderService orderService;
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


//    @PostMapping
//    public ResponseEntity<Order> create(@RequestBody OrderProductResponse orderProductResponse) {
//        log.info(String.valueOf(orderProductResponse.getProductId()),orderProductResponse.getUserId());
//        Order order = orderService.create(orderProductResponse);
//        return ResponseEntity.status(HttpStatus.CREATED).body(order);
//    } //TODO : УБРАНО ИЗЗА КОРЗИНЫ


    //TODO: удаление и апдейт

}
