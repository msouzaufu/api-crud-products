package br.com.products.adapter.repository;

import br.com.products.domain.Product;
import br.com.products.usecase.port.CreateProductPort;
import br.com.products.usecase.port.DeleteProductPort;
import br.com.products.usecase.port.FindProductPort;
import br.com.products.usecase.port.UpdateProductPort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
class ProductRepositoryGateway implements FindProductPort, CreateProductPort, DeleteProductPort, UpdateProductPort {

    private final ProductRepository repository;

    public ProductRepositoryGateway(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Product> findById(String id) {
        return repository.findById(id).map(EntityMapper.INSTANCE::mapFrom);
    }

    @Override
    public Product createProduct(Product product) {
        return EntityMapper.INSTANCE.mapFrom(repository.save(EntityMapper.INSTANCE.mapFrom(product)));
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        repository.findAll().forEach(productEntity -> products.add(EntityMapper.INSTANCE.mapFrom(productEntity)));
        return products;
    }

    @Override
    public void delete(Product product) {
        repository.delete(EntityMapper.INSTANCE.mapFrom(product));
    }

    @Override
    public Product update(Product product) {
        return EntityMapper.INSTANCE.mapFrom(repository.save(EntityMapper.INSTANCE.mapFrom(product)));
    }
}
