package com.alarmsystem.domain;

import java.sql.Timestamp;

public class Temperature {

	int no;
	int tempFrom;
	int tempTo;
	Timestamp inputDate;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getTempFrom() {
		return tempFrom;
	}
	public void setTempFrom(int tempFrom) {
		this.tempFrom = tempFrom;
	}
	public int getTempTo() {
		return tempTo;
	}
	public void setTempTo(int tempTo) {
		this.tempTo = tempTo;
	}
	public Timestamp getInputDate() {
		return inputDate;
	}
	public void setInputDate(Timestamp inputDate) {
		this.inputDate = inputDate;
	}
}
