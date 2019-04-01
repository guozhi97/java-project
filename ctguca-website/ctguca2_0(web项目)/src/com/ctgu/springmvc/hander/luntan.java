package com.ctgu.springmvc.hander;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ctgu.springmvc.entity.OneAnswer;
import com.ctgu.springmvc.entity.OneArcticle;
import com.ctgu.springmvc.entity.TbAnswer;
import com.ctgu.springmvc.entity.TbArcticle;
import com.ctgu.springmvc.entity.TbUser;
import com.ctgu.springmvc.service.TbAnswerService;
import com.ctgu.springmvc.service.TbArcticleService;
import com.ctgu.springmvc.service.TbUserService;

import net.sf.json.JSONArray;


@RequestMapping("luntan")
@Controller
public class luntan {

	@Resource
	private TbUserService tbUserService;
	
	@Resource
	private TbAnswerService tbAnswerService;
	
	@Resource
	private TbArcticleService tbArcticleService;
	
	private final String T="yes";//表是肯定的信息
	private final String F="no";//表是否定的信息
	
	public static List<Integer> jubaoar=new ArrayList();//被举报的文章
	public static List<Integer> jubaoan=new ArrayList();//被举报的回复
	
	@ResponseBody
	@RequestMapping("/jubaoar")
	public String jubaoar(@RequestParam("arid") Integer arid) {
		jubaoar.add(arid);
		 HashSet h = new HashSet(jubaoar);
		 jubaoar.clear();
		 jubaoar.addAll(h);		
//		 System.out.println("jubaoar:"+arid);
		return T;
	}
	@ResponseBody
	@RequestMapping("/jubaoan")
	public String jubaoan(@RequestParam("anid") Integer anid) {
		jubaoan.add(anid);
		 HashSet h = new HashSet(jubaoan);
		 jubaoan.clear();
		 jubaoan.addAll(h);
//		 System.out.println("jubaoan:"+anid);
		return T;
	}
	
	@ResponseBody
	@RequestMapping("/addapploud")
	public String addApploud(@RequestParam("arid") Integer arid) {
		tbArcticleService.addApploud(arid);
		return T;
	}
	
	@ResponseBody
	@RequestMapping("/login")
	public String toLogin(TbUser tu) {//登录
		TbUser t1=tbUserService.findone(tu.getUid());
		if(t1!=null&&tu.getPwd().equals(t1.getPwd())) {
			return T;
		}else {
			return F;
		}
	}
	
	@ResponseBody
	@RequestMapping("/register")
	public String toRegister(TbUser tu) {//注册
		TbUser t1=tbUserService.findone(tu.getUid());
		if(t1!=null) {
			return F;
		}else {
			tbUserService.insert(tu);
			return T;
		}
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public String toUpdate(TbUser tu) {//修改个人信息(此处有一个漏洞，如果有人模拟个人信息发送过来，可任意修改他所知道的账号的密码，可通过密码验证修复)
		tbUserService.update(null, tu);
		return T;
	}
	
	@ResponseBody
	@RequestMapping("/addarcticle")
	public String insertArcticle(TbArcticle ta) {//新增一篇文章
		tbArcticleService.insert(ta);
		return T;
	}
	
	@ResponseBody
	@RequestMapping("/addanswer")
	public String insertAnswer(TbAnswer ta) {//发表一个回复
		tbAnswerService.insert(ta);
		return T;
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/search")
	public String searchArcticle(@RequestParam("words") String words) {//搜索功能
		Collection<TbArcticle> list=tbArcticleService.findFor_content(words);
		if(list==null||list.isEmpty()) {
			return F;
		}
		Collection<OneArcticle> list2=new ArrayList<OneArcticle>(); 
		Iterator<TbArcticle> iter=list.iterator();
		while(iter.hasNext()) {
			TbArcticle tar=iter.next();
			Collection<TbAnswer> tans=tbAnswerService.findFor_arcticle(tar.getArid());
			int i=0;
			if(tans!=null&&!tans.isEmpty()) {
				i=tans.size();
			}
			TbUser tu=tbUserService.findone(tar.getUid());
			OneArcticle oar=new OneArcticle();
			oar.comeOn(tar, tu);
			oar.setAnsnum(i);
			list2.add(oar);
		}
		JSONArray json = JSONArray.fromObject(list2);
		return json.toString();
	}
	
	
	@ResponseBody
	@RequestMapping("/uarcticle")
	public String getUser_arcticle(@RequestParam("uid") String uid) {//获取用户的文章
		Collection<TbArcticle> list=tbArcticleService.findFor_user(uid);
		if(list==null||list.isEmpty()) {
			return F;
		}
		Collection<OneArcticle> list2=new ArrayList<OneArcticle>(); 
		Iterator<TbArcticle> iter=list.iterator();
		while(iter.hasNext()) {
			TbArcticle tar=iter.next();
			Collection<TbAnswer> tans=tbAnswerService.findFor_arcticle(tar.getArid());
			TbUser tu=tbUserService.findone(tar.getUid());
			OneArcticle oar=new OneArcticle();
			oar.comeOn(tar, tu);
			oar.setAnsnum(tans.size());
			list2.add(oar);
		}
		JSONArray json = JSONArray.fromObject(list2);
		return json.toString();
	}
	
	@ResponseBody
	@RequestMapping("/uanswer")
	public String getUser_answer(@RequestParam("uid") String uid) {//获取用户的回复
		Collection<TbAnswer> list=tbAnswerService.findFor_user(uid);
		JSONArray json = JSONArray.fromObject(list);
		return json.toString();
	}
	
	@ResponseBody
	@RequestMapping("/allarcticle")
	public String getAll_arcticle() {//获取所有文章
		Collection<TbArcticle> list=tbArcticleService.getAll();
		if(list==null||list.isEmpty()) {
			return F;
		}
		JSONArray json = JSONArray.fromObject(list);
		return json.toString();
	}
	
	@ResponseBody
	@RequestMapping("/allarc_ans")
	public String getAll_arcticle2() {//获取所有文章并封装成OneArcticle数组
		Collection<TbArcticle> list=tbArcticleService.getAll();
		if(list==null||list.isEmpty()) {
			return F;
		}
		Collection<OneArcticle> list2=new ArrayList<OneArcticle>(); 
		Iterator<TbArcticle> iter=list.iterator();
		while(iter.hasNext()) {
			TbArcticle tar=iter.next();
			Collection<TbAnswer> tans=tbAnswerService.findFor_arcticle(tar.getArid());
			TbUser tu=tbUserService.findone(tar.getUid());
			OneArcticle oar=new OneArcticle();
			oar.comeOn(tar, tu);
			oar.setAnsnum(tans.size());
			list2.add(oar);
		}
		JSONArray json = JSONArray.fromObject(list2);
		return json.toString();
	}
	@ResponseBody
	@RequestMapping("/all_ans")
	public String getAll_answer(@RequestParam("arid") Integer arid) {//获取所有回复并封装成OneAnswer数组
		Collection<OneAnswer> list=new ArrayList<OneAnswer>();
		Collection<TbAnswer> tans=tbAnswerService.findFor_arcticle(arid);		
		Iterator<TbAnswer> iter=tans.iterator();
		while(iter.hasNext()) {
			TbAnswer tar=iter.next();
			TbUser tu=tbUserService.findone(tar.getUid());
			OneAnswer oan=new OneAnswer();
			oan.comeOn(tu, tar);
			list.add(oan);
		}
		JSONArray json = JSONArray.fromObject(list);
		return json.toString();
	}
	
	@ResponseBody
	@RequestMapping("/arofan")
	public TbArcticle getArcticleOfAnswer(@RequestParam("arid") Integer arid) {//获取该回复的提问或文章
		TbArcticle ta=tbArcticleService.findone(arid);
		return ta;
	}
	
	@ResponseBody
	@RequestMapping("/anofar")
	public String getAnswerOfArcticle(@RequestParam("arid") Integer arid) {//获取文章的所有回复
		Collection<TbAnswer> list=tbAnswerService.findFor_arcticle(arid);
		JSONArray json = JSONArray.fromObject(list);
		return json.toString();
	}
	
	@ResponseBody
	@RequestMapping("/usforan")
	public String getUserForAnswer(@RequestParam("uid") String uid) {//获取一个用户的简单信息（评论中显示），这是为了在评论中显示用户个人信息，这个效率不高，建议优化
//		System.out.println("usforan:"+uid);
		TbUser t1=tbUserService.findone(uid);
		if(t1!=null) {
			JSONObject json=new JSONObject();
			json.put("name", t1.getName());
			json.put("ico", t1.getIco());
			json.put("disc", t1.getDisc());
//			System.out.println("usforan2:"+json.toJSONString()+"-"+t1.toString());
			return json.toJSONString();
		}else {
			return F;
		}
	}
	


}
