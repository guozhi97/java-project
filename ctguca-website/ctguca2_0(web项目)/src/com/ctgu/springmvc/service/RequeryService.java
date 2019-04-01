package com.ctgu.springmvc.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ctgu.springmvc.dao.AnswerDao;
import com.ctgu.springmvc.dao.DBSession;
import com.ctgu.springmvc.dao.RequeryDao;
import com.ctgu.springmvc.entity.Answer;
import com.ctgu.springmvc.entity.Requery;

@Repository
public class RequeryService implements RequeryDao {

	private SqlSession session;
	
	public RequeryService() {
	}

	@Override
	public boolean insert(Requery v) {
		session=DBSession.getSession();
		session.insert("Requery.insert",v.getContent());
		session.commit();
		session.close();
		return true;
	}

	@Override
	public boolean remove(Integer rid) {
		session=DBSession.getSession();
		session.insert("Requery.remove",rid);
		session.commit();
		session.close();
		return false;
	}

	@Override
	public boolean update(Integer k, Requery v) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Requery> getAll() {
		Collection<Requery> list=null;
		session=DBSession.getSession();
		list=session.selectList("Requery.getall");
		session.commit();
		session.close();
		return list;
	}



}
