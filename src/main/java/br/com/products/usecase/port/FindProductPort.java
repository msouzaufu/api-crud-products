package br.com.products.usecase.port;


import br.com.products.domain.Product;

import java.util.List;
import java.util.Optional;

public interface FindProductPort {

    Optional<Product> findById(String id);

    List<Product> findAll();

}
