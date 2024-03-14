package ru.yarn.yarnstore;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column
    @NonNull
    String name;

    @Column
    @NonNull
    Double price;

    @Column
    @NonNull
    String pictureUrl;

}
