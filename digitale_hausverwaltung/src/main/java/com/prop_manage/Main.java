package com.prop_manage;

import java.util.List;

import dao.*;
import models.*;

public class Main 
{
    public static void main(String[] args) 
    {
        // MariaDBFacManDAO.main(args);
        // Customer customer = new Customer("test", "test2");
        
        MariaDBCustomerDAO con = new MariaDBCustomerDAO();
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
        con.delete(4);
    }
}