package com.ctgu.lss.order.dao;

import java.util.List;

import com.ctgu.lss.common.IBaseDAO;
import com.ctgu.lss.order.entity.Order;
public interface OrderDao extends IBaseDAO<String, Order> {
	public Order findById(String order_id);
	public List<Order> findAll();
	public List<Order> findAllOrderByCustomerId(String user_id);
	public int getAllCount();
	public boolean doInsert(Order order);
	public boolean doDelete(String order_id);
}
