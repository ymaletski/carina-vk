package com.qaprosoft.demo.models;

public class Person {
	
	private String name;
	private String surname;
	private String city;
	
	public Person(){
		
	}
	
	public Person(String name, String surname){
		this.name = name;
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}
