package br.com.products.adapter.repository;

import br.com.products.domain.Product;
import br.com.products.domain.QueryParameters;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    public List<Product> findAllByParameters(QueryParameters queryParameters) {

        QProductEntity productEntity = QProductEntity.productEntity;
        JPAQuery<?> query = new JPAQuery<Void>(em);

        List<ProductEntity> results = query.select(productEntity)
                .from(productEntity)
                .where(productEntity.name.likeIgnoreCase(queryParameters.getName())
                        .and(productEntity.description.likeIgnoreCase(queryParameters.getName()))).fetch();

        return EntityMapper.INSTANCE.mapFrom(results);
    }

}
