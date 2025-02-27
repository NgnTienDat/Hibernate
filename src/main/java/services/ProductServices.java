package services;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Category;
import pojo.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductServices {
    public List<Product> getProducts(Session session, String keyword, BigDecimal minPrice,
                                     BigDecimal maxPrice, String category, String sort) {



        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
        Root<Product> pRoot = criteriaQuery.from(Product.class);

        criteriaQuery = criteriaQuery.select(pRoot);
        List<Predicate> predicates = new ArrayList<>();

        // Query by name
        if (keyword != null && !keyword.isEmpty()) {
            Predicate predicateKeyWord = builder.like(pRoot.get("name").as(String.class), "%" + keyword + "%");
            predicates.add(predicateKeyWord);
            criteriaQuery = criteriaQuery.where(predicateKeyWord);
        }


        // Query by price range
        if (minPrice != null && (maxPrice == null || maxPrice.equals(BigDecimal.ZERO))) {
            Predicate predicateMinPrice = builder.greaterThanOrEqualTo(pRoot.get("price").as(BigDecimal.class), minPrice);
            predicates.add(predicateMinPrice);
        }

        if (maxPrice != null && (minPrice == null || minPrice.equals(BigDecimal.ZERO))) {
            Predicate predicateMaxPrice = builder.lessThanOrEqualTo(pRoot.get("price").as(BigDecimal.class), minPrice);
            predicates.add(predicateMaxPrice);
        }

        if ((minPrice != null && !minPrice.equals(BigDecimal.ZERO) && (maxPrice != null && !maxPrice.equals(BigDecimal.ZERO))) ) {
            Predicate predicate = builder.between(pRoot.get("price").as(BigDecimal.class), minPrice, maxPrice);
            predicates.add(predicate);
        }

        // Query by category
        if (category!= null && !category.isEmpty()) {
            Predicate predicateCategory = builder.like(pRoot.get("category").get("name").as(String.class), "%" + category + "%");
            predicates.add(predicateCategory);
        }
        // Combine query condition with AND
        criteriaQuery.where(builder.and(predicates.toArray(new Predicate[0])));

        // Order by
        if (sort == "id") {
            criteriaQuery.orderBy(builder.desc(pRoot.get("id").as(BigDecimal.class)));
        } else if (sort == "name") {
            criteriaQuery.orderBy(builder.asc(pRoot.get("name").as(String.class)));
        } else if (sort == "price") {
            criteriaQuery.orderBy(builder.asc(pRoot.get("price").as(BigDecimal.class)));
        }


        Query<Product> query = session.createQuery(criteriaQuery);
        List<Product> products = query.getResultList();

        return products;
    }

    public void insertProduct(Session session, Product product) {
        session.beginTransaction();
        session.persist(product);
        session.getTransaction().commit();
    }

    public void updateProduct(Session session, Product product) {
        session.beginTransaction();
        session.merge(product);
        session.getTransaction().commit();
    }

    public void deleteProduct(Session session, Product product) {
        session.beginTransaction();
        session.remove(product);
        session.getTransaction().commit();    }
}
