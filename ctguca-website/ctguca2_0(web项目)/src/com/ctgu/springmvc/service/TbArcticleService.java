package com.ctgu.springmvc.service;

import java.util.Collection;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ctgu.springmvc.dao.DBSession;
import com.ctgu.springmvc.dao.TbArcticleDao;
import com.ctgu.springmvc.entity.TbAnswer;
import com.ctgu.springmvc.entity.TbArcticle;

@Repository
public class TbArcticleService implements TbArcticleDao {

	private SqlSession session;	
	
	public TbArcticleService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean insert(TbArcticle v) {
		session=DBSession.getSession();
		session.insert("TbArcticle.insert", v);
		session.commit();
		session.close();
		return true;
	}

	@Override
	public boolean remove(Integer arid) {
		session=DBSession.getSession();
		session.delete("TbArcticle.remove", arid);
		session.commit();
		session.close();
		return true;
	}

	@Override
	public boolean update(Integer k, TbArcticle v) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<TbArcticle> getAll() {
		Collection<TbArcticle> list=null;
		session=DBSession.getSession();
		list= session.selectList("TbArcticle.getall");
		session.commit();
		session.close();
		return list;		
	}
	
	public TbArcticle findone(Integer arid) {
		session=DBSession.getSession();
		TbArcticle ta=session.selectOne("TbArcticle.findone", arid);
		session.commit();
		session.close();
		return ta;
	}
	
	public Collection<TbArcticle> findFor_user(String uid){
		Collection<TbArcticle> list=null;
		session=DBSession.getSession();
		list= session.selectList("TbArcticle.findfor_user", uid);
		session.commit();
		session.close();
		return list;		
	}
	
	public Collection<TbArcticle> findFor_content(String str){
		Collection<TbArcticle> list=null;
		session=DBSession.getSession();
		list= session.selectList("TbArcticle.findfor_content", str);
		session.commit();
		session.close();
		return list;		
	}
	
	public boolean addApploud(Integer arid) {
		session=DBSession.getSession();
		session.update("TbArcticle.addapploud", arid);
		session.commit();
		session.close();
		return true;		
	}
	
	

}
