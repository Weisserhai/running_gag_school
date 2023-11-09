package models;

import java.util.UUID;

public class Customer implements Customers  {
    private UUID id;
    private String firstname;
    private String lastname;

    public Customer(UUID id, String firstname, String lastname ){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String getFirstname() {
        return firstname;
    }

    @Override
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String getLastname() {
        return lastname;
    }

    @Override
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
