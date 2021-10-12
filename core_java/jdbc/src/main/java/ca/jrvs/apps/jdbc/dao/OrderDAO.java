package ca.jrvs.apps.jdbc.dao;

import ca.jrvs.apps.jdbc.model.Customer;
import ca.jrvs.apps.jdbc.model.Order;
import ca.jrvs.apps.jdbc.model.OrderedItem;
import ca.jrvs.apps.jdbc.utils.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DataAccessObject<Order> {

    private final String GET_BY_ID_QUERRY ="SELECT o.order_id, " +
            "o.creation_date, o.total_due , o.status , o.salesperson_id," +
            "s.first_name," +
            "p.product_id , p.code, p.\"name\", p.size, p.variety, p.price, p.status, " +
            "oi.quantity, " +
            "c.first_name as cus_firstname, c.last_name as cus_lastname, c.email as cus_email "+
            "FROM orders o " +
            "INNER JOIN order_item oi ON o.order_id =oi.order_id " +
            "INNER JOIN customer c ON o.customer_id =c.customer_id " +
            "INNER JOIN  salesperson s ON o.salesperson_id =s.salesperson_id " +
            "INNER JOIN product p ON oi.product_id =p.product_id " +
            "WHERE o.order_id = ?";

    public OrderDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Order findById(long id) {
        Order order = new Order();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_BY_ID_QUERRY)){
            statement.setLong(1,id);
            ResultSet rs= statement.executeQuery();

            ArrayList<OrderedItem> orderedItems = new ArrayList<>();
            Customer customer = new Customer();

            while(rs.next()){
                order.setId(rs.getLong("order_id"));
                order.setCreationDate(rs.getTimestamp("creation_date"));
                order.setTotalDue(rs.getFloat(3));
                order.setStatus(rs.getString(4));
                order.setSalePersonId(rs.getLong(5));
                order.setSalePersonFirstName(rs.getString(6));
                //make a temp orderedItem
                OrderedItem orderedItemInfo = new OrderedItem();
                orderedItemInfo.setId(rs.getLong("product_id"));
                orderedItemInfo.setCode(rs.getString(8));
                orderedItemInfo.setName(rs.getString(9));
                orderedItemInfo.setSize(rs.getInt(10));
                orderedItemInfo.setVariety(rs.getString(11));
                orderedItemInfo.setPrice(rs.getFloat("price"));
                orderedItemInfo.setStatus(rs.getString(13));
                orderedItemInfo.setQuantity(rs.getInt(14));
                //add ordered item into orderedItems
                orderedItems.add(orderedItemInfo);

                order.setCustomerFirstName(rs.getString("cus_firstname"));
                order.setCustomerLastName(rs.getString("cus_lastname"));
                order.setCustomerEmail(rs.getString("cus_email"));
            }
            order.setOrderedItems(orderedItems);
        }
        catch (SQLException e){
            throw new RuntimeException("Error: Failed to find order by id",e);
        }

        return order;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Order create(Order dto) {
        return null;
    }

    @Override
    public Order update(Order dto) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
