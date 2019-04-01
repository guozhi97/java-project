package com.ctgu.springmvc.hander;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("resource")
@Controller
public class Resource {

	@RequestMapping("/resource")
	public String toResource() {
		
		return "resource/resource";
	}
	
	@RequestMapping("/softs")
	public String toSofts() {
		
		return "resource/softs";
	}
	
	@RequestMapping("/words")
	public String toWords() {
		
		return "resource/words";
	}
}
