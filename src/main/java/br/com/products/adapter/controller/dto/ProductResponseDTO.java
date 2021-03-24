package br.com.products.adapter.controller.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public
class ProductResponseDTO {

    private String id;

    private String name;

    private String description;

    private BigDecimal price;
}
