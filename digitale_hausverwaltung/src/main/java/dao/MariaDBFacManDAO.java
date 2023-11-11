package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import com.prop_manage.ProgrammLogger;


public class MariaDBFacManDAO implements FacManDAO{
    public static int main(String[] args)
    {
        MariaDBFacManDAO mariaDBFacManDAO = new MariaDBFacManDAO();
        if (mariaDBFacManDAO.createDatabaseFacMan() == 1)
        {
            ProgrammLogger.LOGGER.log(Level.WARNING, "An error occurred in \"createDatabaseFacMan\"");

            return 1;
        }

        if (mariaDBFacManDAO.createTableCustomer() == 1)
        {
            ProgrammLogger.LOGGER.log(Level.WARNING, "An error occurred in \"createTableCustomer\"");

            return 1;
        }

        if (mariaDBFacManDAO.createTableReading() == 1)
        {
            ProgrammLogger.LOGGER.log(Level.WARNING, "An error occurred in \"createTableReading\"");

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

            ProgrammLogger.LOGGER.log(Level.INFO, "Created new database \"" + databaseName + "\"");

            statement.close();
            connection.close();

            return 0;
        } 
        catch (Exception error) 
        {
            ProgrammLogger.LOGGER.log(Level.SEVERE, "An error occurred: " + error.getMessage());
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
                    + "firstName VARCHAR(50) NOT NULL, "
                    + "lastName VARCHAR(50) NOT NULL)";

            statement.execute(createTableSQL);

            ProgrammLogger.LOGGER.log(Level.INFO, "Created new table \"Customer\"");

            statement.close();
            connection.close();

            return 0;
        } 
        catch (SQLException error) 
        {
            if (error.getSQLState().equals("X0Y32")) // Table already exists
            {
                ProgrammLogger.LOGGER.log(Level.SEVERE, "Table \"Customer\" already exists.");
            } 
            else 
            {
                ProgrammLogger.LOGGER.log(Level.SEVERE, "An error occurred: " + error.getMessage());
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

            ProgrammLogger.LOGGER.log(Level.INFO, "Created new table \"Reading\"");

            statement.close();
            connection.close();

            return 0;
        } 
        catch (SQLException error) 
        {
            if (error.getSQLState().equals("X0Y32")) // Table already exists
            {
                ProgrammLogger.LOGGER.log(Level.SEVERE, "Table \"Reading\" already exists.");
            } 
            else 
            {
                ProgrammLogger.LOGGER.log(Level.SEVERE, "An error occurred: " + error.getMessage());
                error.printStackTrace();
            }

            return 1;
        }
    }


    public static Connection connectToMariaDB() 
    {

        System.out.println("ich mach was");

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
            ProgrammLogger.LOGGER.log(Level.SEVERE, "An error occurred: " + error.getMessage());
            error.printStackTrace();
        }

        return connection;
    }
}
