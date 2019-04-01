package com.ctgu.springmvc.hander;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctgu.springmvc.entity.Json;
import com.ctgu.springmvc.entity.Problem;
import com.ctgu.springmvc.entity.Requery;
import com.ctgu.springmvc.entity.Softs;
import com.ctgu.springmvc.service.ProblemService;
import com.ctgu.springmvc.service.RequeryService;
import com.ctgu.springmvc.service.SoftsService;

@RequestMapping("index")
@Controller
public class Index {

	@Resource
	private ProblemService problemService;
	
	@Resource
	private SoftsService softsService;
	
	@Resource
	private RequeryService requeryService;
	
	//默认公告信息
	public static String toall="<p>网站初步建立完成，资源中的教程和软件还在收录中，敬请期待!</p>"
			+ "<p>由于网站初步建立，很多地方还需要完善，欢迎通过下方的反馈建议向我们提供建议</p>";
	
	@RequestMapping("/find")
	public String find(@RequestParam(value="find_index",required=false) String index,Map<String,Object> map) {
		map.put("index", index);
		Collection<Problem> list= problemService.search(index);
		map.put("problems", list);
		Collection<Softs> list2=softsService.find(index);
		Iterator<Softs> iter_soft=list2.iterator();
		while(iter_soft.hasNext()) {
			Softs soft=(Softs)iter_soft.next();
			int i=soft.getName().indexOf("#");
			soft.setName(soft.getName().substring(0,i));
		}
		map.put("softs", list2);
		return "find";
	}
	
	@RequestMapping("/tel")
	public String toTel(){
		
		return "tel";
	}
	@RequestMapping("/requery")
	@ResponseBody
	public String toRequery(@RequestParam("content") String content){
		requeryService.insert(new Requery(null,content+"<br>--------------------------------------发表时间:"+new Date()));
		System.out.println("requery:"+content);
		return "yes";
	}
	
	
	@RequestMapping("/about")
	public String toAbout(){
		
		return "about";
	}
	@RequestMapping("/about_detail")
	public String toAboutD(){
		
		return "about_detail";
	}
	
	@RequestMapping("/news")
	@ResponseBody
	public String toNews(){
//		Json json=new Json();
//		json.setMes(Index.toall);
		return Index.toall;
	}
	
	@RequestMapping("/registe")
	public String toRegiste(){
		return "registe";
	}
	
	@RequestMapping("/resource")
	public String toResource(){
		return "resource";
	}
	
	@RequestMapping("/admin")
	public String toAdmin(@RequestParam("name") String name,@RequestParam("pwd") String pwd,Map<String,Object> map){		
		if("root".equals(name)&&"121212".equals(pwd)){
			map.put("admin_index", "root121212");
			return "admin";
		}else {
			map.put("admin","deny");
			return "news";
		}
		
	}

	

	
}
