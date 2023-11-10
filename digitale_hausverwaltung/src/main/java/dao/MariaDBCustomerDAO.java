package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.Customer;
public class MariaDBCustomerDAO implements CustomerDAO {
    private Connection connection = null;

    public MariaDBCustomerDAO(){
        connection = MariaDBFacManDAO.connectToMariaDB();
    }

    @Override
    public int create(String firstname, String lastname) {
        try {
            PreparedStatement ps = connection.prepareStatement("Insert into customer (ID, firstName, lastName) values (?, ?, ?)");
            String generatedUUID = UUID.randomUUID().toString();
            ps.setObject(1, generatedUUID);
            ps.setString(2, firstname);
            ps.setString(3, lastname);
            ps.executeUpdate();
            PreparedStatement IdSelect = connection.prepareStatement("SELECT id FROM customer WHERE UUID = ?");
            IdSelect.setString(1, generatedUUID);
            ResultSet rs = IdSelect.executeQuery();
            int id = rs.getInt("id");

            return id;
        } catch (SQLException e) {
            System.out.println("Error from create without Object: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int create(Customer customer) {
        return create(customer.getFirstname(), customer.getLastname());
    }

    @Override
    public Customer get(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("Select * from customer where ID = ?");
            ps.setObject(1,id);
            ResultSet resultSet = ps.executeQuery();
            Customer customer = new Customer(
                resultSet.getInt("ID"),
                resultSet.getString("UUID"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName")
            );
            return customer;
        } catch (SQLException e) {
            System.out.println("Error from get: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> getAll() {
        try {
            PreparedStatement ps = connection.prepareStatement("Select * from customer");
            ResultSet resultSet = ps.executeQuery();
            List<Customer> list = new ArrayList<Customer>();

            while (resultSet.next()){
                Customer customer = new Customer(
                    resultSet.getInt("ID"),
                    resultSet.getString("UUID"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName")
                );
                list.add(customer);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Error from getAll: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(int id, String firstname, String lastname) {
        try {
            PreparedStatement ps = connection.prepareStatement("Update customer set firstName = ?, firstName = ? where ID = ?");
            ps.setString(1, firstname);
            ps.setString(2,lastname);
            ps.setInt(3, id);
            int resultSet = ps.executeUpdate();
            return (resultSet != 0);
        } catch (SQLException e) {
            System.out.println("Error from update without Object: " + e.getMessage());
            e.getStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        return update(customer.getId(), customer.getFirstname(), customer.getLastname());
    }

    @Override
    public boolean delete(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("Delete from customer where ID = ?");
            ps.setInt(1, id);
            int resultSet = ps.executeUpdate();
            return (resultSet != 0);
        } catch (SQLException e) {
            System.out.println("Error from delete: " + e.getMessage());
            e.getStackTrace();
        }
        return false;
    }
}
