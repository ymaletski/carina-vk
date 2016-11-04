package com.qaprosoft.demo.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qaprosoft.demo.dao.IPersonDao;
import com.qaprosoft.demo.models.Person;

public class PersonDaoImpl implements IPersonDao {

	@Override
	public List<Person> getAllPersons() {
		List<Person> list;
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		try {
			list = session.selectList("mappers.getAllPersons");
			session.commit();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public void insertPerson(Person person) {
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		try {
			session.insert("mappers.insertPerson", person);
			session.commit();
		} finally {
			session.close();
		}
	}

	@Override
	public void insertPersons(List<Person> persons) {
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		try {
			session.insert("mappers.insertPersons", persons);
			session.commit();
		} finally {
			session.close();
		}		
	}
	
}
