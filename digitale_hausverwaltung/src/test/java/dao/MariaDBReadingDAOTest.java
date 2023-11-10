package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.*;

import models.Reading;

public class MariaDBReadingDAOTest {
    private MariaDBReadingDAO readingDAO = null;

    @BeforeEach
    void setup() {
        readingDAO = new MariaDBReadingDAO();
    }

    @DisplayName("Testing method create")
    @Test
    void testCreate() {
        int tmpID = readingDAO.create(
            "test", 
            LocalDate.of(2012, 12, 12),
            20, 
            "test",
            1
        );
        assertNotNull(tmpID);
        readingDAO.delete(tmpID);
    }

    @DisplayName("Testing method create with object")  
    @Test
    void testCreateObject() {
        Reading tmpReading = new Reading(
            1,
            LocalDate.of(2012, 12, 12),
            "test",
            20, 
            "test"
        );
        int tmpID = readingDAO.create(tmpReading);
        assertNotNull(tmpID);
        readingDAO.delete(tmpID);
    }

    @DisplayName("Testing method get")
    @Test
    void testGet() {
        int tmpID = readingDAO.create(
            "test", 
            LocalDate.of(2012, 12, 12),
            20, 
            "test",
            1
        );
        Reading reading = readingDAO.get(tmpID);
        assertNotNull(reading);
        assertEquals(tmpID, reading.getId());
        readingDAO.delete(tmpID);
    }

    @DisplayName("Testing method getAll")
    @Test
    void testGetAll() {
        List<Reading> readings = readingDAO.getAll();
        assertNotNull(readings);
    }

    @DisplayName("Testing method getAllFromCustomer")
    @Test
    void testGetAllFromCustomer() {
        List<Reading> readings = readingDAO.getAllFromCustomer(1);
        assertNotNull(readings);
    }

    @DisplayName("Testing method getReadingsInit2Years")
    @Test
    void testGetReadingsInit2Years() {
        List<Reading> readings = readingDAO.getReadingsInit2Years();
        assertNotNull(readings);
    }

    @DisplayName("Testing method getReadingsForCustomer")
    @Test
    void testGetReadingsForCustomer() {
        List<Reading> readings = readingDAO.getReadingsForCustomer(
            1,
            LocalDate.of(2012, 12, 12), 
            LocalDate.of(2022, 12, 12)
        );
        assertNotNull(readings);
    }

    @DisplayName("Testing method update")  
    @Test
    void testUpdate() {
        int tmpID = readingDAO.create(
            "test", 
            LocalDate.of(2012, 12, 12),
            20, 
            "test",
            1
        );
        boolean updateWorked = readingDAO.update(
            tmpID,
            "neu",
            LocalDate.of(2014, 12, 12),
            10,
            "Neuer Kommentar"
        );
        assertTrue(updateWorked);
        readingDAO.delete(tmpID);
    }

    @DisplayName("Testing method update with object")  
    @Test
    void testUpdateObject() {
        int tmpID = readingDAO.create(
            "test", 
            LocalDate.of(2012, 12, 12),
            20, 
            "test",
            1
        );
        Reading reading = readingDAO.get(tmpID);
        reading.setComment("neu");
        boolean updateWorked = readingDAO.update(reading);
        assertTrue(updateWorked);
        readingDAO.delete(tmpID);
    }

    @DisplayName("Testing method delete")  
    @Test
    void testDelete() {
        int tmpID = readingDAO.create(
            "test", 
            LocalDate.of(2012, 12, 12),
            20, 
            "test",
            1
        );
        boolean deleteWorked = readingDAO.delete(tmpID);
        assertTrue(deleteWorked);
    }
}
