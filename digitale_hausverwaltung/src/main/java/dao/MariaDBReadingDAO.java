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
import java.util.logging.Logger;

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
    public List<Reading> getAll() {
        List<Reading> readings = new ArrayList<>();
        String sql = "SELECT * FROM reading";
        try (PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                readings.add(new Reading(
                        rs.getInt("ID"),
                        rs.getString("UUID"),
                        rs.getInt("customer_id"),
                        rs.getDate("dateOfReading").toLocalDate(), 
                        rs.getString("typeOfReading"), 
                        rs.getInt("meterCount"), 
                        rs.getString("comment")
                    )
                );
            }
        } catch (SQLException e) {
            System.out.println("Error from getAll" + e.getMessage());
            e.printStackTrace();
        }

        return readings;
    }

    @Override
    public List<Reading> getAllFromCustomer(int cust_id) {
        List<Reading> readings = new ArrayList<>();
        String sql = "SELECT * FROM reading WHERE customerId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, cust_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                readings.add(new Reading(
                        rs.getInt("ID"),
                        rs.getString("UUID"),
                        rs.getInt("customer_id"),
                        rs.getDate("dateOfReading").toLocalDate(), 
                        rs.getString("typeOfReading"), 
                        rs.getInt("meterCount"), 
                        rs.getString("comment")
                    )
                );
            }
        } catch (SQLException e) {
            System.out.println("Error from getAllFromCustomer" + e.getMessage());
            e.printStackTrace();
        }

        return readings;
    }

    @Override
    public List<Reading> getReadingsInit2Years() {
        List<Reading> readings = new ArrayList<>();
        String sql = "SELECT * FROM reading WHERE dateOfReading >= DATE_SUB(CURDATE(), INTERVAL 2 YEAR);";
        try (PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                readings.add(new Reading(
                        rs.getInt("ID"),
                        rs.getString("UUID"),
                        rs.getInt("customer_id"),
                        rs.getDate("dateOfReading").toLocalDate(), 
                        rs.getString("typeOfReading"), 
                        rs.getInt("meterCount"), 
                        rs.getString("comment")
                    )
                );
            }
        } catch (SQLException e) {
            System.out.println("Error from getReadingsInit2Years" + e.getMessage());
            e.printStackTrace();
        }

        return readings;
    }

    @Override
    public List<Reading> getReadingsForCustomer(int cust_id, LocalDate start, LocalDate end) {
        List<Reading> readings = new ArrayList<>();
        String sql = "SELECT * FROM reading WHERE customerId = ? AND dateOfReading between ? AND ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, cust_id);
            ps.setDate(2, Date.valueOf(start));
            ps.setDate(3, Date.valueOf(end));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                readings.add(new Reading(
                        rs.getInt("ID"),
                        rs.getString("UUID"),
                        rs.getInt("customer_id"),
                        rs.getDate("dateOfReading").toLocalDate(), 
                        rs.getString("typeOfReading"), 
                        rs.getInt("meterCount"), 
                        rs.getString("comment")
                    )
                );
            }
        } catch (SQLException e) {
            System.out.println("Error from getReadingsForCustomer" + e.getMessage());
            e.printStackTrace();
        }

        return readings;
    }

    @Override
    public boolean update(Reading reading) {
        return update(
            reading.getId(),
            reading.getTypeofreading(), 
            reading.getDateofreading(), 
            reading.getMeterCount(), 
            reading.getComment()
        );
    }

    @Override
    public boolean update(int id, String typeofreading, LocalDate dateofreading, int metercount, String comment) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                "UPDATE reading set dateOfReading = ?, typeOfReading = ?, meterCount = ?, comment = ? WHERE ID = ?"
            );
            ps.setDate(1, Date.valueOf(dateofreading));
            ps.setString(2, typeofreading);
            ps.setInt(3, metercount);
            ps.setString(4, comment);
            ps.setInt(5, id);
            return (ps.executeUpdate() !=0) ;
        } catch (SQLException e) {
            System.out.println("Error from update" + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM reading WHERE ID = ?"
            );
            ps.setObject(1, id);
            return (ps.executeUpdate() !=0);
        } catch (SQLException e) {
            System.out.println("Error from delete" + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }
}
