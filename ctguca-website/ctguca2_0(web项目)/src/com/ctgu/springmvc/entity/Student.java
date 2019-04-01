package com.ctgu.springmvc.entity;


public class Student {

	private String sid;
	private String name;
	private String gender;
	private String major;
	private String address;
	private String will;
	private String tel;

	public Student() {

	}

	

	public Student(String sid, String name, String gender, String major, String address, String will, String tel) {
		this.sid = sid;
		this.name = name;
		this.gender = gender;
		this.major = major;
		this.address = address;
		this.will = will;
		this.tel = tel;
	}



	@Override
	public String toString() {
		return sid + "," + name + "," + gender + "," + major + ","
				+ address + "," + will + "," + tel ;
	}



	public String getWill() {
		return will;
	}



	public void setWill(String will) {
		this.will = will;
	}



	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
