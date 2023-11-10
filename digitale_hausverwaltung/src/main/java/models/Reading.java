package models;

import java.time.LocalDate;

import dao.MariaDBCustomerDAO;

public class Reading implements Readings{
    private int id;
    private String uuid;
    private int customer_id;
    private LocalDate dateofreading;
    private String typeofreading;
    private int metercount;
    private String comment;
    private Customers customer;

    public Reading(int id, String uuid, int customer_id, LocalDate dateofreading, String typeofreading, int metercount, String comment) {
        this.id = id;
        this.uuid = uuid;
        this.customer_id = customer_id;
        this.dateofreading = dateofreading;
        this.typeofreading = typeofreading;
        this.metercount = metercount;
        this.comment = comment;
        MariaDBCustomerDAO customerDAO = new MariaDBCustomerDAO();
        this.customer = customerDAO.get(customer_id);
    } 
    
    public Reading(String uuid, int customer_id, LocalDate dateofreading, String typeofreading, int metercount, String comment) {
        this.uuid = uuid;
        this.customer_id = customer_id;
        this.dateofreading = dateofreading;
        this.typeofreading = typeofreading;
        this.metercount = metercount;
        this.comment = comment;
        MariaDBCustomerDAO customerDAO = new MariaDBCustomerDAO();
        this.customer = customerDAO.get(customer_id);
    } 

    public Reading(int customer_id, LocalDate dateofreading, String typeofreading, int metercount, String comment) {
        this.customer_id = customer_id;
        this.dateofreading = dateofreading;
        this.typeofreading = typeofreading;
        this.metercount = metercount;
        this.comment = comment;
        MariaDBCustomerDAO customerDAO = new MariaDBCustomerDAO();
        this.customer = customerDAO.get(customer_id);
    } 

    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
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

    public int getCustomerID() {
        return customer_id;
    }

    public void setCustomerID(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }
}
