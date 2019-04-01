package com.ctgu.springmvc.entity;

public class Requery {
	private Integer rid;
	private String content;
	public Requery() {
	}
	
	public Requery(Integer rid, String content) {
		this.rid = rid;
		this.content = content;
	}

	@Override
	public String toString() {
		return "Requery [rid=" + rid + ", content=" + content + "]";
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
