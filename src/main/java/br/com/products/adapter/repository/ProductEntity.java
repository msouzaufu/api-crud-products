package br.com.products.adapter.repository;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NamedQuery(name = ProductEntity.FIND_ALL_PRODUCTS_BY_PARAMETERS,
query = "SELECT p FROM ProductEntity p where p.name = :name")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
class ProductEntity {

    public static final String FIND_ALL_PRODUCTS_BY_PARAMETERS = "findAllProductsByParameters";

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
