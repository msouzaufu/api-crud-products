package br.com.products.adapter.controller;

import br.com.products.adapter.controller.dto.CreateProductDTO;
import br.com.products.adapter.controller.dto.ProductResponseDTO;
import br.com.products.domain.Product;
import br.com.products.usecase.CreateProduct;
import br.com.products.usecase.DeleteProduct;
import br.com.products.usecase.FindProduct;
import br.com.products.usecase.UpdateProduct;
import br.com.products.usecase.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "/products")
class ProductController {

    private CreateProduct createProduct;
    private FindProduct findProduct;
    private DeleteProduct deleteProduct;
    private UpdateProduct updateProduct;

    ProductController(CreateProduct createProduct, FindProduct findProduct, DeleteProduct deleteProduct, UpdateProduct updateProduct) {
        this.createProduct = createProduct;
        this.findProduct = findProduct;
        this.deleteProduct = deleteProduct;
        this.updateProduct = updateProduct;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid CreateProductDTO createProductDTO) {

        var createdProduct = createProduct.create(ProductMapper.INSTANCE.mapFrom(createProductDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.INSTANCE.mapFrom(createdProduct));
    }

    @PutMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResponseDTO> update(@PathVariable("id") String id,
                                              @RequestBody @Valid CreateProductDTO createProductDTO) throws Exception {

        var product = updateProduct.update(ProductMapper.INSTANCE.mapFrom(createProductDTO, id));

        return ResponseEntity.status(HttpStatus.OK).body(ProductMapper.INSTANCE.mapFrom(product));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResponseDTO> find(@PathVariable("id") String id) throws ProductNotFoundException {
        return ResponseEntity.ok(ProductMapper.INSTANCE.mapFrom(findProduct.byId(id)));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductResponseDTO>> find() {
        return ResponseEntity.ok(ProductMapper.INSTANCE.mapFrom(findProduct.all()));
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity delete(@PathVariable("id") String id) throws ProductNotFoundException {
        Product product = findProduct.byId(id);
        deleteProduct.delete(product);
        return ResponseEntity.ok().build();
    }


    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductResponseDTO>> searchByParameters(@RequestParam(required = false, defaultValue = "") String q,
                                                                @RequestParam(required = false, defaultValue = "") BigDecimal min_price,
                                                                @RequestParam(required = false, defaultValue = "") BigDecimal max_price) {
        return ResponseEntity.ok(ProductMapper.INSTANCE.mapFrom(findProduct.getByParameters(QueryMapper.INSTANCE.mapFrom(q, min_price, max_price))));
    }

}
