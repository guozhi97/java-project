package com.ctgu.springmvc.hander;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ctgu.springmvc.entity.Student;
import com.ctgu.springmvc.service.StudentService;

@RequestMapping("registe")
@Controller
public class Registe {

	@Resource
	private StudentService studentService;

	@RequestMapping("/submit")
	@ResponseBody
	public String submit(Student student){
//		System.out.println("submit once");
		//判断账号是否已经存在
		if(studentService.isUniqueUsername(student.getSid())){
//			System.out.println("submit2: "+student+studentService.isUniqueUsername(student.getSid()));
			//若不存在，则插入，并返回注成功的信息
			studentService.insert(student);
			return "1";
		}else{
			//若存在，则返回账号已存在的信息
			return "0";
		}
	}
}
