package br.com.products.usecase;

import br.com.products.domain.Product;
import br.com.products.usecase.port.UpdateProductPort;
import org.springframework.stereotype.Service;

@Service
public class UpdateProduct {

    private UpdateProductPort updateProductPort;

    public UpdateProduct(UpdateProductPort updateProductPort) {
        this.updateProductPort = updateProductPort;
    }

    public Product update(Product product) {
        return updateProductPort.update(product);
    }
}
