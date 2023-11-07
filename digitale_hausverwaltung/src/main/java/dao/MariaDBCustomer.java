package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import models.Customer;
import dao.MariaDBFacManDAO;

public class MariaDBCustomer implements CustomerDAO {
    private Connection connection = null;

    public MariaDBCustomer(){
        connection = MariaDBFacManDAO.connectToMariaDB();
    }

    @Override
    public int create(String firstname, String lastname) {
        try {
            PreparedStatement ps = connection.prepareStatement("Insert into USERS (ID, firstName, lastName) values (?, ?, ?)");
            ps.setObject(1, UUID.randomUUID());
            ps.setString(2, firstname);
            ps.setString(3, lastname);
            ResultSet resultSet = ps.executeQuery();
            Customer customer = new Customer(
                id,
                resultSet.getString("firstName"),
                resultSet.getString("lastName")
            );
            while(resultSet.next()){
                return customer;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int create(Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Customer get(UUID id) {
        try {
            PreparedStatement ps = connection.prepareStatement("Select * from Customer where ID = ?");
            ps.setObject(1,id);
            ResultSet resultSet = ps.executeQuery();
            Customer customer = new Customer(
                id,
                resultSet.getString("firstName"),
                resultSet.getString("lastName")
            );
            while(resultSet.next()){
                return customer;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public boolean update(int id, String firstname, String lastname) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean update(Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
