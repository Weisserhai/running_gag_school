package dao;

import java.util.List;

import models.Customer;

public interface CustomerDAO extends DAO<Customer>
{
	// CREATE

		int create(String firstname,  String lastname); // return value is customer id | error value is "-1" not "1"

		int create(Customer customer); // return value is customer id | error value is "-1" not "1"

	// READ

		Customer get(int id);

		List<Customer> getAll();

	// UPDATE

		boolean update(int id, String firstname,  String lastname);

		boolean update(Customer customer);

	// DELETE

		boolean delete(int id);
}
