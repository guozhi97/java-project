package com.ctgu.springmvc.service;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ctgu.springmvc.dao.DBSession;
import com.ctgu.springmvc.dao.ProblemDao;
import com.ctgu.springmvc.entity.Problem;


@Repository
public class ProblemService implements ProblemDao {

	private SqlSession session;
	
	public ProblemService() {
	}

	@Override
	public boolean insert(Problem v) {
		System.out.println("insert-problem:"+v);
		session=DBSession.getSession();
		session.insert("Problem.insert", v);
		session.commit();
		session.close();
		return false;
	}



	@Override
	public boolean remove(Integer pid) {
		System.out.println("problem:remove");
		session=DBSession.getSession();
		session.delete("Problem.remove", pid);
		session.commit();
		session.close();
		return false;
	}



	@Override
	public boolean update(Integer k, Problem v) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Problem> getAll() {
		System.out.println("getall-problem");
		session=DBSession.getSession();
		Collection<Problem>list= session.selectList("Problem.getall");
		session.commit();
		session.close(); 
		return list;
	}
	
	public Collection<Problem> getAll_sum() {
		System.out.println("getall-problem");
		session=DBSession.getSession();
		Collection<Problem>list= session.selectList("Problem.getall");
		Collection<Problem> list2=get_sum_100(list);
		session.commit();
		session.close();
		return list2;
	}
	
	public Collection<Problem> getAll_time() {
		System.out.println("getall-problem");
		session=DBSession.getSession();
		Collection<Problem>list= session.selectList("Problem.getall");
		for(Problem p:list) {
			if(threeDay(p)) {
				System.out.println("remove :"+p.getPid());
				list.remove(p);
			}
		}
		Collections.reverse((List<Problem>) list);
		session.commit();
		session.close(); 
		return list;
	}
	
	/*按照热度提取链表的前一百条记录*/
	private Collection<Problem> get_sum_100(Collection<Problem> list){
		Collection<Problem> list2;
		Collections.sort((List<Problem>) list, new Comparator<Problem>() {
			@Override
			public int compare(Problem o1, Problem o2) {
				return o2.getSum()-o1.getSum();
			}
		});
		list2=(((List<Problem>) list).subList(0, (list.size()<100?list.size():100)));
		return list2;
	}
	
	/*按照时间顺序提取前一百条记录*/
	private Collection<Problem> get_time_100(Collection<Problem> list){
		Collection<Problem> list2;
		Collections.sort((List<Problem>) list, new Comparator<Problem>() {
			@Override
			public int compare(Problem o1, Problem o2) {
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
		list2=(((List<Problem>) list).subList(0, (list.size()<100?list.size():100)));
		return list2;
	}
	
	/*过滤未在三天内发送的提问*/
	private boolean threeDay(Problem problem) {
        String time=problem.getTime().trim();
		int y=Integer.parseInt(time.substring(0,4));
		int m=Integer.parseInt(time.substring(5,7));
		int d=Integer.parseInt(time.substring(8,10));
		Date date = new Date();
		if((date.getYear()-y)>0||(date.getMonth()-m)>0||(date.getDay()-d)>=3) {
			return true;
		}
		System.out.println(y+"-"+m+"-"+d+"-now:"+date.getDate());
		return false;
	}
	
	public Collection<Problem> search(String title) {
		System.out.println("search-problem");
		session=DBSession.getSession();
		Collection<Problem>list= session.selectList("Problem.search",title);
		Collection<Problem>list2=get_sum_100(list);
		session.commit();
		session.close(); 
		return list2;
	}
	
	
	public Problem findOne(Integer pid){
		System.out.println("problem-findone");
		session=DBSession.getSession();
		Problem problem=session.selectOne("Problem.findone", pid);
		session.commit();
		session.close();
		return problem;
	}
	
	public boolean updateSum(Integer pid) {
		System.out.println("updateSum");
		session=DBSession.getSession();
		session.update("Problem.updatesum", pid);
		session.commit();
		session.close();
		return true;
	}

}
