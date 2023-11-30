package com.tosan.persistence.entity;

public class GenderAndAvgResult {

	private int gender;
	private Double avg;

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

	public GenderAndAvgResult(int gender, Double avg) {
		super();
		this.gender = gender;
		this.avg = avg;
	}

	@Override
	public String toString() {
		return "GenderAndAvgResult [gender=" + gender + ", avg=" + avg + "]";
	}
}
