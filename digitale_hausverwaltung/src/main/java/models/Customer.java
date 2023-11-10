package models;

public class Customer implements Customers  {
    private int id;
    private String uuid;
    private String firstname;
    private String lastname;

    public Customer(int id ,String uuid, String firstname, String lastname ){
        this.id = id;
        this.uuid = uuid;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    
    public Customer(String uuid, String firstname, String lastname ){
        this.uuid = uuid;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
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

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }
}
