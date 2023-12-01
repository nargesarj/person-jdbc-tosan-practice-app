package com.tosan.persistence.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tosan.persistence.dao.PersonDao;
import com.tosan.persistence.entity.AgeAndGenderAndAverageResult;
import com.tosan.persistence.entity.CountAvgResult;
import com.tosan.persistence.entity.GenderAndAvgResult;
import com.tosan.persistence.entity.GenderAndCntResult;
import com.tosan.persistence.entity.Person;

public class DaoMySqlImpl implements
		PersonDao<Person, CountAvgResult, GenderAndAvgResult, GenderAndCntResult, AgeAndGenderAndAverageResult> {


	public List<Person> startsWith(String column, String searchItem) {
		List<Person> list = new ArrayList<>();
		try {
			Statement statement = JdbcConnection.getJdbcConnection().statement;
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM persons WHERE " + column + " LIKE '" + searchItem + "%'");
			while (resultSet.next()) {
				list.add(new Person(resultSet.getLong("id"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getDate("birth_date"), resultSet.getInt("gender"),
						resultSet.getInt("score")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public List<Person> endsWith(String column, String searchItem) {
		List<Person> list = new ArrayList<>();
		try {
			Statement statement = JdbcConnection.getJdbcConnection().statement;
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM persons WHERE " + column + " LIKE '%" + searchItem + "';");
			while (resultSet.next()) {
				list.add(new Person(resultSet.getLong("id"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getDate("birth_date"), resultSet.getInt("gender"),
						resultSet.getInt("score")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public List<Person> includesOr(String firstColumn, String secondColumn, String searchItem) {
		List<Person> list = new ArrayList<>();
		try {
			Statement statement = JdbcConnection.getJdbcConnection().statement;
			ResultSet resultSet = statement.executeQuery("SELECT * FROM persons WHERE " + firstColumn + " OR "
					+ secondColumn + " LIKE '%" + searchItem + "%';");
			while (resultSet.next()) {
				list.add(new Person(resultSet.getLong("id"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getDate("birth_date"), resultSet.getInt("gender"),
						resultSet.getInt("score")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public List<Person> includesAnd(String firstColumn, String secondColumn, String searchItem) {
		List<Person> list = new ArrayList<>();
		try {
			Statement statement = JdbcConnection.getJdbcConnection().statement;
			String sql = "SELECT * FROM persons WHERE " + firstColumn + "  LIKE '%" + searchItem + "%' AND "
					+ secondColumn + " LIKE '%" + searchItem + "%';";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				list.add(new Person(resultSet.getLong("id"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getDate("birth_date"), resultSet.getInt("gender"),
						resultSet.getInt("score")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public List<Person> includesOrGreaterThan(String firstColumn, String secondColumn, String searchItem,
			String thirdColumn, int intSearchItem) {
		List<Person> list = new ArrayList<>();
		try {
			Statement statement = JdbcConnection.getJdbcConnection().statement;
			String sql = "SELECT * FROM persons WHERE " + firstColumn + " LIKE '%" + searchItem + "%'" + " OR "
					+ secondColumn + " LIKE '%" + searchItem + "%' AND " + thirdColumn + " > " + intSearchItem + ";";
			ResultSet resultSet = statement.executeQuery(sql);
			// System.out.println(sql);
			while (resultSet.next()) {
				list.add(new Person(resultSet.getLong("id"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getDate("birth_date"), resultSet.getInt("gender"),
						resultSet.getInt("score")));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public Person max(String column) {
		Person max = new Person();
		try {
			Statement statement = JdbcConnection.getJdbcConnection().statement;
			String sql = "SELECT * FROM persons WHERE " + column + " = (SELECT MAX(" + column + ") FROM persons)";
			ResultSet resultSet = statement.executeQuery(sql);
			// System.out.println(sql);
			resultSet.next();
			max = new Person(resultSet.getLong("id"), resultSet.getString("first_name"),
					resultSet.getString("last_name"), resultSet.getDate("birth_date"), resultSet.getInt("gender"),
					resultSet.getInt("score"));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return max;
	}

	public Person min(String column) {
		Person min = new Person();
		try {
			Statement statement = JdbcConnection.getJdbcConnection().statement;
			ResultSet resultSet = statement.executeQuery(
					"SELECT * FROM persons WHERE " + column + " = (SELECT MIN(" + column + ") FROM persons);");
			resultSet.next();
			min = new Person(resultSet.getLong("id"), resultSet.getString("first_name"),
					resultSet.getString("last_name"), resultSet.getDate("birth_date"), resultSet.getInt("gender"),
					resultSet.getInt("score"));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return min;
	}

	public Double avg(String column) {
		Double avg;
		try {
			Statement statement = JdbcConnection.getJdbcConnection().statement;
			String sql = "SELECT AVG(ages.age) AS avg FROM (SELECT datediff(now(), " + column
					+ ")/365 AS age FROM persons) ages;";
			ResultSet resultSet = statement.executeQuery(sql);
			System.out.println(sql);
			resultSet.next();
			avg = resultSet.getDouble("avg");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return avg;
	}

	@Override
	public int countGreaterThan(String column, int intSearchItem) {
		int count;
		try {
			Statement statement = JdbcConnection.getJdbcConnection().statement;
			ResultSet resultSet = statement.executeQuery(
					"SELECT COUNT(filtered.finalAge) AS count FROM (SELECT ages.age AS finalAge FROM (SELECT (datediff(now(), "
							+ column + ") / 365) AS age FROM persons ) ages WHERE ages.age > " + intSearchItem
							+ ") filtered;");
			resultSet.next();
			count = resultSet.getInt("count");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return count;
	}

	@Override
	public List<CountAvgResult> includesAndCount(String column) {
		List<CountAvgResult> list = new ArrayList<>();
		try {
			Statement statement = JdbcConnection.getJdbcConnection().statement;
			String sql = "SELECT ages.age AS age, COUNT(ages.age) AS cnt FROM(SELECT (datediff(now(), " + column
					+ ") / 365)AS age FROM persons) ages GROUP BY ages.age;";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				list.add(new CountAvgResult(resultSet.getInt("cnt"), resultSet.getDouble("age")));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public List<GenderAndAvgResult> includesAndAvg(String firstColumn, String secondColumn) {
		List<GenderAndAvgResult> list = new ArrayList<>();
		try {
			Statement statement = JdbcConnection.getJdbcConnection().statement;
			String sql = "SELECT " + firstColumn + ", AVG(datediff(now(), " + secondColumn
					+ ") / 365) AS avg FROM persons GROUP BY " + firstColumn + ";";
			ResultSet resultSet = statement.executeQuery(sql);
			System.out.println(sql);
			while (resultSet.next()) {
				list.add(new GenderAndAvgResult(resultSet.getInt("gender"), resultSet.getDouble("avg")));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public List<GenderAndCntResult> includesAndCount(int column) {
		List<GenderAndCntResult> list = new ArrayList<>();
		try {
			Statement statement = JdbcConnection.getJdbcConnection().statement;
			String sql = "SELECT " + column + ", COUNT(" + column + ") AS count FROM persons GROUP BY " + column + ";";
			ResultSet resultSet = statement.executeQuery(sql);
			System.out.println(sql);
			while (resultSet.next()) {
				list.add(new GenderAndCntResult(resultSet.getInt("gender"), resultSet.getInt("cnt")));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public List<AgeAndGenderAndAverageResult> includesAndAvg(String firstColumn, String secondColumn,
			String thirdColumn) {
		List<AgeAndGenderAndAverageResult> list = new ArrayList<>();
		try {
			Statement statement = JdbcConnection.getJdbcConnection().statement;
			ResultSet resultSet = statement.executeQuery(
					"SELECT datediff(now(), " + firstColumn + " ) / 365 AS age ," + secondColumn + ",AVG(" + thirdColumn
							+ ") AS avg FROM persons GROUP BY " + firstColumn + " ," + secondColumn + ";");
			while (resultSet.next()) {
				list.add(new AgeAndGenderAndAverageResult(resultSet.getDouble("age"), resultSet.getInt("gender"),
						resultSet.getDouble("avg")));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public List<Person> include(String column) {
		List<Person> list = new ArrayList<>();
		try {
			Statement statement = JdbcConnection.getJdbcConnection().statement;
			ResultSet resultSet = statement.executeQuery("SELECT *,CASE WHEN " + column + " > 10 THEN 'Passed' WHEN "
					+ column + " < 30 THEN 'Failes' END AS 'State' FROM persons;");
			while (resultSet.next()) {
				list.add(new Person(resultSet.getLong("id"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getDate("birth_date"), resultSet.getInt("gender"),
						resultSet.getInt("score")));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public void save(List<Person> list) {
		for (Person item : list) {
			String sql = "INSERT INTO persons (first_name, last_name, birth_date, gender, score) VALUES ('"
					+ item.getFirstName() + "', '" + item.getLastName() + "', '" + item.getBirthdate() + "', '"
					+ item.getGender() + "', '" + item.getScore() + "');";
			System.out.println(sql);
			try {
				JdbcConnection.getJdbcConnection().statement.executeUpdate(sql);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void drop() {
		String sql = "Truncate persons";
		System.out.println(sql);
		try {
			JdbcConnection.getJdbcConnection().statement.executeUpdate(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
