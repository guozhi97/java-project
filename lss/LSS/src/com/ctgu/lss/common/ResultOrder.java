package com.ctgu.lss.common;


/**
 *  返回数据的对象，用来封装短信发送完成后的返回数据。
 * @author Administrator
 *
 */
public class ResultOrder {
	private Integer rec_id;
	private String courier_phone;
	private String customer_phone;
	private String package_code;
	private String area_num;
	private String op_date;
	private char order_state;//'1': 未发送成功   '2':已发送   '3':已派件
	
	public Integer getRec_id() {
		return rec_id;
	}
	public void setRec_id(Integer rec_id) {
		this.rec_id = rec_id;
	}
	public String getCourier_phone() {
		return courier_phone;
	}
	public void setCourier_phone(String courier_phone) {
		this.courier_phone = courier_phone;
	}
	public String getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}
	public String getPackage_code() {
		return package_code;
	}
	public void setPackage_code(String package_code) {
		this.package_code = package_code;
	}
	public String getArea_num() {
		return area_num;
	}
	public void setArea_num(String area_num) {
		this.area_num = area_num;
	}
	public String getOp_date() {
		return op_date;
	}
	public void setOp_date(String op_date) {
		this.op_date = op_date;
	}
	public char getOrder_state() {
		return order_state;
	}
	public void setOrder_state(char order_state) {
		this.order_state = order_state;
	}
}
