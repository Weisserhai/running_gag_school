package dao;

import java.util.List;
import java.util.UUID;

import models.Customer;

public interface CustomerDAO extends DAO<Customer>{



// CREATE


	UUID create(String firstname,  String lastname);


	UUID create(Customer customer);

//READ

	Customer get(UUID id);

	List<Customer> getAll();

//UPDATE

	boolean update(UUID id, String firstname,  String lastname);


	boolean update(Customer customer);

//DELETE

	boolean delete(UUID id);
}
