package com.tosan.persistence.dao;

import java.util.List;

import com.tosan.persistence.entity.Person;

public interface PersonDao<T, V, E, K, L> {

	List<T> startsWith(String column, String searchItem);

	List<T> endsWith(String column, String searchItem);

	List<T> includesOr(String firstColumn, String secondColumn, String searchItem);

	List<T> includesAnd(String firstColumn, String secondColumn, String searchItem);

	List<T> includesOrGreaterThan(String firstColumn, String secondColumn, String searchItem, String thirdColumn,
			int intSearchItem);

	Person max(String column);

	Person min(String column);

	Double avg(String column);

	int countGreaterThan(String column, int intSearchItem);

	List<V> includesAndCount(String column);

	List<E> includesAndAvg(String firstColumn, String secondColumn);

	List<K> includesAndCount(int column);

	List<L> includesAndAvg(String firstColumn, String secondColumn, String thirdColumn);

	List<T> include(String column);

	void save(List<Person> person);

	void drop();
}
