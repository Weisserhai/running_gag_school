package dao;

import java.time.LocalDate;
import java.util.List;

import models.Reading;

public interface ReadingDAO extends DAO<Reading>{

	//CREATE

	int create(String typeofreading,  LocalDate dateofreading,  int metercount,  String comment,  int c_id);

	int create(Reading reading);

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
