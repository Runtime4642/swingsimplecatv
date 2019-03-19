package com.catv.dto;

import java.util.List;

public class CustomComboDto {
	private List<String> state;
	private List<String> method; //수금방법
	private List<String> bank;
	private List<String> resudent; //거주구분
	private List<String> area;
	
	public List<String> getArea() {
		return area;
	}
	public void setArea(List<String> area) {
		this.area = area;
	}
	public List<String> getResudent() {
		return resudent;
	}
	public void setResudent(List<String> resudent) {
		this.resudent = resudent;
	}
	public List<String> getState() {
		return state;
	}
	public void setState(List<String> state) {
		this.state = state;
	}
	public List<String> getMethod() {
		return method;
	}
	public void setMethod(List<String> method) {
		this.method = method;
	}
	public List<String> getBank() {
		return bank;
	}
	public void setBank(List<String> bank) {
		this.bank = bank;
	}

	
}
