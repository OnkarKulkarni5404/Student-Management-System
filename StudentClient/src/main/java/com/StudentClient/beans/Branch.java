package com.StudentClient.beans;
public enum Branch {

	CS(1), IT(2), EC(3), CE(4), ME(5);
	
	private Integer value;

	private Branch(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}
}
