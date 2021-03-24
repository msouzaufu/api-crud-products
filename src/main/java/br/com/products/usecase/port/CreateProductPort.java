package br.com.products.usecase.port;


import br.com.products.domain.Product;

public interface CreateProductPort {

    Product createUser(Product product);
}
