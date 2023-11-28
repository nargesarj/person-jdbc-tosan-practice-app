package com.tosan.persistence;

public class AgeAndGenderAndAverageResult {
	private Double age;
	private int gender;
	private Double avg;

	public Double getAge() {
		return age;
	}

	public void setAge(Double age) {
		this.age = age;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Double getAvg() {
		return avg;
	}

	public void setAvg(Double avg) {
		this.avg = avg;
	}

	public AgeAndGenderAndAverageResult(Double age, int gender, Double avg) {
		super();
		this.age = age;
		this.gender = gender;
		this.avg = avg;
	}

	@Override
	public String toString() {
		return "AgeAndGenderAndAverageResult [age=" + age + ", gender=" + gender + ", avg=" + avg + "]";
	}
}
