package br.com.products.usecase;

import br.com.products.domain.Product;
import br.com.products.usecase.exception.ProductNotFoundException;
import br.com.products.usecase.port.FindProductPort;
import br.com.products.usecase.port.UpdateProductPort;
import org.springframework.stereotype.Service;

@Service
public class UpdateProduct {

    private UpdateProductPort updateProductPort;
    private FindProductPort findProductPort;

    public UpdateProduct(UpdateProductPort updateProductPort, FindProductPort findProductPort) {
        this.updateProductPort = updateProductPort;
        this.findProductPort = findProductPort;
    }

    public Product update(Product product) throws Exception {
        if(findProductPort.findById(product.getId()).isPresent()){
            return updateProductPort.update(product);
        }
        else throw new ProductNotFoundException(product.getId());
    }
}
