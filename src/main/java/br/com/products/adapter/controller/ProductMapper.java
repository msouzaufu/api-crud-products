package br.com.products.adapter.controller;

import br.com.products.adapter.controller.dto.CreateProductDTO;
import br.com.products.adapter.controller.dto.ProductResponseDTO;
import br.com.products.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", ignore = true)
    Product mapFrom(CreateProductDTO createProductDTO);

    ProductResponseDTO mapFrom(Product product);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "description", source = "dto.description")
    @Mapping(target = "price", source = "dto.price")
    Product mapFrom(CreateProductDTO dto, String id);

    List<ProductResponseDTO> mapFrom(List<Product> products);
}

