package com.StudentClient.beans;

public enum Gender {
	
	Male(1), Female(2), Transgender(3);
	
	private Integer value;

	private Gender(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}
	
}
