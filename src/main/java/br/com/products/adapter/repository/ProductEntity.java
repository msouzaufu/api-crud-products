package br.com.products.adapter.repository;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Table(name = "product")
class ProductEntity {

    public ProductEntity setName(String name) {
        this.name = name;
        return this;
    }

    public ProductEntity setId(String id) {
        this.id = id;
        return this;
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;
}
