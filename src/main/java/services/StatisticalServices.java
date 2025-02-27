package services;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Order;
import pojo.Product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class StatisticalServices {

    public void statisticByDate(Session session, String beginDate, String endDate) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
        Root<Order> oRoot = criteriaQuery.from(Order.class);

        Expression<Date> byDate = builder.function("DATE", Date.class, oRoot.get("createdDate"));
        System.out.println(byDate);
        criteriaQuery.multiselect(
                builder.count(oRoot.get("id").as(Integer.class)),
                builder.sum(oRoot.get("total").as(BigDecimal.class)),
                byDate
        );

        if (beginDate!=null && !beginDate.isEmpty() && endDate!=null && !endDate.isEmpty()) {
            criteriaQuery.where(builder.between(byDate, java.sql.Date.valueOf(beginDate), java.sql.Date.valueOf(endDate)));
        } else
            if (beginDate!=null && !beginDate.isEmpty()) {
            criteriaQuery.where(builder.equal(byDate, java.sql.Date.valueOf(beginDate)));
        }

        criteriaQuery.groupBy(byDate);
        Query<Object[]> query = session.createQuery(criteriaQuery);
        List<Object[]> list = query.getResultList();
        list.forEach(o -> {
            System.out.printf("%d - %.2f - %s\n", o[0], o[1], o[2]);
        });
    }

    public void statisticByMonth(Session session, int month, int year) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
        Root<Order> oRoot = criteriaQuery.from(Order.class);

        Expression<Integer> byMonth = builder.function("MONTH", Integer.class, oRoot.get("createdDate"));
        Expression<Integer> byYear = builder.function("YEAR", Integer.class, oRoot.get("createdDate"));
        criteriaQuery.multiselect(
                builder.count(oRoot.get("id").as(Integer.class)),
                builder.sum(oRoot.get("total").as(BigDecimal.class)),
                byMonth
        );


        criteriaQuery.where(builder.equal(byMonth, month), builder.equal(byYear, year));
        criteriaQuery.groupBy(byMonth);

        Query<Object[]> query = session.createQuery(criteriaQuery);
        List<Object[]> list = query.getResultList();
        list.forEach(o -> {
            System.out.printf("Numbers of Order: %d \nTotal: %.2f \nMonth: %s\n", o[0], o[1], o[2]);
        });

    }
}
