package ru.yarn.yarnstore.dto;

import lombok.Data;
import ru.yarn.yarnstore.entities.Product;

@Data
public class OrderProductDto {

    private Product product;
    private Integer quantity;
}
