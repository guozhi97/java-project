package com.ctgu.springmvc.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ctgu.springmvc.dao.AnswerDao;
import com.ctgu.springmvc.dao.DBSession;
import com.ctgu.springmvc.entity.Answer;

@Repository
public class AnswerService implements AnswerDao {

	private SqlSession session;
	
	public AnswerService() {
	}

	@Override
	public boolean insert(Answer v) {
//		System.out.println("answer insert");
		session=DBSession.getSession();
		session.insert("Answer.insert", v);
		session.commit();
		session.close();
		return true;
	}

	@Override
	public boolean remove(Integer aid) {
//		System.out.println("answer remove");
		session=DBSession.getSession();
		session.delete("Answer.remove", aid);
		session.commit();
		session.close();
		return true;
	}

	@Override
	public boolean update(Integer k, Answer v) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Answer> getAll() {
//		System.out.println("answer:getall");
		session=DBSession.getSession();
		Collection<Answer> list=session.selectList("Answer.getall");
		Collection<Answer> list2=get_time_100(list);
		session.commit();
		session.close();
		return list2;
	}
	
	/*����ʱ��˳����ȡ��¼*/
	private Collection<Answer> get_time_100(Collection<Answer> list){
		Collection<Answer> list2;
		Collections.sort((List<Answer>) list, new Comparator<Answer>() {
			@Override
			public int compare(Answer o1, Answer o2) {
		        String time=o1.getTime().trim();
		        String time2=o2.getTime().trim();
				int len1 = 10000*Integer.parseInt(time.substring(0,4));
					len1 += 100*Integer.parseInt(time.substring(5,7));
					len1 += Integer.parseInt(time.substring(8,10));
				int len2 = 10000*Integer.parseInt(time2.substring(0,4));
					len2 += 100*Integer.parseInt(time2.substring(5,7));
					len2 += Integer.parseInt(time2.substring(8,10));
				return len2-len1;
			}
		});
		list2=(((List<Answer>) list).subList(0, list.size()));
		return list2;
	}
	
	public Collection<Answer> find(Integer pid){
//		System.out.println("answer:find");
		session=DBSession.getSession();
		Collection<Answer> list=session.selectList("Answer.find",pid);
		session.commit();
		session.close();
		return list;
	}
	
	public Answer findone(Integer aid){
//		System.out.println("answer:findone");
		session=DBSession.getSession();
		Answer answer=session.selectOne("Answer.findone",aid);
		session.commit();
		session.close();
		return answer;
	}

	
	

}
