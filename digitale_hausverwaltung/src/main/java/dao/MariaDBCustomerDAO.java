package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

import models.Customer;

import com.prop_manage.LoggerBackend;

public class MariaDBCustomerDAO implements CustomerDAO 
{
    private Connection connection = null;

    public static void main(String[] args)
    {
        MariaDBCustomerDAO abc = new MariaDBCustomerDAO();
        abc.create("test", "rest");
    }


    public MariaDBCustomerDAO()
    {
        connection = MariaDBFacManDAO.connectToMariaDB();
    }

    // Create

    @Override
    public int create(String firstname, String lastname) // Inserts new customer into database | error value is "-1" not "1"
    {
        try 
        {
            PreparedStatement ps = connection.prepareStatement("Insert into customer (UUID, Firstname, Lastname) values (?, ?, ?)");
            String generatedUUID = UUID.randomUUID().toString();

            ps.setObject(1, generatedUUID);
            ps.setString(2, firstname);
            ps.setString(3, lastname);
            ps.executeUpdate();

            PreparedStatement IdSelect = connection.prepareStatement("SELECT ID FROM customer WHERE UUID = ?");
            IdSelect.setString(1, generatedUUID);
            ResultSet rs = IdSelect.executeQuery();

            rs.next();
            int id = rs.getInt("ID");

            LoggerBackend.LOGGER.log(Level.INFO, "New customer created");
            
            return id;
        } 
        catch (SQLException error) 
        {
            LoggerBackend.LOGGER.log(Level.SEVERE, "An error occurred: " + error.getMessage());
            error.printStackTrace();

            return -1;
        }
    }

    @Override
    public int create(Customer customer) // Calls the bigger customer creator | error value is "-1" not "1"
    {
        return create(customer.getFirstname(), customer.getLastname());
    }

    // Read

    @Override
    public Customer get(int id) // Returns customer with id <id>
    {
        try 
        {
            PreparedStatement ps = connection.prepareStatement("Select * from customer where ID = ?");
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            rs.next();

            Customer customer = new Customer(
                rs.getInt("ID"),
                rs.getString("UUID"),
                rs.getString("Firstname"),
                rs.getString("Lastname")
            );

            LoggerBackend.LOGGER.log(Level.INFO, "Customer read");

            return customer;
        } 
        catch (SQLException error) 
        {
            LoggerBackend.LOGGER.log(Level.SEVERE, "An error occurred: " + error.getMessage());
            error.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Customer> getAll() // Returns all customers
    {
        try 
        {
            PreparedStatement ps = connection.prepareStatement("Select * from customer");
            ResultSet rs = ps.executeQuery();
            List<Customer> list = new ArrayList<Customer>();

            while (rs.next())
            {
                Customer customer = new Customer(
                    rs.getInt("ID"),
                    rs.getString("UUID"),
                    rs.getString("firstName"),
                    rs.getString("lastName")
                );
                list.add(customer);
            }
            LoggerBackend.LOGGER.log(Level.INFO, "All customers read");

            return list;
        } 
        catch (SQLException error) 
        {
            LoggerBackend.LOGGER.log(Level.SEVERE, "An error occurred: " + error.getMessage());
            error.printStackTrace();
        }
        return null;
    }

    // Update

    @Override
    public boolean update(int id, String firstname, String lastname) 
    {
        try 
        {
            PreparedStatement ps = connection.prepareStatement("Update customer set Firstname = ?, Lastname = ? where ID = ?");
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setInt(3, id);

            int rs = ps.executeUpdate();

            LoggerBackend.LOGGER.log(Level.INFO, "Customer updated");

            return (rs != 0);
        } 
        catch (SQLException error) 
        {
            LoggerBackend.LOGGER.log(Level.SEVERE, "An error occurred: " + error.getMessage());
            error.getStackTrace();

            return false;
        }
    }

    @Override
    public boolean update(Customer customer) // Calls the bigger update
    {
        return update(customer.getId(), customer.getFirstname(), customer.getLastname());
    }

    // Delete 

    @Override
    public boolean delete(int id) 
    {
        // TODO
        LoggerBackend.LOGGER.log(Level.SEVERE, "Not implemented jet");

        return false;


        // try
        // {
        //     PreparedStatement ps = connection.prepareStatement("Delete from customer where ID = ?");
        //     ps.setInt(1, id);
        //     int rs = ps.executeUpdate();

        //     LoggerBackend.LOGGER.log(Level.INFO, "Customer deleted"); 

        //     return (rs != 0);
        // }
        // catch (SQLException error) 
        // {
        //     LoggerBackend.LOGGER.log(Level.SEVERE, "An error occurred: " + error.getMessage());
        //     error.getStackTrace();

        //     return false;
        // }
    }
}
