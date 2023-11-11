package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import com.prop_manage.LoggerBackend;


public class MariaDBFacManDAO implements FacManDAO{
    public static int createDatabaseStructure()
    {
        MariaDBFacManDAO entity = new MariaDBFacManDAO();

        if (entity.createDatabaseFacMan() == 1)
        {
            LoggerBackend.LOGGER.log(Level.WARNING, "An error occurred in \"createDatabaseFacMan\"");

            return 1;
        }

        if (entity.createTableCustomer() == 1)
        {
            LoggerBackend.LOGGER.log(Level.WARNING, "An error occurred in \"createTableCustomer\"");

            return 1;
        }

        if (entity.createTableReading() == 1)
        {
            LoggerBackend.LOGGER.log(Level.WARNING, "An error occurred in \"createTableReading\"");

            return 1;
        }

        return 0;
    }


    @Override
    public int createDatabaseFacMan() // Create new database
    { 
        String databaseName = "running_gag";
        String jdbcUrl = "jdbc:mariadb://localhost:3307/mysql"; // connect to mariadb (not a database!) to create a new database
        String user = "root";
        String password = "root";

        try 
        {
            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);

            java.sql.Statement statement = connection.createStatement();
            String createDatabaseSQL = "CREATE DATABASE " + databaseName + ";";
            statement.execute(createDatabaseSQL);

            LoggerBackend.LOGGER.log(Level.INFO, "Created new database \"" + databaseName + "\"");

            statement.close();
            connection.close();

            return 0;
        } 
        catch (Exception error) 
        {
            LoggerBackend.LOGGER.log(Level.SEVERE, "An error occurred: " + error.getMessage());
            error.printStackTrace();

            return 1;
        }
    }

    @Override
    public int createTableCustomer() // Create table Customer
    { 
        String databaseName = "running_gag";
        String jdbcUrl = "jdbc:mariadb://localhost:3307/running_gag";
        String user = "root";
        String password = "root";

        try 
        {
            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);

            java.sql.Statement statement = connection.createStatement();

            String useDatabase = "Use " + databaseName + ";";

            statement.execute(useDatabase);
            String createTableSQL = "CREATE TABLE Customer ("
                    + "ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL, "
                    + "UUID VARCHAR(36) NOT NULL, "
                    + "Firstname VARCHAR(50) NOT NULL, "
                    + "Lastname VARCHAR(50) NOT NULL)";

            statement.execute(createTableSQL);

            LoggerBackend.LOGGER.log(Level.INFO, "Created new table \"Customer\"");

            statement.close();
            connection.close();

            return 0;
        } 
        catch (SQLException error) 
        {
            if (error.getSQLState().equals("X0Y32")) // Table already exists
            {
                LoggerBackend.LOGGER.log(Level.SEVERE, "Table \"Customer\" already exists.");
            } 
            else 
            {
                LoggerBackend.LOGGER.log(Level.SEVERE, "An error occurred: " + error.getMessage());
                error.printStackTrace();
            }

            return 1;
        }
    }

    @Override
    public int createTableReading() // Create table Reading
    { 
        String databaseName = "running_gag";
        String jdbcUrl = "jdbc:mariadb://localhost:3307/running_gag";
        String user = "root";
        String password = "root";

        try 
        {
            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);

            java.sql.Statement statement = connection.createStatement();

            String useDatabase = "Use " + databaseName + ";";
            statement.execute(useDatabase);

            String createTableSQL = "CREATE TABLE Reading ("
                    + "ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL, "
                    + "UUID VARCHAR(36) NOT NULL, "
                    + "customerId int NOT NULL,"
                    + "dateOfReading date NOT NULL, "
                    + "typeOfReading varchar(50) NOT NULL, "
                    + "meterCount int NOT NULL, "
                    + "comment varchar(50) NOT NULL DEFAULT'', "
                    + "FOREIGN KEY (customerId) REFERENCES Customer(ID))";
            statement.execute(createTableSQL);

            LoggerBackend.LOGGER.log(Level.INFO, "Created new table \"Reading\"");

            statement.close();
            connection.close();

            return 0;
        } 
        catch (SQLException error) 
        {
            if (error.getSQLState().equals("X0Y32")) // Table already exists
            {
                LoggerBackend.LOGGER.log(Level.SEVERE, "Table \"Reading\" already exists.");
            } 
            else 
            {
                LoggerBackend.LOGGER.log(Level.SEVERE, "An error occurred: " + error.getMessage());
                error.printStackTrace();
            }

            return 1;
        }
    }


    public static Connection connectToMariaDB() 
    {
        Connection connection = null;

        String jdbcUrl = "jdbc:mariadb://localhost:3307/running_gag";
        String user = "root";
        String password = "root";

        try 
        {
            connection = DriverManager.getConnection(jdbcUrl, user, password);
        } 
        catch (SQLException error) 
        {
            LoggerBackend.LOGGER.log(Level.SEVERE, "An error occurred: " + error.getMessage());
            error.printStackTrace();
        }

        return connection;
    }
}
