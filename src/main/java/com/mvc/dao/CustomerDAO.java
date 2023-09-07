package com.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mvc.bean.Customer;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Ramesh Fadatare
 *
 */
public class CustomerDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/assignment?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "danish0611";

    private static final String INSERT_CUSTOMERS_SQL = "INSERT INTO customers" + "  (firstname, lastname, street, address, city, state,  email, phone) VALUES (?, ?, ?, ?, ? ,?, ?, ?);";

    private static final String SELECT_CUSTOMER_BY_ID = "select id, firstname, lastname, street, address, city, state,  email, phone from customers where id =?";
    private static final String SELECT_ALL_CUSTOMERS = "select * from customers";
    private static final String DELETE_CUSTOMERS_SQL = "delete from customers where id = ?;";
    private static final String UPDATE_CUSTOMERS_SQL = "update customers set firstname = ?, lastname = ?, street = ?,address = ?,city = ?,state = ?, email= ?, phone =? where id = ?;";

    public CustomerDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public void insertCustomer(Customer user) throws SQLException {
        System.out.println(INSERT_CUSTOMERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); 
        	PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMERS_SQL)) {
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getStreet());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getCity());
            preparedStatement.setString(6, user.getState());
            preparedStatement.setString(7, user.getEmail());
            preparedStatement.setString(8, user.getPhone());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Customer selectCustomer(int id) {
    	Customer customer = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String street = rs.getString("street");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                customer = new Customer(id, firstname, lastname, street, address, city, state, email, phone);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customer;
    }

    public List < Customer > selectAllCustomers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Customer > users = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String street = rs.getString("street");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                users.add(new Customer(id, firstname, lastname, street, address, city, state, email, phone));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public boolean deleteCustomer(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
        	PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateCustomer(Customer user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
        	PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMERS_SQL);) {
        	statement.setString(1, user.getFirstname());
        	statement.setString(2, user.getLastname());
        	statement.setString(3, user.getStreet());
        	statement.setString(4, user.getAddress());
        	statement.setString(5, user.getCity());
        	statement.setString(6, user.getState());
        	statement.setString(7, user.getEmail());
        	statement.setString(8, user.getPhone());
            statement.setInt(9, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
