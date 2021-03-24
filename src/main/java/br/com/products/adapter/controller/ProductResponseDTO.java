package br.com.products.adapter.controller;

import java.io.Serializable;

class ProductResponseDTO implements Serializable {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public ProductResponseDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductResponseDTO setName(String name) {
        this.name = name;
        return this;
    }
}
