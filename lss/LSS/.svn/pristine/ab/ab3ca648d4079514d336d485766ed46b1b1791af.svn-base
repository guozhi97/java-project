package com.ctgu.lss.user.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ctgu.lss.address.entity.Address;
import com.ctgu.lss.address.service.AddressService;
import com.ctgu.lss.user.entity.User;
import com.ctgu.lss.user.service.UserService;
@Controller
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private AddressService addressService;
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String doLogin(@RequestParam("user") String user,@RequestParam("password")String password,ModelMap map,HttpSession session){
		System.out.println(user + password);
		if (user == null || password == null) {
			map.addAttribute("message", "error1");
			return "redirect:login.jsp";
		}else{
			User u = userService.getUserInfo(user);
			
			if (u == null) {
				map.addAttribute("message", "error2");
				return "redirect:login.jsp";
			}else if(u.getPassword().equals(password)){
				session.setAttribute("userName", u.getReal_name());
				session.setAttribute("userId", u.getUser_id());
				return "redirect:home.jsp";
			}else{
				map.addAttribute("message", "error3");
				return "redirect:login.jsp";
			}
		}
		
	}
	
	@RequestMapping("exit")
	public String exitLogin(HttpSession session){
		session.removeAttribute("user");
		session.removeAttribute("bookList");
		return "redirect:login.jsp";
	}
	
	
	@RequestMapping("person")
	public String person(ModelMap map,HttpSession session){
		User user = (User) session.getAttribute("user");
		try {
			List<Address> addressList = addressService.getAllCustomerAddress(user.getUser_id());
			map.addAttribute("addresssList",addressList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "jsp/information";
	}
}
