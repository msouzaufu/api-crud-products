package br.com.products.adapter.controller;

import br.com.products.adapter.controller.dto.ProductResponseDTO;
import br.com.products.domain.Product;
import br.com.products.domain.QueryParameters;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface QueryMapper {
    QueryMapper INSTANCE = Mappers.getMapper(QueryMapper.class);


    @Mapping(target = "name", source = "q", defaultValue = "")
    @Mapping(target = "min_price", source = "min_price", defaultValue = "")
    @Mapping(target = "max_price", source = "max_price", defaultValue = "")
    QueryParameters mapFrom(String q, BigDecimal min_price, BigDecimal max_price);
}
