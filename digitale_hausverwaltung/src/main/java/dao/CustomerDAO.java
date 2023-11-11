package dao;

import java.util.List;

import models.Customer;

public interface CustomerDAO extends DAO<Customer>
{
	// CREATE

		int create(String firstname,  String lastname); // return value is customer id

		int create(Customer customer); // return value is customer id

	// READ

		Customer get(int id);

		List<Customer> getAll();

	// UPDATE

		boolean update(int id, String firstname,  String lastname);

		boolean update(Customer customer);

	// DELETE

		boolean delete(int id);
}
