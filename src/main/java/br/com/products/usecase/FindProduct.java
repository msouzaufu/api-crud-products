package br.com.products.usecase;

import br.com.products.domain.Product;
import br.com.products.usecase.exception.ProductNotFoundException;
import br.com.products.usecase.port.FindProductPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindProduct {

    private final FindProductPort findProductPort;

    public FindProduct(FindProductPort findProductPort) {
        this.findProductPort = findProductPort;
    }

    public Product byId(String id) throws ProductNotFoundException {
        return findProductPort.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> all() {
        return findProductPort.findAll();
    }
}
