package br.com.products.adapter.controller;

import lombok.Data;

import java.math.BigDecimal;

@Data
class ProductResponseDTO {

    private String id;

    private String name;

    private String description;

    private BigDecimal price;
}
