package br.com.products.usecase;

import br.com.products.domain.Product;
import br.com.products.usecase.port.DeleteProductPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteProduct {

    private DeleteProductPort deleteProductPort;

    public DeleteProduct(DeleteProductPort deleteProductPort) {
        this.deleteProductPort = deleteProductPort;
    }

    public void delete(Product product) {
        deleteProductPort.delete(product);
    }
}
