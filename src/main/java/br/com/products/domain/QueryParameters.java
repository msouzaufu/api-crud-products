package br.com.products.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class QueryParameters implements Serializable {

    private static final long serialVersionUID = 2096098400311327874L;

    private String name;

    private BigDecimal min_price;

    private BigDecimal max_price;

}
