package br.com.products.adapter.repository;

import org.springframework.data.repository.CrudRepository;

interface ProductRepository extends CrudRepository<ProductEntity, String> {

    boolean existsById(String id);
}
