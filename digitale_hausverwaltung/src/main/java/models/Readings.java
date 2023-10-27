package models;

import java.time.LocalDate;

public interface Readings {

	int getId();
	void setId(int id);

	String getArtofreading();
	void setArtofreading(String artofreading);

	LocalDate getDateofreading();
	void setDateofreading(LocalDate dateofreading);

	int getMetercount();
	void setMetercount(int metercount);

	String getComment();
	void setComment(String comment);

	Customers getCustomer();
	void setCustomer(Customers customer);



}
