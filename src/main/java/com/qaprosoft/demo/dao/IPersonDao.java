package com.qaprosoft.demo.dao;

import java.util.List;

import com.qaprosoft.demo.models.Person;

public interface IPersonDao {
	
	List<Person> getAllPersons();
	void insertPerson(Person person);
	void insertPersons(List<Person> persons);
	
}
