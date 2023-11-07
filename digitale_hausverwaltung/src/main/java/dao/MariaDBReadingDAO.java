package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
            // TODO Auto-generated catch block
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
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Reading> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public List<Reading> getAllFromCustomer(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllFromCustomer'");
    }

    @Override
    public List<Reading> getReadingsInit2Years() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReadingsInit2Years'");
    }

    @Override
    public List<Reading> getReadingsForCustomer(UUID id, LocalDate start, LocalDate end) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReadingsForCustomer'");
    }

    @Override
    public int update(Reading reading) {
        return update(
            reading.getId(),
            reading.getTypeofreading(), 
            reading.getDateofreading(), 
            reading.getMetercount(), 
            reading.getComment()
        );
    }

    @Override
    public int update(UUID id, String typeofreading, LocalDate dateofreading, int metercount, String comment) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE reading set dateOfReading = ?, typeOfReading = ?, meterCount = ?, comment = ? WHERE id = ?"
            );
            ps.setDate(1, Date.valueOf(dateofreading));
            ps.setString(2, typeofreading);
            ps.setInt(3, metercount);
            ps.setString(4, comment);
            return ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public boolean delete(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
