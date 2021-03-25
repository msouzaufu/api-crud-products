package br.com.products.adapter.repository;

import org.springframework.data.repository.CrudRepository;

interface ProductRepository extends CrudRepository<ProductEntity, String> {

    @Override
    boolean existsById(String id);

}
