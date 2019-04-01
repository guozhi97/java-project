package com.ctgu.springmvc.entity;
/*
<id column="anid" jdbcType="INTEGER" property="anid"/>
<result column="content" jdbcType="VARCHAR" property="content"/>
<result column="arid" jdbcType="INTEGER" property="arid"/>
<result column="time" jdbcType="VARCHAR" property="time"/>
<result column="uid" jdbcType="VARCHAR" property="uid"/>
<result column="ansid" jdbcType="INTEGER" property="ansid"/>*/
public class TbAnswer {
	private Integer anid;
	private String content;
	private Integer arid;
	private String time;
	private String uid;
	private Integer ansid;
	public TbAnswer() {
	}
	public TbAnswer(Integer anid, String content, Integer arid, String time, String uid, Integer ansid) {
		this.anid = anid;
		this.content = content;
		this.arid = arid;
		this.time = time;
		this.uid = uid;
		this.ansid = ansid;
	}
	@Override
	public String toString() {
		return "TbAnswer [anid=" + anid + ", content=" + content + ", arid=" + arid + ", time=" + time + ", uid=" + uid
				+ ", ansid=" + ansid + "]";
	}
	public Integer getAnid() {
		return anid;
	}
	public void setAnid(Integer anid) {
		this.anid = anid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getArid() {
		return arid;
	}
	public void setArid(Integer arid) {
		this.arid = arid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Integer getAnsid() {
		return ansid;
	}
	public void setAnsid(Integer ansid) {
		this.ansid = ansid;
	}
	
}
