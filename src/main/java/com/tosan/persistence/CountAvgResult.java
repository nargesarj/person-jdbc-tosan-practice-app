package com.tosan.persistence;

public class CountAvgResult {

	private int cnt;

	private Double avg;

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public Double getAvg() {
		return avg;
	}

	public void setAvg(Double avg) {
		this.avg = avg;
	}

	public CountAvgResult(int cnt, Double avg) {
		this.cnt = cnt;
		this.avg = avg;
	}

	public CountAvgResult() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CountAvgResult [cnt=" + cnt + ", avg=" + avg + "]";
	}

}