package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import models.Customer;

public class MariaDBCustomerDAOTest {
    private MariaDBCustomerDAO customerDAO = null;

    @BeforeEach
    void setup(){
        customerDAO = new MariaDBCustomerDAO();
    }

    @Test
    @DisplayName("Testing method create")
    void testCreate(){
        int tmpID = customerDAO.create("Max","Mustermann");
        assertNotNull(tmpID);
        customerDAO.delete(tmpID);
    }

    @Test
    @DisplayName("Testing method create with object")
    void testCreateObject(){
        Customer tmpCustomer = new Customer("Maxl", "Mustermann");
        int tmpID = customerDAO.create(tmpCustomer);
        assertNotNull(tmpID);
        customerDAO.delete(tmpID);
    }

    @Test
    @DisplayName("Testing method get")
    void testGet(){
        int tmpID = customerDAO.create("Max","Mustermann");
        Customer tmpCustomer = customerDAO.get(tmpID);
        assertNotNull(tmpCustomer);
        assertEquals(tmpID, tmpCustomer.getId());
        customerDAO.delete(tmpID);
    }

    @Test
    @DisplayName("Testing method getAll")
    void testGetAll(){
        List<Customer> tmpList = customerDAO.getAll();
        assertNotNull(tmpList);
    }

    @Test
    @DisplayName("Testing method update")
    void testUpdate(){
        int tmpID = customerDAO.create("Max","Mustermann");
        boolean tmpBoolean = customerDAO.update(tmpID, "Maxl", "Mustermann1");
        assertTrue(tmpBoolean);
        customerDAO.delete(tmpID);
    }

    @Test
    @DisplayName("Testing method update with object")
    void testUpdateObject(){
        int tmpID = customerDAO.create("Max","Mustermann");
        Customer tmpCustomer = customerDAO.get(tmpID);
        tmpCustomer.setFirstname("Test");
        boolean tmpBoolean = customerDAO.update(tmpCustomer);
        assertTrue(tmpBoolean);
        customerDAO.delete(tmpID);
    }

    @Test
    @DisplayName("Testing method delete")
    void testDelete(){
        int tmpID = customerDAO.create("Max","Mustermann");
        boolean tmpBoolean = customerDAO.delete(tmpID);
        assertTrue(tmpBoolean);
    }
}
