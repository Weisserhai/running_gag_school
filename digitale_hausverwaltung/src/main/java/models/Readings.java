package models;

import java.time.LocalDate;

public interface Readings 
{

	int getId();
	void setId(int id);

	String getTypeofreading();
	void setTypeofreading(String typeOfReading);

	LocalDate getDateofreading();
	void setDateofreading(LocalDate dateOfReading);

	int getMeterCount();
	void setMeterCount(int meterCount);

	String getComment();
	void setComment(String comment);

	Customers getCustomer();
	void setCustomer(Customers customer);
}
