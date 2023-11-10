package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;

import org.junit.jupiter.api.*;

public class MariaDBReadingDAOTest {
    private Connection conn = null;
    private MariaDBReadingDAO readingDAO = null;

    @BeforeEach
    void setup() {
        conn = MariaDBFacManDAO.connectToMariaDB();
        readingDAO = new MariaDBReadingDAO();
    }

    @Test
    void createTest() {
        assertEquals(1, );
    }
}
