package br.com.products.usecase.exception;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String id) {
        super(String.format("Product with id %s not found", id));
    }
}
