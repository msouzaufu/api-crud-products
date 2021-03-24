package br.com.products.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class Product implements Serializable {

    private static final long serialVersionUID = -6709949360695646993L;

    private String id;

    private String name;

    private String description;

    private BigDecimal price;

}
