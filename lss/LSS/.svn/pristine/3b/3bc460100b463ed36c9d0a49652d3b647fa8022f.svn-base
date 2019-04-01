package com.ctgu.lss.order.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ctgu.lss.order.dao.OrderDao;
import com.ctgu.lss.order.entity.Order;

import freemarker.template.SimpleDate;

@Service
public class OrderService {
	@Resource
	private OrderDao orderDao;
	public boolean insertOrder(Order order){
		return orderDao.doInsert(order);
	}
	
	public boolean doDelete(String order_id){
		return orderDao.doDelete(order_id);
	}
	
	public List<Order> findAll(){
		return orderDao.findAll();
	}
	public List<Order> findAllOrderByCustomerId(String user_id){
		return orderDao.findAllOrderByCustomerId(user_id);
	}
	public int getAllCount(){
		return orderDao.getAllCount();
	}
	public Order findByOrderId(String order_id){
		return orderDao.findById(order_id);
	}
	
	public String getOrder_id(String user_id){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String now = df.format(new Date());
		return now+user_id;
	}
}
