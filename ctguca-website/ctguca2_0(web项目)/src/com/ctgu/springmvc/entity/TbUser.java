package com.ctgu.springmvc.entity;

/*
<id column="uid" jdbcType="VARCHAR" property="uid"/>
<result column="pwd" jdbcType="VARCHAR" property="pwd"/>
<result column="name" jdbcType="VARCHAR" property="name"/>
<result column="disc" jdbcType="VARCHAR" property="disc"/>
<result column="ico" jdbcType="VARCHAR" property="ico"/>*/
public class TbUser {
	private String uid;
	private String pwd;
	private String name;
	private String disc;
	private String ico;
	public TbUser() {
	}
	public TbUser(String uid, String pwd, String name, String disc, String ico) {
		this.uid = uid;
		this.pwd = pwd;
		this.name = name;
		this.disc = disc;
		this.ico = ico;
	}
	@Override
	public String toString() {
		return "TbUser [uid=" + uid + ", pwd=" + pwd + ", name=" + name + ", disc=" + disc + ", ico=" + ico + "]";
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisc() {
		return disc;
	}
	public void setDisc(String disc) {
		this.disc = disc;
	}
	public String getIco() {
		return ico;
	}
	public void setIco(String ico) {
		this.ico = ico;
	}
	
}
