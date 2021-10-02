package ca.jrvs.apps.jdbc.dao;

import ca.jrvs.apps.jdbc.JDBCExecutor;
import ca.jrvs.apps.jdbc.model.Customer;
import ca.jrvs.apps.jdbc.utils.DataAccessObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDAO extends DataAccessObject<Customer> {
    final Logger logger = LoggerFactory.getLogger(JDBCExecutor.class);

    private final String INSERT_QUERRY ="insert into customer(first_name,last_name," +
            "email,phone,address,city,state,zipcode) VALUES(?,?,?,?,?,?,?,?)";

    private final String FIND_BY_ID_QUERRY="SELECT customer_id, first_name, last_name, email,phone, " +
            "address, city,state,zipcode FROM customer WHERE customer_id =?";

    private final String UPDATE_QUERRY="UPDATE customer SET first_name=?, last_name=?, email=?,phone=?," +
            "address=?, city=?,state=?,zipcode=? WHERE customer_id=?";

    private final String DELETE_QUERRY = "DELETE FROM customer WHERE customer_id=?";

    public CustomerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Customer findById(long id) {
        Customer result = new Customer();
        try(PreparedStatement statement = this.connection.prepareStatement(FIND_BY_ID_QUERRY)){
            statement.setLong(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                result.setId(rs.getLong("customer_id"));
                result.setFirstName(rs.getString("first_name"));
                result.setLastName(rs.getString("last_name"));
                result.setEmail(rs.getString("email"));
                result.setPhone(rs.getString("phone"));
                result.setAddress(rs.getString("address"));
                result.setCity(rs.getString("city"));
                result.setState(rs.getString("state"));
                result.setZipcode(rs.getString("zipcode"));
            }
            logger.info("Find customer by id completed!");
        }catch (SQLException e){
            throw new RuntimeException("ERROR: Failed to find the customer!",e);
        };
        return result;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer create(Customer dto) {
        try(PreparedStatement statement = this.connection.prepareStatement(INSERT_QUERRY)){
            statement.setString(1, dto.getFirstName());
            statement.setString(2,dto.getLastName());
            statement.setString(3, dto.getEmail());
            statement.setString(4, dto.getPhone());
            statement.setString(5, dto.getAddress());
            statement.setString(6, dto.getCity());
            statement.setString(7,dto.getState());
            statement.setString(8, dto.getZipcode());
            int status = statement.executeUpdate();
//            int id = this.getLastVal(CUSTOMER_SEQUENCE);
//            Customer customer = this.findById(id);
            logger.info("Inserted "+status+" new customer(s)");

        }catch(SQLException e){
            throw new RuntimeException("Error: Couldn't create customer!",e);
        }
        return null;
    }

    @Override
    public Customer update(Customer dto) {
        try(PreparedStatement statement = this.connection.prepareStatement(UPDATE_QUERRY)){
            statement.setString(1,dto.getFirstName());
            statement.setString(2,dto.getLastName());
            statement.setString(3,dto.getEmail());
            statement.setString(4,dto.getPhone());
            statement.setString(5,dto.getAddress());
            statement.setString(6,dto.getCity());
            statement.setString(7,dto.getState());
            statement.setString(8,dto.getZipcode());
            statement.setLong(9,dto.getId());
            int status = statement.executeUpdate();
            logger.info("Updated "+status+" customer: "+ dto);

        }catch (SQLException e){
            throw new RuntimeException("Error: Failed to update!", e);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        try(PreparedStatement statement = this.connection.prepareStatement(DELETE_QUERRY)){
            statement.setLong(1,id);
            int status = statement.executeUpdate();
            logger.info("Deleted "+status+" customer!");
        }catch (SQLException e){
            throw new RuntimeException("Error: Failed to delete!",e);
        }
    }
}
