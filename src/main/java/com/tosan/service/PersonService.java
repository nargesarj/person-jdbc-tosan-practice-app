package com.tosan.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tosan.persistence.dao.PersonDao;
import com.tosan.persistence.daoimpl.DaoMySqlImpl;
import com.tosan.persistence.entity.AgeAndGenderAndAverageResult;
import com.tosan.persistence.entity.CountAvgResult;
import com.tosan.persistence.entity.GenderAndAvgResult;
import com.tosan.persistence.entity.Person;

public class PersonService {

	private PersonDao dao = new DaoMySqlImpl();

	public List<String> getPersonInfoListFirstNameStartsWithALetter() {
		List<Person> list = dao.startsWith("first_name", "A");
		List<String> strList = new ArrayList<>();
		for (Person person : list) {
			strList.add(person.toString());
		}
		return strList;
	}

	public List<String> getPersonInfoListFirstNameEndsWithALetter() {
		List<Person> list = dao.endsWith("last_name", "c");
		List<String> strList = new ArrayList<>();
		for (Person person : list) {
			strList.add(person.toString());
		}
		return strList;
	}

	public List<String> getPersonInfoListFirstNameOrLastNameIncludesALetter() {
		List<Person> list = dao.includesOr("first_name", "last_name", "b");
		List<String> strList = new ArrayList<>();
		for (Person person : list) {
			strList.add(person.toString());
		}
		return strList;
	}

	public List<String> getPersonInfoListFirstNameAndLastNameIncludesALetter() {
		List<Person> list = dao.includesAnd("first_name", "last_name", "m");
		List<String> strList = new ArrayList<>();
		for (Person person : list) {
			strList.add(person.toString());
		}
		return strList;
	}

	public List<String> getPersonInfoListFirstNameOrLastNameIncludesALetterAndGreaterThanAge() {
		List<Person> list = dao.includesOrGreaterThan("first_name", "last_name", "m", "datediff(now(), birth_date)/365",
				18);
		List<String> strList = new ArrayList<>();
		for (Person person : list) {
			strList.add(person.toString());
		}
		return strList;
	}

	public String youngestPersonInfo() {
		Person person = dao.max("birth_date");
		String youngestPerson;
		youngestPerson = person.toString();
		return youngestPerson;
	}

	public String oldestPersonInfo() {
		Person person = dao.min("birth_date");
		String oldestPerson;
		oldestPerson = person.toString();
		return oldestPerson;
	}

	public String ageAverage() {
		Double person = dao.avg("birth_date");
		String ageAvg;
		ageAvg = person.toString();
		return ageAvg;
	}

	public int countPersonGreaterThanEighteenYearsOld() {
		int count = dao.countGreaterThan("birth_date", 18);
		return count;
	}

	public List<String> ageAndCountOfPersonInAge() {
		List<CountAvgResult> list = dao.includesAndCount("birth_date");
		List<String> strList = new ArrayList<>();
		for (CountAvgResult countAvgResult : list) {
			strList.add(countAvgResult.toString());
		}
		return strList;
	}

	public List<String> genderAndAverageAgeInGender() {
		List<GenderAndAvgResult> list = dao.includesAndAvg("gender", "birth_date");
		List<String> strList = new ArrayList<>();
		for (GenderAndAvgResult person : list) {
			strList.add(person.toString());
		}
		return strList;
	}

	public List<String> genderAndCountAgeInGender() {
		List<CountAvgResult> list = dao.includesAndCount("gender");
		List<String> strList = new ArrayList<>();
		for (CountAvgResult countAvgResult : list) {
			strList.add(countAvgResult.toString());
		}
		return strList;
	}

	public List<String> ageAndGenderAndAverageScoreInAgeAndGender() {
		List<AgeAndGenderAndAverageResult> list = dao.includesAndAvg("birth_date", "gender", "score");
		List<String> strList = new ArrayList<>();
		for (AgeAndGenderAndAverageResult ageAndGenderAndAverageResult : list) {
			strList.add(ageAndGenderAndAverageResult.toString());
		}
		return strList;
	}

	public List<String> getPersonScoreAndShowStateAndPersonInfo() {
		List<Person> perList = dao.include("score");
		List<String> strList = new ArrayList<>();
		for (Person person : perList) {
			strList.add(person.toString());
		}
		return strList;
	}
	
	@SuppressWarnings("deprecation")
	public void insertDefaultPersonList() {
		List<Person> list = new ArrayList<>();
		list.add(new Person("John", "Johni", new Date(1796, 5, 2), 1, 20));
		list.add(new Person("Faz", "Johni", new Date(1896, 6, 20), 1, 10));
		list.add(new Person("Nol", "Johni", new Date(1996, 1, 6), 2, 8));
		list.add(new Person("Jack", "Johni", new Date(1990, 10, 21), 1, 15));
		list.add(new Person("Sara", "Johni", new Date(2017, 11, 21), 2, 12));
		list.add(new Person("My", "Johni", new Date(2002, 12, 28), 1, 18));
		list.add(new Person("Why", "Johni", new Date(2017, 1, 2), 1, 14));
		list.add(new Person("Edvrd", "Johni", new Date(2000, 4, 6), 2, 19));
		list.add(new Person("John", "Johni", new Date(1740, 5, 10), 2, 12));
		list.add(new Person("July", "Johni", new Date(1885, 8, 4), 1, 17));
		
		dao.save(list);
	}
	
	public void dropAllPersons() {
		dao.drop();
	}
}