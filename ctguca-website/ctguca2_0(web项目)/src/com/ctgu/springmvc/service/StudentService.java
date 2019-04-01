package com.ctgu.springmvc.service;

import java.util.Collection;
import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ctgu.springmvc.dao.DBSession;
import com.ctgu.springmvc.dao.StudentDao;
import com.ctgu.springmvc.entity.Student;

@Repository
public class StudentService implements StudentDao {

	private SqlSession sqlSession;
	
	public StudentService() {
	}

	@Override
	public boolean insert(Student v) {
		System.out.println("service:insert");
		sqlSession = DBSession.getSession();
		sqlSession.insert("Student.insert",v);
		sqlSession.commit();
		sqlSession.close();
		System.out.println("service:insert end");
		return true;
	}

	@Override
	public boolean remove(String k) {
		sqlSession = DBSession.getSession();
		sqlSession.delete("Student.remove", k);
		sqlSession.commit();
		sqlSession.close();
		return true;
	}

	@Override
	public boolean update(String k, Student v) {
		return false;
	}

	@Override
	public Collection<Student> getAll() {
		System.out.println("service getall");
		sqlSession=DBSession.getSession();
		List<Student> list=  sqlSession.selectList("Student.getAll");
		sqlSession.commit();
		sqlSession.close();
		return list;
	}

	//查找有着不同意愿的人
	public Collection<Student> getSomeOne(String will) {
		System.out.println("service getsomeone");
		sqlSession=DBSession.getSession();
		List<Student> list= sqlSession.selectList("Student.findwill",will);
		sqlSession.commit();
		sqlSession.close();
		return list;
	}
	
	public boolean isUniqueUsername(String sid){
		System.out.println("isUniqueUsername");
		sqlSession=DBSession.getSession();
		boolean is = sqlSession.selectOne("Student.isUniqueUsername", sid);
		sqlSession.commit();
		System.out.println("isUniqueUsername return:"+is);
		sqlSession.close();
		return is;
	}

}
