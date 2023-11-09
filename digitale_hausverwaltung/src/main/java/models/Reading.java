package models;

import java.time.LocalDate;
import java.util.UUID;

import dao.MariaDBCustomerDAO;

public class Reading implements Readings{
    private UUID id;
    private UUID customer_id;
    private LocalDate dateofreading;
    private String typeofreading;
    private int metercount;
    private String comment;
    private Customers customer;

    public Reading(UUID id, UUID customer_id, LocalDate dateofreading, String typeofreading, int metercount, String comment) {
        this.id = id;
        this.customer_id = customer_id;
        this.dateofreading = dateofreading;
        this.typeofreading = typeofreading;
        this.metercount = metercount;
        this.comment = comment;
        MariaDBCustomerDAO customerDAO = new MariaDBCustomerDAO();
        this.customer = customerDAO.get(customer_id);
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

    @Override
    public Customers getCustomer() {
        return customer;
    }

    @Override
    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public UUID getCustomerID() {
        return customer_id;
    }

    public void setCustomerID(UUID customer_id) {
        this.customer_id = customer_id;
    }
}
