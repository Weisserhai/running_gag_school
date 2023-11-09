package dao;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import models.Reading;

public interface ReadingDAO extends DAO<Reading>{

	//CREATE

	int create(String typeofreading,  LocalDate dateofreading,  int metercount,  String comment,  UUID c_id);


	int create(Reading reading);


	//READ

	Reading get(UUID id);


	List<Reading> getAll();



	List<Reading> getAllFromCustomer(UUID cust_id);


	List<Reading> getReadingsInit2Years();


	List<Reading> getReadingsForCustomer(UUID cust_id,  LocalDate start,  LocalDate end);


	//UPDATE

	boolean update(Reading reading);


	boolean update(UUID id, String typeofreading,  LocalDate dateofreading,  int metercount,  String comment);

	//DELETE

	boolean delete(UUID id);
}
