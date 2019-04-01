package com.ctgu.lss.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ctgu.lss.user.dao.UserDao;
import com.ctgu.lss.user.entity.User;
@Service
public class UserService {
	@Resource
	private UserDao userDao;
	
	public boolean loginSystem(String user_id,String password){
		boolean success = false;
		if (user_id == null || password == null) {
			success = false;
		}else{
			System.out.println("loginsystem---userId = " + user_id);
			User user = userDao.findById(user_id);
			System.out.println(user);
			if (user == null) {
				success = false;
			}else if(user.getPassword().equals(password)){
				success = true;
			}
		}
		return success;
	}
	
	public User getUserInfo(String user_id){
		System.out.println("用户用户信息");
		return userDao.findById(user_id);
	}
	
	public boolean doUpdate(User user){
		try {
			return userDao.doUpdate(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
