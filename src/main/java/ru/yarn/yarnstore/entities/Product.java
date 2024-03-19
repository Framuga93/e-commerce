package ru.yarn.yarnstore.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Entity
@Data
@NoArgsConstructor
//TODO: УЗНАТЬ ДЛЯ ЧЕГО НУЖЕН КОНСТРУКТОР БЕЗ АРГУМЕНТОВ
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NonNull
    private String name;

    @NonNull
    private Double price;

    @NonNull
    private String pictureUrl;

}
