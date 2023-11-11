package models;

import java.time.LocalDate;

import dao.MariaDBCustomerDAO;

public class Reading implements Readings{
    private int id;
    private String uuid;
    private int customerId;
    private LocalDate dateOfReading;
    private String typeOfReading;
    private int meterCount;
    private String comment;
    private Customers customer;

    public Reading(int id, String uuid, int customerId, LocalDate dateOfReading, String typeOfReading, int meterCount, String comment) {
        this.id = id;
        this.uuid = uuid;
        this.customerId = customerId;
        this.dateOfReading = dateOfReading;
        this.typeOfReading = typeOfReading;
        this.meterCount = meterCount;
        this.comment = comment;
        MariaDBCustomerDAO customerDAO = new MariaDBCustomerDAO();
        this.customer = customerDAO.get(customerId);
    } 
    
    public Reading(String uuid, int customerId, LocalDate dateOfReading, String typeOfReading, int meterCount, String comment) {
        this.uuid = uuid;
        this.customerId = customerId;
        this.dateOfReading = dateOfReading;
        this.typeOfReading = typeOfReading;
        this.meterCount = meterCount;
        this.comment = comment;
        MariaDBCustomerDAO customerDAO = new MariaDBCustomerDAO();
        this.customer = customerDAO.get(customerId);
    } 

    public Reading(int customerId, LocalDate dateOfReading, String typeOfReading, int meterCount, String comment) {
        this.customerId = customerId;
        this.dateOfReading = dateOfReading;
        this.typeOfReading = typeOfReading;
        this.meterCount = meterCount;
        this.comment = comment;
        MariaDBCustomerDAO customerDAO = new MariaDBCustomerDAO();
        this.customer = customerDAO.get(customerId);
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
        return typeOfReading;
    }

    @Override
    public void setTypeofreading(String typeOfReading) {
        this.typeOfReading = typeOfReading;
    }

    @Override
    public LocalDate getDateofreading() {
        return dateOfReading;
    }

    @Override
    public void setDateofreading(LocalDate dateOfReading) {
        this.dateOfReading = dateOfReading;
    }

    @Override
    public int getMeterCount() {
        return meterCount;
    }

    @Override
    public void setMeterCount(int meterCount) {
        this.meterCount = meterCount;
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
        return customerId;
    }

    public void setCustomerID(int customerId) {
        this.customerId = customerId;
    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }
}
