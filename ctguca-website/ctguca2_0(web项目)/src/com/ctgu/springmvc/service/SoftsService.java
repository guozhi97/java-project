package com.ctgu.springmvc.service;

import java.util.Collection;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ctgu.springmvc.dao.DBSession;
import com.ctgu.springmvc.dao.SoftsDao;
import com.ctgu.springmvc.entity.Answer;
import com.ctgu.springmvc.entity.Softs;

@Repository
public class SoftsService implements SoftsDao {

	private SqlSession session;
	
	public SoftsService() {
		
	}
	
	public Collection<Softs> find(String name){
		System.out.println("softs:find");
		session=DBSession.getSession();
		Collection<Softs> list=session.selectList("Softs.find",name);
		session.commit();
		session.close();
		return list;
	}


	@Override
	public boolean insert(Softs v) {
		System.out.println("softs:insert");
		session=DBSession.getSession();
		session.insert("Softs.insert",v);
		session.commit();
		session.close();
		return true;
	}


	@Override
	public boolean remove(Integer soid) {
		System.out.println("softs:remove");
		session=DBSession.getSession();
		session.delete("Softs.remove",soid);
		session.commit();
		session.close();
		return true;
	}


	@Override
	public boolean update(Integer k, Softs v) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Collection<Softs> getAll() {
		System.out.println("softs:find");
		session=DBSession.getSession();
		Collection<Softs> list=session.selectList("Softs.getall");
		session.commit();
		session.close();
		return list;
	}

	

}
