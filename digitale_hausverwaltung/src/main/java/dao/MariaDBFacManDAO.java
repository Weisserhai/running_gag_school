package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MariaDBFacManDAO implements FacManDAO{
    @Override
    public void createDatabaseFacMan() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createDatabaseFacMan'");
    }

    @Override
    public void createTableCustomer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTableCustomer'");
    }

    @Override
    public void createTableReading() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTableReading'");
    }

    public static Connection connectToMariaDB() {
        Connection connection = null;

        String jdbcUrl = "jdbc:mariadb://localhost:3306/running_gag_database";
        String username = "running_gag";
        String password = "";

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error while connecting to the database: " + e.getMessage());
        }

        return connection;
    }
}
