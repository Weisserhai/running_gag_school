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

import models.Reading;

public class MariaDBReadingDAO implements ReadingDAO {
    private Connection conn = null;

    public MariaDBReadingDAO() {
        conn = MariaDBFacManDAO.connectToMariaDB();
    }

    @Override
    public int create(String typeofreading, LocalDate dateofreading, int metercount, String comment, UUID c_id) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO reading (ID, customerId, dateOfReading, typeOfReading, meterCount, comment) VALUES (?, ?, ?, ?, ?, ?)"
            );
            UUID generatedUUID = UUID.randomUUID();
            ps.setObject(1, generatedUUID);
            ps.setObject(2, c_id);
            ps.setDate(3, Date.valueOf(dateofreading));
            ps.setString(4, typeofreading);
            ps.setInt(5, metercount);
            ps.setString(6, comment);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error from create" + e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int create(Reading reading) {
        return create(
            reading.getTypeofreading(),
            reading.getDateofreading(), 
            reading.getMetercount(), 
            reading.getComment(), 
            reading.getCustomerID()
        );
    }

    @Override
    public Reading get(UUID id) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM reading WHERE ID = ?");
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Reading reading = new Reading(
                    id, 
                    rs.getObject("customer_id" , java.util.UUID.class),
                    rs.getDate("dateOfReading").toLocalDate(), 
                    rs.getString("typeOfReading"), 
                    rs.getInt("meterCount"), 
                    rs.getString("comment")
                );

                return reading;
            }
        } catch (SQLException e) {
            System.out.println("Error from get" + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Reading> getAll() {
        List<Reading> readings = new ArrayList<>();
        String sql = "SELECT * FROM reading";
        try (PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                readings.add(new Reading(
                        rs.getObject("id" , java.util.UUID.class),
                        rs.getObject("customer_id" , java.util.UUID.class),
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
    public List<Reading> getAllFromCustomer(UUID cust_id) {
        List<Reading> readings = new ArrayList<>();
        String sql = "SELECT * FROM reading WHERE customerId = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, cust_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                readings.add(new Reading(
                        rs.getObject("id" , java.util.UUID.class),
                        rs.getObject("customer_id" , java.util.UUID.class),
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
        try (PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                readings.add(new Reading(
                        rs.getObject("id" , java.util.UUID.class),
                        rs.getObject("customer_id" , java.util.UUID.class),
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
    public List<Reading> getReadingsForCustomer(UUID cust_id, LocalDate start, LocalDate end) {
        List<Reading> readings = new ArrayList<>();
        String sql = "SELECT * FROM reading WHERE customerId = ? AND dateOfReading between ? AND ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, cust_id);
            ps.setDate(2, Date.valueOf(start));
            ps.setDate(3, Date.valueOf(end));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                readings.add(new Reading(
                        rs.getObject("id" , java.util.UUID.class),
                        rs.getObject("customer_id" , java.util.UUID.class),
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
            reading.getMetercount(), 
            reading.getComment()
        );
    }

    @Override
    public boolean update(UUID id, String typeofreading, LocalDate dateofreading, int metercount, String comment) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE reading set dateOfReading = ?, typeOfReading = ?, meterCount = ?, comment = ? WHERE id = ?"
            );
            ps.setDate(1, Date.valueOf(dateofreading));
            ps.setString(2, typeofreading);
            ps.setInt(3, metercount);
            ps.setString(4, comment);
            return (ps.executeUpdate() !=0) ;
        } catch (SQLException e) {
            System.out.println("Error from update" + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(UUID id) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM reading WHERE id = ?"
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
