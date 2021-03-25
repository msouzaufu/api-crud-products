package br.com.products.usecase.port;


import br.com.products.domain.Product;
import br.com.products.domain.QueryParameters;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface FindProductPort {

    Optional<Product> findById(String id);

    List<Product> findAll();

    List<Product> findByParameters(QueryParameters queryParameters);
}
