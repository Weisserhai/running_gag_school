package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
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
        UUID tmpUUID = customer.create("Max","Mustermann");
        assertNotNull(tmpUUID);
        customer.delete(tmpUUID);
    }

    @Test
    @DisplayName("Testing method create with object")
    void testCreateObject(){
        UUID id = null;
        Customer tmpCustomer = new Customer(id,"Maxl", "Mustermann");
        UUID tmpUUID = customer.create(tmpCustomer);
        assertNotNull(tmpUUID);
        customer.delete(tmpUUID);
    }

    @Test
    @DisplayName("Testing method get")
    void testGet(){
        UUID tmpUUID = customer.create("Max","Mustermann");
        Customer tmpCustomer = customer.get(tmpUUID);
        assertNotNull(tmpCustomer);
        customer.delete(tmpUUID);
    }

    @Test
    @DisplayName("Testing method getAll")
    void testGetAll(){
        UUID tmpUUID = customer.create("Max","Mustermann");
        List<Customer> tmpList = customer.getAll();
        assertNotNull(tmpList);
        customer.delete(tmpUUID);
    }

    @Test
    @DisplayName("Testing method update")
    void testUpdate(){
        UUID tmpUUID = customer.create("Max","Mustermann");
        boolean tmpBoolean = customer.update(tmpUUID, "Maxl", "Musternmann");
        assertEquals(true, tmpBoolean);
        customer.delete(tmpUUID);
    }

    @Test
    @DisplayName("Testing method update with object")
    void testUpdateObject(){
        UUID tmpUUID = customer.create("Max","Mustermann");
        Customer tmpCustomer = new Customer(tmpUUID,"Maxl", "Mustermann");
        boolean tmpBoolean = customer.update(tmpCustomer);
        assertEquals(true,tmpBoolean);
        customer.delete(tmpUUID);
    }

    @Test
    @DisplayName("Testing method delete")
    void testDelete(UUID tmpId){
        UUID tmpUUID = customer.create("Max","Mustermann");
        boolean tmpBoolean = customer.delete(tmpUUID);
        assertEquals(true,tmpBoolean);
    }


}
