package br.com.products.adapter.controller;

import br.com.products.adapter.controller.dto.CreateProductDTO;
import br.com.products.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", ignore = true)
    Product mapFrom(CreateProductDTO createProductDTO);

    ProductResponseDTO mapFrom(Product product);

}

