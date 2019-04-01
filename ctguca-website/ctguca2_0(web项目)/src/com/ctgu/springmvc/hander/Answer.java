package com.ctgu.springmvc.hander;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctgu.springmvc.entity.Problem;
import com.ctgu.springmvc.entity.Requery;
import com.ctgu.springmvc.service.AnswerService;
import com.ctgu.springmvc.service.ProblemService;
import com.ctgu.springmvc.service.RequeryService;

@RequestMapping("answer")
@Controller
public class Answer {

	@Resource(name="problemService")
	private ProblemService problemService;
	
	@Resource(name="answerService")
	private AnswerService answerService;
	
	@Resource
	private RequeryService requeryService;
	
	@ResponseBody
	@RequestMapping("/requestdeletep")
	public String requestDeleteProblem(@RequestParam("pid") Integer pid) {
		requeryService.insert(new Requery(null,"#pid="+pid));
		System.out.println("requestdeletep");
		return "yes";
	}
	
	@ResponseBody
	@RequestMapping("/requestdeletea")
	public String requestDeleteAnswer(@RequestParam("aid") Integer aid) {
		requeryService.insert(new Requery(null,"#aid="+aid));
		System.out.println("requestdeletea");
		return "yes";
	}
	
	
	@RequestMapping("/answer")
	public String toAnswer(Map<String,Object> map){
		Collection<Problem> list= problemService.getAll_sum();
		Collection<Problem> list2= problemService.getAll_time();
		map.put("problem2s", list2);
		map.put("problems", list);
		return "answer/answer";
	}
	
	@RequestMapping("/search")
	public String searchProblem(@RequestParam("search_index") String title,Map<String,Object> map) {
		System.out.println(title);
		Collection<Problem> list= problemService.search(title);
		map.put("problems", list);
		map.put("index", title);
		return "answer/search";
	}
	
	@RequestMapping("/comments")
	public String toComments(@RequestParam("pid") Integer pid,Map<String,Object> map){
		System.out.println("toComments");
		Problem problem = problemService.findOne(pid);
		problemService.updateSum(pid);
		Collection<com.ctgu.springmvc.entity.Answer> list=(Collection<com.ctgu.springmvc.entity.Answer>) answerService.find(pid);
		map.put("problem", problem);
		map.put("comments", list);
		return "answer/comments";
	}
	
	@RequestMapping("/subcomments")
	public String subComments(com.ctgu.springmvc.entity.Answer answer,Map<String,Object> map){
		System.out.println("subComments:"+answer);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
		answer.setTime(time);
		answerService.insert(answer);
		Problem problem = problemService.findOne(answer.getPid());
		Collection<com.ctgu.springmvc.entity.Answer> list=(Collection<com.ctgu.springmvc.entity.Answer>) answerService.find(answer.getPid());
		map.put("problem", problem);
		map.put("comments", list);
		return "answer/comments";
	}
	
	
	@RequestMapping("/problem")
	public String toProblem(Problem problem,Map<String,Object> map){
		System.out.println("answer toproblem");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        problem.setTime(time);
        problemService.insert(problem);
		Collection<Problem> list= problemService.getAll_sum();
		Collection<Problem> list2= problemService.getAll_time();
		map.put("problems", list);
		map.put("problem2s", list2);
		return "answer/answer";
	}

}
