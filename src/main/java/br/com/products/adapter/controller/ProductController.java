package br.com.products.adapter.controller;

import br.com.products.adapter.controller.dto.CreateProductDTO;
import br.com.products.adapter.controller.dto.ProductResponseDTO;
import br.com.products.domain.Product;
import br.com.products.usecase.CreateProduct;
import br.com.products.usecase.DeleteProduct;
import br.com.products.usecase.FindProduct;
import br.com.products.usecase.UpdateProduct;
import br.com.products.usecase.exception.ProductNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Cadastro um produto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna o produto cadastro com o id"),
            @ApiResponse(code = 400, message = "Requisição invalida ou parametro errado durante a inserção de um produto"),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid CreateProductDTO createProductDTO) {

        var createdProduct = createProduct.create(ProductMapper.INSTANCE.mapFrom(createProductDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.INSTANCE.mapFrom(createdProduct));
    }

    @ApiOperation(value = "Atualiza um produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o produto atualizado com o id"),
            @ApiResponse(code = 404, message = "Caso o id do produto enviado não seja encontrado"),
    })
    @PutMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResponseDTO> update(@PathVariable("id") String id,
                                              @RequestBody @Valid CreateProductDTO createProductDTO) throws Exception {

        var product = updateProduct.update(ProductMapper.INSTANCE.mapFrom(createProductDTO, id));

        return ResponseEntity.status(HttpStatus.OK).body(ProductMapper.INSTANCE.mapFrom(product));
    }

    @ApiOperation(value = "Busca um produto passando um id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o produto encontrado na base de dados"),
            @ApiResponse(code = 404, message = "Caso o produto não seja encontrado"),
    })
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResponseDTO> find(@PathVariable("id") String id) throws ProductNotFoundException {
        return ResponseEntity.ok(ProductMapper.INSTANCE.mapFrom(findProduct.byId(id)));
    }

    @ApiOperation(value = "Busca todos produtos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna todos produtos cadastrados no sistema"),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductResponseDTO>> find() {
        return ResponseEntity.ok(ProductMapper.INSTANCE.mapFrom(findProduct.all()));
    }

    @ApiOperation(value = "Deleta um produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "deleta o produto encontrado na base de dados com o id passado"),
            @ApiResponse(code = 404, message = "Caso o produto não seja encontrado"),
    })
    @DeleteMapping(path = "/{id}")
    ResponseEntity delete(@PathVariable("id") String id) throws ProductNotFoundException {
        Product product = findProduct.byId(id);
        deleteProduct.delete(product);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "busca produtos de acordo com parametros")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca os produtos no sistema de acordo com os parametros passados"),
            @ApiResponse(code = 404, message = "Caso não seja encontrado nenhum produto"),
    })
    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductResponseDTO>> searchByParameters(@RequestParam(required = false, defaultValue = "") String q,
                                                                @RequestParam(required = false, defaultValue = "") BigDecimal min_price,
                                                                @RequestParam(required = false, defaultValue = "") BigDecimal max_price) {
        return ResponseEntity.ok(ProductMapper.INSTANCE.mapFrom(findProduct.getByParameters(QueryMapper.INSTANCE.mapFrom(q, min_price, max_price))));
    }

}
