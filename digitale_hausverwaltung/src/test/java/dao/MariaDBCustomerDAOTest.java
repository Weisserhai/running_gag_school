package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MariaDBCustomerDAOTest {

    private Connection conn = null;

    @BeforeEach
    void setup(){
        conn = MariaDBFacManDAO.connectToMariaDB();
    }

    @Test
    @DisplayName("Testing method create")
    int create(){
        assertEquals(1);
    }
}
