package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.Customer;
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
            int resultSet = ps.executeUpdate();
            return resultSet;

        } catch (SQLException e) {
            System.out.println("Error from create without Object: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int create(Customer customer) {
        try {
            PreparedStatement ps = connection.prepareStatement("Insert into USERS values ?");
            ps.setObject(1, customer);
            int resultSet = ps.executeUpdate();
            return resultSet;

        } catch (SQLException e) {
            System.out.println("Error from create with Object: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
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
            System.out.println("Error from get: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> getAll() {
        try {
            PreparedStatement ps = connection.prepareStatement("Select * from Customer");
            ResultSet resultSet = ps.executeQuery();
            List<Customer> list = new ArrayList<Customer>();

            while (resultSet.next()){
                Customer customer = new Customer(
                (UUID) resultSet.getObject("ID"),
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
            PreparedStatement ps = connection.prepareStatement("Update Customer set firstName = ?, firstName = ? where ID = ?");
            ps.setString(1, firstname);
            ps.setString(2,lastname);
            ps.setObject(3, id);
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
        try {
            PreparedStatement ps = connection.prepareStatement("Update Customer set firstName = ?, firstName = ? where ID = ?");
            ps.setString(1,customer.getFirstname());
            ps.setString(2, customer.getLastname());
            ps.setObject(3, customer.getId());
            int resultSet = ps.executeUpdate();
            return (resultSet != 0);
        } catch (SQLException e) {
            System.out.println("Error from update with Object: " + e.getMessage());
            e.getStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("Delete from Customer where ID = ?");
            ps.setObject(1, id);
            int resultSet = ps.executeUpdate();
            return (resultSet != 0);
        } catch (SQLException e) {
            System.out.println("Error from delete: " + e.getMessage());
            e.getStackTrace();
        }
        return false;
    }

}
