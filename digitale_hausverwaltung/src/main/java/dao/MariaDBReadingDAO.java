package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

import com.prop_manage.LoggerBackend;

import models.Reading;

public class MariaDBReadingDAO implements ReadingDAO 
{
    private Connection connection = null;

    public MariaDBReadingDAO() 
    {
        connection = MariaDBFacManDAO.connectToMariaDB();
    }

    // Create

    @Override
    public int create(String typeOfReading, LocalDate dateOfReading, int meterCount, String comment, int customerId) // Creates new reading | error value is "-1" not "1"
    {
        try 
        {
            PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO reading (UUID, customerId, dateOfReading, typeOfReading, meterCount, comment) VALUES (?, ?, ?, ?, ?, ?)"
            );
            String generatedUUID = UUID.randomUUID().toString();
            ps.setString(1, generatedUUID);
            ps.setInt(2, customerId);
            ps.setDate(3, Date.valueOf(dateOfReading));
            ps.setString(4, typeOfReading);
            ps.setInt(5, meterCount);
            ps.setString(6, comment);
            ps.executeUpdate();
            
            PreparedStatement IdSelect = connection.prepareStatement("SELECT ID FROM reading WHERE UUID = ?");
            IdSelect.setString(1, generatedUUID);
            ResultSet rs = IdSelect.executeQuery();
            rs.next();
            int id = rs.getInt("ID");

            LoggerBackend.LOGGER.log(Level.INFO, "New reading created");
            
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
    public int create(Reading reading) // Calls the bigger reading creator | error value is "-1" not "1"
    {
        return create(
            reading.getTypeofreading(),
            reading.getDateofreading(), 
            reading.getMeterCount(), 
            reading.getComment(), 
            reading.getCustomerID()
        );
    }

    // Read

    @Override
    public Reading get(int id) // Returns reading with id <id>
    {
        try 
        {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM reading WHERE ID = ?");
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Reading reading = new Reading(
                id, 
                rs.getString("UUID"),
                rs.getInt("customerId"),
                rs.getDate("dateOfReading").toLocalDate(), 
                rs.getString("typeOfReading"), 
                rs.getInt("meterCount"), 
                rs.getString("comment"));

            LoggerBackend.LOGGER.log(Level.INFO, "Reading read");

            return reading;
        } 
        catch (SQLException error) 
        {
            LoggerBackend.LOGGER.log(Level.SEVERE, "An error occurred: " + error.getMessage());
            error.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reading> getAll() // Returns all readings
    {
        try
        {
            List<Reading> readings = new ArrayList<>();
            String sql = "SELECT * FROM reading";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
    
            while (rs.next()) 
            {
                readings.add(new Reading(
                        rs.getInt("ID"),
                        rs.getString("UUID"),
                        rs.getInt("customerId"),
                        rs.getDate("dateOfReading").toLocalDate(), 
                        rs.getString("typeOfReading"), 
                        rs.getInt("meterCount"), 
                        rs.getString("comment")
                    )
                );
            }
            LoggerBackend.LOGGER.log(Level.INFO, "All readings read");

            return readings;
        } 
        catch (SQLException error) 
        {
            LoggerBackend.LOGGER.log(Level.SEVERE, "An error occurred: " + error.getMessage());
            error.printStackTrace();

            return null;
        }
    }

    @Override
    public List<Reading> getAllFromCustomer(int customerId) 
    {
        try 
        {
            List<Reading> readings = new ArrayList<>();
            String sql = "SELECT * FROM reading WHERE customerId = ?"; 
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) 
            {
                readings.add(new Reading(
                        rs.getInt("ID"),
                        rs.getString("UUID"),
                        customerId,
                        rs.getDate("dateOfReading").toLocalDate(), 
                        rs.getString("typeOfReading"), 
                        rs.getInt("meterCount"), 
                        rs.getString("comment")
                    )
                );
            }
            LoggerBackend.LOGGER.log(Level.INFO, "All readings of one customer");

            return readings;
        } 
        catch (SQLException error) 
        {
            LoggerBackend.LOGGER.log(Level.SEVERE, "An error occurred: " + error.getMessage());
            error.printStackTrace();

            return null;
        }
    }

    @Override
    public List<Reading> getReadingsInit2Years() // Returns all readings from two years ago until current date
    {
        try
        { 
            List<Reading> readings = new ArrayList<>();
            String sql = "SELECT * FROM reading WHERE dateOfReading >= DATE_SUB(CURDATE(), INTERVAL 2 YEAR);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) 
            {
                readings.add(new Reading(
                        rs.getInt("ID"),
                        rs.getString("UUID"),
                        rs.getInt("customerId"),
                        rs.getDate("dateOfReading").toLocalDate(), 
                        rs.getString("typeOfReading"), 
                        rs.getInt("meterCount"), 
                        rs.getString("comment")
                    )
                );
            }
            LoggerBackend.LOGGER.log(Level.INFO, "All readings form two years ago until current date read");

            return readings;
        } 
        catch (SQLException error) 
        {
            LoggerBackend.LOGGER.log(Level.SEVERE, "An error occurred: " + error.getMessage());
            error.printStackTrace();

            return null;
        }
    }

    @Override
    public List<Reading> getReadingsForCustomer(int customerId, LocalDate start, LocalDate end) // Returns readings of a customer between two dates
    {
        try 
        {
            List<Reading> readings = new ArrayList<>();
            String sql = "SELECT * FROM reading WHERE customerId = ? AND dateOfReading between ? AND ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, customerId);
            ps.setDate(2, Date.valueOf(start));
            ps.setDate(3, Date.valueOf(end));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) 
            {
                readings.add(new Reading(
                        rs.getInt("ID"),
                        rs.getString("UUID"),
                        customerId,
                        rs.getDate("dateOfReading").toLocalDate(), 
                        rs.getString("typeOfReading"), 
                        rs.getInt("meterCount"), 
                        rs.getString("comment")
                    )
                );
            }
            LoggerBackend.LOGGER.log(Level.INFO, "All readings fo a Customer between two dates");

            return readings;
        } 
        catch (SQLException error) 
        {
            LoggerBackend.LOGGER.log(Level.SEVERE, "An error occurred: " + error.getMessage());
            error.printStackTrace();
            
            return null;
        }
    }

    // Update

    @Override
    public boolean update(int id, String typeOfReading, LocalDate dateOfReading, int meterCount, String comment) 
    {
        try 
        {
            PreparedStatement ps = connection.prepareStatement(
                "UPDATE reading set dateOfReading = ?, typeOfReading = ?, meterCount = ?, comment = ? WHERE ID = ?"
            );
            ps.setDate(1, Date.valueOf(dateOfReading));
            ps.setString(2, typeOfReading);
            ps.setInt(3, meterCount);
            ps.setString(4, comment);
            ps.setInt(5, id);

            int rs = ps.executeUpdate();

            LoggerBackend.LOGGER.log(Level.INFO, "Reading updated");

            return (rs !=0);
        } 
        catch (SQLException error) 
        {
            LoggerBackend.LOGGER.log(Level.SEVERE, "An error occurred: " + error.getMessage());
            error.printStackTrace();

            return false;
        }
    }

    @Override
    public boolean update(Reading reading) // Calls the bigger update
    {
        return update(
            reading.getId(),
            reading.getTypeofreading(), 
            reading.getDateofreading(), 
            reading.getMeterCount(), 
            reading.getComment()
        );
    }

    // Delete

    @Override
    public boolean delete(int id) // Deletes Reading
    {

        // TODO
        LoggerBackend.LOGGER.log(Level.SEVERE, "Not implemented jet");

        return false;
        

        // try 
        // {
        //     PreparedStatement ps = connection.prepareStatement(
        //         "DELETE FROM reading WHERE ID = ?"
        //     );
        //     ps.setObject(1, id);

        //     int rs = ps.executeUpdate();

        //     LoggerBackend.LOGGER.log(Level.INFO, "Reading deleted");

        //     return (rs !=0);
        // } 
        // catch (SQLException error) 
        // {
        //     LoggerBackend.LOGGER.log(Level.SEVERE, "An error occurred: " + error.getMessage());
        //     error.printStackTrace();

        //     return false;
        // }
    }
}
