package com.tosan.persistence;

public class GenderAndCntResult {

	private int gender;
	private int cnt;

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public GenderAndCntResult(int gender, int cnt) {
		super();
		this.gender = gender;
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "GenderAndCntResult [gender=" + gender + ", cnt=" + cnt + "]";
	}
}
