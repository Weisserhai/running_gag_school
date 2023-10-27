package dao;

public interface ReadingDAO extends DAO<Reading>{

	//CREATE

	int create( String artofreading,  LocalDate dateofreading,  int metercount,  String comment,  int c_id);


	int create(Reading reading);


	//READ

	Reading get( int id);


	List<Reading> getAll();



	List<Reading> getAllFromCustomer( int id);


	List<Reading> getReadingsInit2Years();


	List<Reading> getReadingsForCustomer( int id,  LocalDate start,  LocalDate end);


	//UPDATE

	boolean update(Reading reading);


	boolean update( String artofreading,  LocalDate dateofreading,  Long metercount,  String comment);

	//DELETE

	boolean delete(int id);


}
