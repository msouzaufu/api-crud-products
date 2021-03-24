package br.com.products.adapter.controller;

import java.io.Serializable;

class CreateProductDTO implements Serializable {

    private String name;

    public String getName() {
        return name;
    }
 
    CreateProductDTO setName(String name) {
        this.name = name;
        return this;
    }
}
