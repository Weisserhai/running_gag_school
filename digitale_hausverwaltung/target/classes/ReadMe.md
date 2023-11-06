Documentation for resources

“create_database.sql”:
    Command that creates the database “running_gag_database” (drops the old one if it already exists). It creates the tables “customer” and “reading” and inserts data into “customer”. Execute command in MariaDB.

	customer:
        ID (UUID) PK,
        first_name (varchar(50)),
        last_name (varchar(50))

	reading:
        ID (UUID)  PK,
        customerId (UUID) FK,
        dateOfReading (date),
        typeOfReading (varchar(50)),
        meterCount (int),
        comment(varchar(50))
