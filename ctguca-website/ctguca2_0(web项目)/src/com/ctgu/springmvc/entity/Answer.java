package com.ctgu.springmvc.entity;


public class Answer {
	private Integer aid;
	private String content;
	private String time;
	private Integer pid;
	
	
	public Answer() {
	}

	public Answer(Integer aid, String content, String time, Integer pid) {
		this.aid = aid;
		this.content = content;
		this.time = time;
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "Answer [aid=" + aid + ", content=" + content + ", time=" + time + ", pid=" + pid + "]";
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	
}
