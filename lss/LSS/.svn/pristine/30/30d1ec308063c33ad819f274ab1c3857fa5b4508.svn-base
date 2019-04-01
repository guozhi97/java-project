package com.ctgu.lss.dept.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ctgu.lss.dept.dao.DepartmentDao;
import com.ctgu.lss.dept.entity.Department;

@Service
public class DepartmentService {
	@Resource
	private DepartmentDao departmentDao;
	public List<Department> findAll(){
		return departmentDao.findAll();
	}
	
}
