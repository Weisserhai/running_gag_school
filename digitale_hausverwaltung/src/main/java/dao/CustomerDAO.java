package dao;

import java.util.List;

import models.Customer;

public interface CustomerDAO extends DAO<Customer>{



// CREATE


	int create(String firstname,  String lastname);


	int create(Customer customer);

//READ

	Customer get(int id);

	List<Customer> getAll();

//UPDATE

	boolean update(int id, String firstname,  String lastname);


	boolean update(Customer customer);

//DELETE

	boolean delete(int id);
}
