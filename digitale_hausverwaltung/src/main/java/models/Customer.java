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
    public int getId() {

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

    @Override
    public void setId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }

    @Override
    public String getFirstname() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFirstname'");
    }

    @Override
    public void setFirstname(String firstname) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFirstname'");
    }

    @Override
    public String getLastname() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLastname'");
    }

    @Override
    public void setLastname(String lastname) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setLastname'");
    }

}
