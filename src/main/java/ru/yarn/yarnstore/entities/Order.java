package ru.yarn.yarnstore.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product orderProducts;

    @OneToOne
    @JoinColumn(name = "users_id")
    private User user;

//    @Transient
//    public Double getTotalOrderPrice(){
//        List<Product> orderProducts = getOrderProducts();   //TODO исправить
//        return orderProducts.stream().mapToDouble(OrderProduct::getTotalPrice).sum();
//    }

//    @Transient
//    public int getNumberOfProducts(){
//        return this.orderProducts.size();
//    }


}
