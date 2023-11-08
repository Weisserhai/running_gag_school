package models;

import java.util.UUID;

public interface Customers {

	UUID getId();
	void setId(UUID id);

	String getFirstname();
	void setFirstname(String firstname);

	String getLastname();
	void setLastname(String lastname);



}
