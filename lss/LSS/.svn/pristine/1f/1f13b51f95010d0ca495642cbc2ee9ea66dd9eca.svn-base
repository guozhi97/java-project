package com.ctgu.lss.user.dao;

import java.util.List;

import com.ctgu.lss.common.IBaseDAO;
import com.ctgu.lss.user.entity.User;

public interface UserDao extends IBaseDAO<String, User> {
	//得到某个用户的信息
	public User findById(String user_id);
	//添加用户
	public boolean doInsert(User user);
	//删除某个用户
	public boolean doDelete(User user);
	//更新用户
	public boolean deUpdate(User user);
	//得到所有用户
	public List<User> findAll(); 
}
