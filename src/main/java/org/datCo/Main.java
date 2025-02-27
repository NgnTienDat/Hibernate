package org.datCo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojo.*;
import services.ProductServices;
import services.StatisticalServices;
import utils.HibernateUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtils.getFACTORY().openSession();

        ProductServices productServices = new ProductServices();

//        List<Product> products = productServices.getProducts(session, "",
//                new BigDecimal(200000), new BigDecimal(50000000), "", "id");
//        products.forEach(p -> System.out.printf("%d - %s\n", p.getId(), p.getName()));

//        Category category = session.get(Category.class, 2);
//
//        Product product = new Product();
//        product.setName("Lenovo ThinkPad X1");
//        product.setDescription("RAM32");
//        product.setPrice(new BigDecimal(52000000));
//        product.setCategory(category);

//        productServices.insertProduct(session, product);
//
//        Product product = session.get(Product.class, 20);
//
//        productServices.deleteProduct(session, product);

//        User user = new User();
//        user.setUsername("dat");
//        user.setPassword("123");
//        user.setEmail("dat@gmail.com");
//        user.setFirstName("Dat");
//        user.setLastName("Nguyen");
//        user.setPhone("011111111");

//        session.beginTransaction();
//
//        User user = session.get(User.class, "b785dc01-3817-4d45-8d58-2f65cdef8698");
//        Product product = session.get(Product.class, 3);
//        Product product2 = session.get(Product.class, 4);
//
//        Order order = new Order();
//        order.setUser(user);
//        order.setTotal(new BigDecimal(3000000));
//        order.setProductNumber(2);
//
//        session.persist(order);
//
//
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setOrder(order);
//        orderDetail.setQuantity(1);
//        orderDetail.setProduct(product);
//        orderDetail.setUnitPrice(product.getPrice().multiply(BigDecimal.valueOf(orderDetail.getQuantity())));
//
//        session.persist(orderDetail);
//
//
//        OrderDetail orderDetail2 = new OrderDetail();
//        orderDetail2.setOrder(order);
//        orderDetail2.setQuantity(2);
//        orderDetail2.setProduct(product2);
//        orderDetail2.setUnitPrice(product2.getPrice().multiply(BigDecimal.valueOf(orderDetail2.getQuantity())));
//
//
//        session.persist(orderDetail2);
//
//
//        session.getTransaction().commit();
//
        StatisticalServices statisticalServices = new StatisticalServices();
        statisticalServices.statisticByDate(session, "", "");

//        statisticalServices.statisticByMonth(session, 2, 2025);

    }
}