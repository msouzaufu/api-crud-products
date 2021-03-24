package br.com.products.usecase;

import br.com.products.domain.Product;
import br.com.products.usecase.port.CreateProductPort;
import org.springframework.stereotype.Service;

@Service
public class CreateProduct {

    private CreateProductPort createProductPort;

    private FindProduct findProduct;

    public CreateProduct(CreateProductPort createProductPort, FindProduct findProduct) {
        this.createProductPort = createProductPort;
        this.findProduct = findProduct;
    }

    public Product create(Product product) {
        return createProductPort.createProduct(product);
    }
}
