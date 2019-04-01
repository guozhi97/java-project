package com.ctgu.lss.order.entity;

import java.util.Date;

public class Order {
	private String order_id;
	private String user_id;
	private int address_id;
	private Date deliv_start;
	private Date deliv_end;
	private int deliv_user;
	private int order_status;
	private Date create_time;
	private Date finish_time;
	private String comment;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public Date getDeliv_start() {
		return deliv_start;
	}
	public void setDeliv_start(Date deliv_start) {
		this.deliv_start = deliv_start;
	}
	public Date getDeliv_end() {
		return deliv_end;
	}
	public void setDeliv_end(Date deliv_end) {
		this.deliv_end = deliv_end;
	}
	public int getDeliv_user() {
		return deliv_user;
	}
	public void setDeliv_user(int deliv_user) {
		this.deliv_user = deliv_user;
	}
	public int getOrder_status() {
		return order_status;
	}
	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getFinish_time() {
		return finish_time;
	}
	public void setFinish_time(Date finish_time) {
		this.finish_time = finish_time;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", user_id=" + user_id
				+ ", address_id=" + address_id + ", deliv_start=" + deliv_start
				+ ", deliv_end=" + deliv_end + ", deliv_user=" + deliv_user
				+ ", order_status=" + order_status + ", create_time="
				+ create_time + ", finish_time=" + finish_time + ", comment="
				+ comment + "]";
	}
	public Order(String order_id, String user_id, int address_id,
			Date deliv_start, Date deliv_end, int deliv_user, int order_status,
			Date create_time, Date finish_time, String comment) {
		super();
		this.order_id = order_id;
		this.user_id = user_id;
		this.address_id = address_id;
		this.deliv_start = deliv_start;
		this.deliv_end = deliv_end;
		this.deliv_user = deliv_user;
		this.order_status = order_status;
		this.create_time = create_time;
		this.finish_time = finish_time;
		this.comment = comment;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
