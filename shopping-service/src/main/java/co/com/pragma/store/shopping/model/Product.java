package co.com.pragma.store.shopping.model;

import lombok.Data;

@Data
public class Product {

    private Long id;

    private String name;

    private String description;

    private Double stock;

    private Double price;

    private Double status;

    private Category category;
}
