package com.prop_manage;

import java.time.LocalDate;
import java.util.List;

import dao.*;
import models.*;

public class Main 
{
    public static void main(String[] args) 
    {
        // MariaDBFacManDAO.main(args);
        // Customer customer = new Customer("test", "test2");
        
        // MariaDBCustomerDAO con = new MariaDBCustomerDAO();
        // con.create(customer);

        // List<Customer> customerList = con.getAll();

        // for (Customer output : customerList)
        // {
        //     System.out.println(output.getId());
        //     System.out.println(output.getUUID());
        //     System.out.println(output.getFirstname());
        //     System.out.println(output.getLastname());
        // }

        // con.update(5, "sahjdflnsadf", "jsdhfjlksbadfnsblakdf");
        // con.delete(4);


        MariaDBReadingDAO entity = new MariaDBReadingDAO();

        // entity.create("Test", LocalDate.parse("2023-11-11"), 1, "sdhjf", 2);
        // entity.create("Test", LocalDate.parse("2023-11-11"), 1, "ababababababababababababababababababababababababababababababbababababababababa", 2);
        // Reading reading = entity.get(1);

        // List<Reading> readingList = entity.getAll();

        // List<Reading> readingList = entity.getAllFromCustomer(2);

        List<Reading> readingList = entity.getReadingsInit2Years();
        
        
        for (Reading reading : readingList)
        {
            System.out.println(reading.getUUID());
            System.out.println(reading.getCustomerID());
            System.out.println(reading.getDateofreading());
            System.out.println(reading.getTypeofreading());
            System.out.println(reading.getMeterCount());
            System.out.println(reading.getComment());
        }


    }
}