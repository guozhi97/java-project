package com.ctgu.springmvc.entity;

/*
<id column="arid" jdbcType="INTEGER" property="arid"/>
<result column="title" jdbcType="VARCHAR" property="title"/>
<result column="content" jdbcType="VARCHAR" property="content"/>
<result column="uid" jdbcType="VARCHAR" property="uid"/>
<result column="time" jdbcType="VARCHAR" property="time"/>
<result column="apploud" jdbcType="INTEGER" property="apploud"/>*/
public class TbArcticle {
	private Integer arid;
	private String title;
	private String content;
	private String uid;
	private String time;
	private Integer apploud;
	public TbArcticle() {
	}
	public TbArcticle(Integer arid, String title, String content, String uid, String time, Integer apploud) {
		this.arid = arid;
		this.title = title;
		this.content = content;
		this.uid = uid;
		this.time = time;
		this.apploud = apploud;
	}
	@Override
	public String toString() {
		return "TbArcticle [arid=" + arid + ", title=" + title + ", content=" + content + ", uid=" + uid + ", time="
				+ time + ", apploud=" + apploud + "]";
	}
	public Integer getArid() {
		return arid;
	}
	public void setArid(Integer arid) {
		this.arid = arid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getApploud() {
		return apploud;
	}
	public void setApploud(Integer apploud) {
		this.apploud = apploud;
	}
	
	
}
