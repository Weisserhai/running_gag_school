package models;

import java.time.LocalDate;
import java.util.UUID;

public interface Readings {

	UUID getId();
	void setId(UUID id);

	String getTypeofreading();
	void setTypeofreading(String typeofreading);

	LocalDate getDateofreading();
	void setDateofreading(LocalDate dateofreading);

	int getMetercount();
	void setMetercount(int metercount);

	String getComment();
	void setComment(String comment);

	Customers getCustomer();
	void setCustomer(Customers customer);
}
