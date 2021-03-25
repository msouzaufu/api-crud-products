package br.com.products.adapter.repository;

import br.com.products.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EntityMapper {

    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    ProductEntity mapFrom(Product product);

    Product mapFrom(ProductEntity productEntity);

    List<Product> mapFrom(List<ProductEntity> productsEntity);

}
