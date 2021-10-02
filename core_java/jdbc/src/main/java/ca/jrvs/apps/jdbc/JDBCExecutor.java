package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.dao.CustomerDAO;
import ca.jrvs.apps.jdbc.dao.OrderDAO;
import ca.jrvs.apps.jdbc.model.Customer;
import ca.jrvs.apps.jdbc.model.Order;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCExecutor {
    final Logger logger = LoggerFactory.getLogger(JDBCExecutor.class);

    public static void main(String[] args) {
        BasicConfigurator.configure(); //set default config for logger
        JDBCExecutor executor = new JDBCExecutor();
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "hplussport",
                "postgres","password");
        try{
            Connection connection = dcm.getConnection();
            OrderDAO orderDAO = new OrderDAO(connection);
            Order order = orderDAO.findById(1012);
            executor.logger.info("Get order by id: "+order);

            CustomerDAO customerDAO = new CustomerDAO(connection);
            Customer customer = new Customer("Phuong","Ly","abc@gmail.com",
                    "1234567890","abc street","Toronto","ON","W1W 1A1");

//            customerDAO.create(customer);
            executor.logger.info(customerDAO.findById(1000).toString());

//            customer.setId(10002);
//            customerDAO.update(customer);

//            customerDAO.delete(10003);

        }catch (SQLException e){
            executor.logger.error("Failed to run JDBC Executor!",e);
        }
    }
}
