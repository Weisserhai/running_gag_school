package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class Reading implements Readings{
    private UUID id;
    private UUID customer_id;
    private LocalDate dateofreading;
    private String typeofreading;
    private int metercount;
    private String comment;

    public Reading(UUID id, UUID customer_id, LocalDate dateofreading, String typeofreading, int metercount, String comment) {
        this.id = id;
        this.customer_id = customer_id;
        this.dateofreading = dateofreading;
        this.typeofreading = typeofreading;
        this.metercount = metercount;
        this.comment = comment;
    } 

    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String getTypeofreading() {
        return typeofreading;
    }

    @Override
    public void setTypeofreading(String typeofreading) {
        this.typeofreading = typeofreading;
    }

    @Override
    public LocalDate getDateofreading() {
        return dateofreading;
    }

    @Override
    public void setDateofreading(LocalDate dateofreading) {
        this.dateofreading = dateofreading;
    }

    @Override
    public int getMetercount() {
        return metercount;
    }

    @Override
    public void setMetercount(int metercount) {
        this.metercount = metercount;
    }

    @Override
    public String getComment() {        
        return comment;
    }

    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    //TODO
    @Override
    public Customers getCustomer() {
        Customers customer = MariaDBCustomerDAO.get(customer_id);

        return customer;
    }

    @Override
    public void setCustomer(Customers customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCustomer'");
    }

    public UUID getCustomerID() {
        return customer_id;
    }

    public void setCustomerID(UUID customer_id) {
        this.customer_id = customer_id;
    }
}
