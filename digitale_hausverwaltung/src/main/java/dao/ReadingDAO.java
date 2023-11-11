package dao;

import java.time.LocalDate;
import java.util.List;

import models.Reading;

public interface ReadingDAO extends DAO<Reading>{

	//CREATE

	int create(String typeOfReading,  LocalDate dateOfReading,  int metercount,  String comment,  int customerId); // return value is reaing id | error value is "-1" not "1"

	int create(Reading reading); // return value is reding id | error value is "-1" not "1"

	//READ

	Reading get(int id);

	List<Reading> getAll();

	List<Reading> getAllFromCustomer(int cust_id);

	List<Reading> getReadingsInit2Years();

	List<Reading> getReadingsForCustomer(int cust_id,  LocalDate start,  LocalDate end);

	//UPDATE

	boolean update(Reading reading);

	boolean update(int id, String typeofreading,  LocalDate dateofreading,  int metercount,  String comment);

	//DELETE

	boolean delete(int id);
}
