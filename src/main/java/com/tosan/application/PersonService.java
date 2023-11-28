package com.tosan.application;

import java.util.ArrayList;
import java.util.List;

import com.tosan.persistence.AgeAndGenderAndAverageResult;
import com.tosan.persistence.CountAvgResult;
import com.tosan.persistence.Dao;
import com.tosan.persistence.DaoMySqlImpl;
import com.tosan.persistence.GenderAndAvgResult;
import com.tosan.persistence.Person;

public class PersonService {

	private Dao dao = new DaoMySqlImpl();

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
}