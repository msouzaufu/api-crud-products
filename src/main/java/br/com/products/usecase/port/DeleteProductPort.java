package br.com.products.usecase.port;


import br.com.products.domain.Product;

public interface DeleteProductPort {

    void delete(Product product);
}
