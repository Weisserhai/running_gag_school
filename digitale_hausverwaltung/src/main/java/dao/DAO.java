package dao;

import java.util.List;

public interface DAO<T> {

	int create(T t);

	T get(int i);

	List<T> getAll();

	boolean update(T t);

	boolean delete(int i);
}
