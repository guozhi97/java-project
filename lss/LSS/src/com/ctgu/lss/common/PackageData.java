package com.ctgu.lss.common;

import java.io.Serializable;
import java.util.List;
/**
 * 接收手机端短信发送数据的对象，包含所有必须的基本信息
 * 其中包含MiniOrder对象数组，用以解析要发送的数据
 * @author Administrator
 *
 */
public class PackageData implements Serializable{

	private String courier_phone;
	private String tpl_id;
	private String company;
	private String time;
	private String address;
	private String phone;	
	private List<MiniOrder> orders;
	
	public String getCourier_phone() {
		return courier_phone;
	}
	public void setCourier_phone(String courier_phone) {
		this.courier_phone = courier_phone;
	}
	
	
	public List<MiniOrder> getOrders() {
		return orders;
	}
	public void setOrders(List<MiniOrder> orders) {
		this.orders = orders;
	}
	public String getTpl_id() {
		return tpl_id;
	}
	public void setTpl_id(String tpl_id) {
		this.tpl_id = tpl_id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
