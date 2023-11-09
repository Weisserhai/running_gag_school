package dao;

import java.util.List;
import java.util.UUID;

public interface DAO<T> {

	int create(T t);

	T get(UUID i);

	List<T> getAll();

	boolean update(T t);

	boolean delete(UUID i);
}
