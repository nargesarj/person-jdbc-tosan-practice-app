package com.tosan.presentation;

import java.util.List;

import com.tosan.application.PersonService;

public class MyApp {

	public static void main(String[] args) {

		System.out.println("");
		System.out.println("--------------------------------------------------------------");
		System.out.println("getPersonInfoListFirstNameStartsWithALetter");
		PersonService personService = new PersonService();
		List<String> personInfoList = personService.getPersonInfoListFirstNameStartsWithALetter();
		for (String string : personInfoList)
			System.out.println(string);

		System.out.println("");
		System.out.println("--------------------------------------------------------------");
		System.out.println("getPersonInfoListFirstNameEndsWithALetter");
		personService = new PersonService();
		personInfoList = personService.getPersonInfoListFirstNameEndsWithALetter();
		for (String string : personInfoList)
			System.out.println(string);

		System.out.println("");
		System.out.println("--------------------------------------------------------------");
		System.out.println("getPersonInfoListFirstNameOrLastNameIncludesALetter");
		personService = new PersonService();
		personInfoList = personService.getPersonInfoListFirstNameOrLastNameIncludesALetter();
		for (String string : personInfoList)
			System.out.println(string);

		System.out.println("");
		System.out.println("--------------------------------------------------------------");
		System.out.println("getPersonInfoListFirstNameAndLastNameIncludesALetter");
		personService = new PersonService();
		personInfoList = personService.getPersonInfoListFirstNameAndLastNameIncludesALetter();
		for (String string : personInfoList)
			System.out.println(string);

		System.out.println("");
		System.out.println("--------------------------------------------------------------");
		System.out.println("getPersonInfoListFirstNameOrLastNameIncludesALetterAndGreaterThanAge");
		personService = new PersonService();
		personInfoList = personService.getPersonInfoListFirstNameOrLastNameIncludesALetterAndGreaterThanAge();
		for (String string : personInfoList)
			System.out.println(string);

		System.out.println("");
		System.out.println("--------------------------------------------------------------");
		System.out.println("youngestPersonInfo");
		personService = new PersonService();
		String personInfo = personService.youngestPersonInfo();
		System.out.println(personInfo);

		System.out.println("");
		System.out.println("--------------------------------------------------------------");
		System.out.println("oldestPersonInfo");
		personService = new PersonService();
		String personInfo1 = personService.oldestPersonInfo();
		System.out.println(personInfo1);

		System.out.println("");
		System.out.println("--------------------------------------------------------------");
		System.out.println("ageAverage");
		personService = new PersonService();
		String personInfo2 = personService.ageAverage();
		System.out.println(personInfo2);

		System.out.println("");
		System.out.println("--------------------------------------------------------------");
		System.out.println("countPersonGreaterThanEighteenYearsOld");
		personService = new PersonService();
		int personInfo3 = personService.countPersonGreaterThanEighteenYearsOld();
		System.out.println(personInfo3);

		System.out.println("");
		System.out.println("--------------------------------------------------------------");
		System.out.println("getPersonAgeAndCountPersonInThatAge");
		personService = new PersonService();
		List<String> personInfo4 = personService.ageAndCountOfPersonInAge();
		System.out.println(personInfo4);

		System.out.println("");
		System.out.println("--------------------------------------------------------------");
		System.out.println("getGenderAndAverageAgeInThatGender");
		personService = new PersonService();
		personInfoList = personService.genderAndAverageAgeInGender();
		for (String string : personInfoList)
			System.out.println(string);

		System.out.println("");
		System.out.println("--------------------------------------------------------------");
		System.out.println("getPersonGenderAndCountPersonInThatGender");
		personService = new PersonService();
		personInfoList = personService.genderAndCountAgeInGender();
		for (String string : personInfoList)
			System.out.println(string);

		System.out.println("");
		System.out.println("--------------------------------------------------------------");
		System.out.println("getPersonGenderAndCountPersonInThatGender");
		personService = new PersonService();
		personInfoList = personService.ageAndGenderAndAverageScoreInAgeAndGender();
		for (String string : personInfoList)
			System.out.println(string);

		System.out.println("");
		System.out.println("--------------------------------------------------------------");
		System.out.println("getPersonScoreAndShowStateAndPersonInfo");
		personService = new PersonService();
		personInfoList = personService.getPersonScoreAndShowStateAndPersonInfo();
		for (String string : personInfoList)
			System.out.println(string);

	}
}