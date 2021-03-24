package br.com.products.adapter.controller;

import br.com.products.domain.Product;
import br.com.products.usecase.CreateProduct;
import br.com.products.usecase.DeleteProduct;
import br.com.products.usecase.FindProduct;
import br.com.products.usecase.UpdateProduct;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<ProductResponseDTO> create(@RequestBody CreateProductDTO createProductDTO) {

        Product createdProduct = createProduct.create(ProductMapper.INSTANCE.mapFrom(createProductDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.INSTANCE.mapFrom(createdProduct));
    }

//    @PutMapping(path = "/{id}",
//            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    ResponseEntity<UserResponseDTO> update(@PathVariable("id") String id,
//                                           @RequestBody CreateUserDTO createUserDTO) {
//        User createdUser = updateUser.update(UserDTOMapper.to(createUserDTO).setId(id));
//        return ResponseEntity.status(HttpStatus.OK).body(UserDTOMapper.from(createdUser));
//    }
//
//    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    ResponseEntity<UserResponseDTO> find(@PathVariable("id") String id) throws UserNotFoundException {
//        return ResponseEntity.ok(UserDTOMapper.from(findUser.byId(id)));
//    }
//
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    ResponseEntity<List<UserResponseDTO>> find() {
//        return ResponseEntity.ok(UserDTOMapper.from(findUser.all()));
//    }
//
//    @DeleteMapping(path = "/{id}")
//    ResponseEntity delete(@PathVariable("id") String id) throws UserNotFoundException {
//        User user = findUser.byId(id);
//        deleteUser.delete(user);
//        return ResponseEntity.noContent().build();
//    }

}
