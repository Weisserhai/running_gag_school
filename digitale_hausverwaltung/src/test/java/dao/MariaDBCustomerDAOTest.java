package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import models.Customer;

public class MariaDBCustomerDAOTest {

    private MariaDBCustomerDAO customer = null;
    @BeforeEach
    void setup(){
        customer = new MariaDBCustomerDAO();
    }

    @Test
    @DisplayName("Testing method create")
    void testCreate(){
        int tmpUUID = customer.create("Max","Mustermann");
        assertNotNull(tmpUUID);
        customer.delete(tmpUUID);
    }

    @Test
    @DisplayName("Testing method create with object")
    void testCreateObject(){
        int id = 555;
        Customer tmpCustomer = new Customer("Maxl", "Mustermann");
        int tmpUUID = customer.create(tmpCustomer);
        assertNotNull(tmpUUID);
        customer.delete(tmpUUID);
    }

    @Test
    @DisplayName("Testing method get")
    void testGet(){
        int tmpUUID = customer.create("Max","Mustermann");
        Customer tmpCustomer = customer.get(tmpUUID);
        assertNotNull(tmpCustomer);
        customer.delete(tmpUUID);
    }

    @Test
    @DisplayName("Testing method getAll")
    void testGetAll(){
        int tmpUUID = customer.create("Max","Mustermann");
        List<Customer> tmpList = customer.getAll();
        assertNotNull(tmpList);
        customer.delete(tmpUUID);
    }

    @Test
    @DisplayName("Testing method update")
    void testUpdate(){
        int tmpUUID = customer.create("Max","Mustermann");
        boolean tmpBoolean = customer.update(tmpUUID, "Maxl", "Musternmann");
        assertEquals(true, tmpBoolean);
        customer.delete(tmpUUID);
    }

    @Test
    @DisplayName("Testing method update with object")
    void testUpdateObject(){
        int tmpUUID = customer.create("Max","Mustermann");
        Customer tmpCustomer = new Customer("Maxl", "Mustermann");
        boolean tmpBoolean = customer.update(tmpCustomer);
        assertEquals(true,tmpBoolean);
        customer.delete(tmpUUID);
    }

    @Test
    @DisplayName("Testing method delete")
    void testDelete(UUID tmpId){
        int tmpUUID = customer.create("Max","Mustermann");
        boolean tmpBoolean = customer.delete(tmpUUID);
        assertEquals(true,tmpBoolean);
    }
}
