package com.ctgu.springmvc.hander;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctgu.springmvc.entity.Problem;
import com.ctgu.springmvc.entity.Requery;
import com.ctgu.springmvc.entity.Softs;
import com.ctgu.springmvc.entity.Student;
import com.ctgu.springmvc.service.AnswerService;
import com.ctgu.springmvc.service.ProblemService;
import com.ctgu.springmvc.service.RequeryService;
import com.ctgu.springmvc.service.SoftsService;
import com.ctgu.springmvc.service.StudentService;
import com.ctgu.springmvc.service.TbAnswerService;
import com.ctgu.springmvc.service.TbArcticleService;


@RequestMapping("admins")
@Controller
public class Admin {

	@javax.annotation.Resource
	private  StudentService studentService;
	
	@javax.annotation.Resource
	private  SoftsService softsService;
	
	@Resource
	private RequeryService requeryService;
	
	@Resource 
	private AnswerService answerService;
	
	@Resource
	private ProblemService problemServic;
	
	private String pwd_sure="23kh4h23h4j2h342h3j4h797987hkhuh";
	
	private String URL="admin";
	@Resource
	private TbAnswerService tbAnswerService;
	
	@Resource
	private TbArcticleService tbArcticleService;
	
	@ResponseBody
	@RequestMapping("/sure")
	public String sureAdmin(@RequestParam("pwd") String index) {
		if("root121212".equals(index)) {
			return pwd_sure;
		}else {
			return  "no";
		}
	}
	
	@RequestMapping("/login")
	public String toLogin(@RequestParam(value="pwd",required=true) String pwd,Map<String,Object>map){
		if(pwd_sure.equals(pwd)) {
			map.put("admin_index", "root121212");
			return URL;
		}else {
			System.out.println(pwd);
			return "呵呵~";
		}
	}
	
	@RequestMapping("/updatatoall")
	public String updateToall(@RequestParam("words") String words ,Map<String,Object>map) {
		Index.toall=words;
		map.put("toall",Index.toall);
		map.put("gettoall","update");
		map.put("admin_index", "root121212");
		System.out.println("toall:update:"+words);
		return URL;
	}
	
	@RequestMapping("/getwords")
	public String updateToall(Map<String,Object>map) {
		map.put("toall",Index.toall);
		map.put("admin_index", "root121212");
		map.put("gettoall","update");
		System.out.println("toall:getall");
		return URL;
	}
	
//	ɾ������ɾ�����ۻ����ʵ�requery
	@RequestMapping("/delrequeryp_a")
	public String deleterequery(@RequestParam("aid") Integer maid,@RequestParam("pid") Integer mpid,Map<String,Object>map) {
		Collection<Requery>list=null;
		Collection<com.ctgu.springmvc.entity.Answer>a_list=new ArrayList<com.ctgu.springmvc.entity.Answer>();
		Collection<Problem>p_list=new ArrayList<Problem>();
		list=requeryService.getAll();
		Iterator<Requery> iter=list.iterator();
		while(iter.hasNext()) {
			Requery requery=(Requery)iter.next();
			if(requery.getContent().startsWith("#aid=")) {
				if(requery.getContent().startsWith("#aid="+maid)) {
					requeryService.remove(requery.getRid());
					continue;
				}
				String said=requery.getContent().substring(5);
				try {
					int aid=Integer.parseInt(said);
					com.ctgu.springmvc.entity.Answer a =answerService.findone(aid);
					if(a==null||a.getAid()==null) {
						requeryService.remove(requery.getRid());
					}else {
						a_list.add(a);
					}
				}catch(Exception e) {
					System.out.println("admin:tocomments:exception");
				}
			}else if(requery.getContent().startsWith("#pid=")){
				if(requery.getContent().startsWith("#pid="+mpid)) {
					requeryService.remove(requery.getRid());
					continue;
				}
				String spid=requery.getContent().substring(5);
				try {
					int pid=Integer.parseInt(spid);
					Problem p=problemServic.findOne(pid);
					if(p==null||p.getPid()==null) {
						requeryService.remove(requery.getRid());
					}else {
						p_list.add(p);
					}
				}catch(Exception e) {
					System.out.println("admin:tocomments:exception2");
				}
			}
		}
		map.put("foranswer", a_list);
		map.put("forproblem", p_list);
		map.put("tocomment", "delrequeryp_a");
		map.put("admin_index", "root121212");
		System.out.println("tocomment:getall");
		return URL;
	}
	
	
	@RequestMapping("/delpro_ans")
	public String deleteAnswer(@RequestParam("aid") Integer maid,@RequestParam("pid") Integer mpid,Map<String,Object>map) {
		if(maid!=-1) {
			answerService.remove(maid);
		}else if(mpid!=-1) {
			problemServic.remove(mpid);
		}
		Collection<Requery>list=null;
		Collection<com.ctgu.springmvc.entity.Answer>a_list=new ArrayList<com.ctgu.springmvc.entity.Answer>();
		Collection<Problem>p_list=new ArrayList<Problem>();
		list=requeryService.getAll();
		Iterator<Requery> iter=list.iterator();
		while(iter.hasNext()) {
			Requery requery=(Requery)iter.next();
			if(requery.getContent().startsWith("#aid=")) {
				String said=requery.getContent().substring(5);
				try {
					int aid=Integer.parseInt(said);
					com.ctgu.springmvc.entity.Answer a =answerService.findone(aid);
					if(a==null||a.getAid()==null) {
						requeryService.remove(requery.getRid());
					}else {
						a_list.add(a);
					}
				}catch(Exception e) {
					System.out.println("admin:tocomments:exception");
				}
			}else if(requery.getContent().startsWith("#pid=")){
				String spid=requery.getContent().substring(5);
				try {
					int pid=Integer.parseInt(spid);
					Problem p=problemServic.findOne(pid);
					if(p==null||p.getPid()==null) {
						requeryService.remove(requery.getRid());
					}else {
						p_list.add(p);
					}
				}catch(Exception e) {
					System.out.println("admin:tocomments:exception2");
				}
			}
		}
		map.put("foranswer", a_list);
		map.put("forproblem", p_list);
		map.put("tocomment", "delpro_ans");
		map.put("admin_index", "root121212");
		System.out.println("tocomment:getall");
		return URL;
	}
	
	@RequestMapping("/tocomments")
	public String getComments(Map<String,Object>map) {
		Collection<Requery>list=null;
		Collection<com.ctgu.springmvc.entity.Answer>a_list=new ArrayList<com.ctgu.springmvc.entity.Answer>();
		Collection<Problem>p_list=new ArrayList<Problem>();
		list=requeryService.getAll();
		Iterator<Requery> iter=list.iterator();
		while(iter.hasNext()) {
			Requery requery=(Requery)iter.next();
			if(requery.getContent().startsWith("#aid=")) {
				String said=requery.getContent().substring(5);
				try {
					int aid=Integer.parseInt(said);
					com.ctgu.springmvc.entity.Answer a =answerService.findone(aid);
					if(a==null||a.getAid()==null) {
						requeryService.remove(requery.getRid());
					}else {
						a_list.add(a);
					}
				}catch(Exception e) {
					System.out.println("admin:tocomments:exception");
				}
			}else if(requery.getContent().startsWith("#pid=")){
				String spid=requery.getContent().substring(5);
				try {
					int pid=Integer.parseInt(spid);
					Problem p=problemServic.findOne(pid);
					if(p==null||p.getPid()==null) {
						requeryService.remove(requery.getRid());
					}else {
						p_list.add(p);
					}
				}catch(Exception e) {
					System.out.println("admin:tocomments:exception2");
				}
			}
		}
		map.put("foranswer", a_list);
		map.put("forproblem", p_list);
		map.put("tocomment", "getall");
		map.put("admin_index", "root121212");
		System.out.println("tocomment:getall");
		return URL;
	}
	
	@RequestMapping("/delrequery")
	public String delRequery(@RequestParam("rid") Integer rid,Map<String,Object>map) {
		Collection<Requery>list=null;
		requeryService.remove(rid);
		list=requeryService.getAll();
		map.put("requerys", list);
		map.put("torequery", "delrequery");
		map.put("admin_index", "root121212");
		System.out.println("requery:delete");
		return URL;
	}
	
	@RequestMapping("/getrequery")
	public String delStudent(Map<String,Object>map) {
		Collection<Requery>list=null;
		list=requeryService.getAll();
		Iterator<Requery> iter=list.iterator();
		while(iter.hasNext()) {
			Requery r = iter.next();
			if (r.getContent().startsWith("#pid")||r.getContent().startsWith("#aid")) {
				iter.remove();
			}
		}
		for(Integer x :luntan.jubaoar) {
			Requery r=new Requery();
			r.setRid(-1);
			r.setContent("新论坛-举报文章或提问："+tbArcticleService.findone(x).toString());
			list.add(r);
		}
		for(Integer x :luntan.jubaoan) {
			Requery r=new Requery();
			r.setRid(-1);
			r.setContent("新论坛-举报回复："+tbAnswerService.findone(x).toString());
			list.add(r);
		}
		map.put("requerys", list);
		map.put("torequery", "getall");
		map.put("admin_index", "root121212");
		System.out.println("requery:getall");
		return URL;
	}
	
	@RequestMapping("/deletesoft")
	public String delStudent(@RequestParam("soid") Integer soid,Map<String,Object> map) {
		Collection<Softs> list=null;
		softsService.remove(soid);
		list=softsService.getAll();
		map.put("softs",list);
		map.put("tosoft", "deletesoft");
		map.put("admin_index", "root121212");
		System.out.println("delstudent:"+soid);
		return URL;
	}
	
	@RequestMapping("/addsoft")
	public String addSoft(Softs soft,Map<String,Object>map) {
		softsService.insert(soft);
		System.out.println("addsoft:"+soft);
		Collection<Softs> list=null;
		list=softsService.getAll();
		map.put("softs", list);
		map.put("tosoft", "addsoft");
		map.put("admin_index", "root121212");
		System.out.println("getsofts:"+list);
		return URL;
	}
	
	@RequestMapping("/getsofts")
	public String getSofts(Map<String,Object> map) {
		Collection<Softs> list=null;
		list=softsService.getAll();
		map.put("softs", list);
		map.put("tosoft", "getall");
		map.put("admin_index", "root121212");
		System.out.println("getsofts:"+list);
		return URL;
	}
	
	@RequestMapping("/delete")
	public String delStudent(@RequestParam("sid") String sid,Map<String,Object> map) {
		Collection<Student> list=null;
		studentService.remove(sid);
		list=studentService.getAll();
		map.put("students",list);
		map.put("admin_index", "root121212");
		System.out.println("delstudent:"+sid);
		return URL;
	}
	
	
	@RequestMapping("/getstudents")
	public String getAll(@RequestParam("reg-form") String tip,Map<String,Object> map){
		Collection<Student> list=null;
		if(tip.equals("getall")){
			list = studentService.getAll();			
		}else if(!"".equals(tip)){
			list=studentService.getSomeOne(tip);
		}
		map.put("students", list);
		map.put("active", tip);
		map.put("reg_sum",list.size());
		System.out.println(list);
		map.put("admin_index", "root121212");
		return URL;
	}
	
	@RequestMapping("download")
	public ResponseEntity<byte[]> testResponseEntity(@RequestParam("reg-form") String tip, HttpSession session)throws Exception{
		StudentService studentService = new StudentService();
		Collection<Student> list=new ArrayList<Student>();
		if(tip.equals("getall")){
			list = studentService.getAll();
			tip="������Ա";
		}else if(!"".equals(tip)){
			list=studentService.getSomeOne(tip);
		}
		StringBuffer body=new StringBuffer();
		body.append(tip+"����<br>");
		for(Student s:list){
			body.append(s.toString()+"<br>");
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=students.html");
		
		HttpStatus status = HttpStatus.OK;
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body.toString().getBytes(),headers, status);
		return response;
	}
}
