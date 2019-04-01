package com.ctgu.springmvc.service;

import java.util.Collection;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ctgu.springmvc.dao.DBSession;
import com.ctgu.springmvc.dao.TbUserDao;
import com.ctgu.springmvc.entity.TbUser;


@Repository
public class TbUserService implements TbUserDao {

	private SqlSession session;
	
	public TbUserService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean insert(TbUser v) {
//		System.out.println("tbuser insert"+v);
		session=DBSession.getSession();
		session.insert("TbUser.insert", v);
		session.commit();
		session.close();
		return true;
	}

	@Override
	public boolean remove(String uid) {
//		System.out.println("tbuser remove"+v);
		session=DBSession.getSession();
		session.delete("TbUser.remove", uid);
		session.commit();
		session.close();		
		return true;
	}

	@Override
	public boolean update(String k, TbUser v) {
		session=DBSession.getSession();
		session.update("TbUser.update", v);
		session.commit();
		session.close();
		return true;
	}

	@Override
	public Collection<TbUser> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public TbUser findone(String uid) {
		session=DBSession.getSession();
		TbUser tu= session.selectOne("TbUser.findone",uid);
		session.commit();
		session.close();		
		return tu;
	}
	

}
