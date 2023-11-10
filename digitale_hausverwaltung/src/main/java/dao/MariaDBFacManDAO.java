package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MariaDBFacManDAO implements FacManDAO{
    // Main Methode ausführen
    public static void main(String[] args) {
        MariaDBFacManDAO mariaDBFacManDAO = new MariaDBFacManDAO();
        mariaDBFacManDAO.createDatabaseFacMan();
        mariaDBFacManDAO.createTableCustomer();
        mariaDBFacManDAO.createTableReading();
    }


    @Override
    public int createDatabaseFacMan() {
        // Erstellung der Datenbank
            String databaseName = "running_gag";
            String jdbcUrl = "jdbc:mariadb://localhost:3307/mysql";
            String user = "root";
            String password = "root";

            try {
                Connection connection = DriverManager.getConnection(jdbcUrl, user, password);

                java.sql.Statement statement = connection.createStatement();
                String createDatabaseSQL = "CREATE DATABASE " + databaseName + ";";
                statement.execute(createDatabaseSQL);

                System.out.println("Datenbank wurde erfolgreich erstellt.");

                statement.close();
                connection.close();
                return 0;
            } catch (Exception e) {
                e.printStackTrace();
                return 1;
            }
    }

    @Override
    public int createTableCustomer() {
        // Erstellung der Customer Tabelle
                String databaseName = "running_gag";
                String tableName = "Customer";
                String jdbcUrl = "jdbc:mariadb://localhost:3307/running_gag";
                String user = "root";
                String password = "root";

                try {
                    Connection connection = DriverManager.getConnection(jdbcUrl, user, password);

                    java.sql.Statement statement = connection.createStatement();

                    String useDatabase = "Use " + databaseName + ";";

                    statement.execute(useDatabase);
                    String createTableSQL = "CREATE TABLE IF NOT EXISTS Customer ("
                            + "ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL, "
                            + "UUID VARCHAR(36) NOT NULL, "
                            + "firstName VARCHAR(50) NOT NULL, "
                            + "lastName VARCHAR(50) NOT NULL)";
                    statement.execute(createTableSQL);

                    System.out.println("Tabelle " + tableName + " wurde erfolgreich erstellt.");

                    statement.close();
                    connection.close();
                    return 0;
                } catch (Exception e) {
                    e.printStackTrace();
                    return 1;
                }
    }

    @Override
    public int createTableReading() {
        // Erstellung der Reading Tabelle
                String databaseName = "running_gag";
                String tableName = "Reading";
                String jdbcUrl = "jdbc:mariadb://localhost:3307/running_gag";
                String user = "root";
                String password = "root";

                try {
                    Connection connection = DriverManager.getConnection(jdbcUrl, user, password);

                    java.sql.Statement statement = connection.createStatement();

                    String useDatabase = "Use " + databaseName + ";";
                    statement.execute(useDatabase);

                    String createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                            + "ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL, "
                            + "UUID VARCHAR(36) NOT NULL, "
                            + "customerId int NOT NULL,"
                            + "dateOfReading date NOT NULL, "
                            + "typeOfReading varchar(50) NOT NULL, "
                            + "meterCount int NOT NULL, "
                            + "comment varchar(50) NOT NULL DEFAULT'', "
                            + "FOREIGN KEY (customerId) REFERENCES Customer(ID))";
                    statement.execute(createTableSQL);

                    System.out.println("Tabelle " + tableName + " wurde erfolgreich erstellt.");

                    statement.close();
                    connection.close();
                    return 0;
                } catch (Exception e) {
                    e.printStackTrace();
                    return 1;
                }
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
