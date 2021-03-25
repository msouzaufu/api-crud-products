package br.com.products.adapter.repository;

import br.com.products.adapter.controller.ProductMapper;
import br.com.products.domain.Product;
import br.com.products.domain.QueryParameters;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProductRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    public List<Product> findAllByParameters(QueryParameters queryParameters){
        TypedQuery<ProductEntity> findQuery = em.createNamedQuery(ProductEntity.FIND_ALL_PRODUCTS_BY_PARAMETERS, ProductEntity.class);
        findQuery.setParameter("name", queryParameters.getName());
        findQuery.setParameter("min_price", queryParameters.getMin_price());
        findQuery.setParameter("max_price", queryParameters.getMax_price());

        var results = findQuery.getResultList();

        return EntityMapper.INSTANCE.mapFrom(results);
    }

}
