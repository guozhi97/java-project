package com.ctgu.springmvc.service;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ctgu.springmvc.dao.DBSession;
import com.ctgu.springmvc.dao.TbAnswerDao;
import com.ctgu.springmvc.entity.TbAnswer;


@Repository
public class TbAnswerService implements TbAnswerDao {

	private SqlSession session;
	
	public TbAnswerService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean insert(TbAnswer v) {
		session=DBSession.getSession();
		session.insert("TbAnswer.insert", v);
		session.commit();
		session.close();
		return true;
	}

	@Override
	public boolean remove(Integer anid) {
		session=DBSession.getSession();
		session.delete("TbAnswer.remove", anid);
		session.commit();
		session.close();
		return true;
	}

	@Override
	public boolean update(Integer k, TbAnswer v) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<TbAnswer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public TbAnswer findone(Integer anid) {
		session=DBSession.getSession();
		TbAnswer ta=session.selectOne("TbAnswer.findone", anid);
		session.commit();
		session.close();
		return ta;
	}
	
	public Collection<TbAnswer> findFor_answer(Integer ansid){
		Collection<TbAnswer> list=null;
		session=DBSession.getSession();
		list= session.selectList("TbAnswer.findfor_answer", ansid);
		session.commit();
		session.close();
		return list;		
		
	}
	public Collection<TbAnswer> findFor_arcticle(Integer arid){
		Collection<TbAnswer> list=null;
		session=DBSession.getSession();
		list= session.selectList("TbAnswer.findfor_arcticle", arid);
		session.commit();
		session.close();
		return list;		
	}
	
	public Collection<TbAnswer> findFor_user(String uid){
		Collection<TbAnswer> list=null;
		session=DBSession.getSession();
		list= session.selectList("TbAnswer.findfor_arcticle", uid);
		session.commit();
		session.close();
		return list;		
	}
	
	public Collection<TbAnswer> findFor_content(String str){
		Collection<TbAnswer> list=null;
		session=DBSession.getSession();
		list= session.selectList("TbAnswer.findfor_content", str);
		session.commit();
		session.close();
		return list;		
	}


}
