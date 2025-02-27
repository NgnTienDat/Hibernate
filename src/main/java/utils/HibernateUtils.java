package utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import pojo.*;

import java.util.Properties;

public class HibernateUtils {
    private static final SessionFactory FACTORY;

    static {
        Configuration configuration = new Configuration();

        Properties properties = new Properties();
        properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/newsaledb");
        properties.put("hibernate.connection.username", "root");
        properties.put("hibernate.connection.password", "tiendatmySQL964@");
        properties.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
//        Lúc chạy nó bảo ko cần vì nó dựa vào url jdbc nên tự biết chọn dialect
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");

        configuration.setProperties(properties);
        configuration.addAnnotatedClass(Category.class);
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(Manufacturer.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Order.class);
        configuration.addAnnotatedClass(OrderDetail.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        FACTORY = configuration.buildSessionFactory(registry);
    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }
}
