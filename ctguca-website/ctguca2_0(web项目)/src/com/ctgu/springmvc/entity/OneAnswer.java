package com.ctgu.springmvc.entity;

public class OneAnswer {
	
	private String name;//用户名称
	private String ico;//用户头像
	private String time;//发表时间
	private Integer anid;//评论id
	private String content;//评论内容
		
	public OneAnswer() {
		// TODO Auto-generated constructor stub
	}

	public OneAnswer(String name, String ico, String time, Integer anid, String content) {
		this.name = name;
		this.ico = ico;
		this.time = time;
		this.anid = anid;
		this.content = content;
	}

	public void comeOn(TbUser tu,TbAnswer ta) {
		this.name=tu.getName();
		this.ico=tu.getIco();
		this.time=ta.getTime();
		this.anid=ta.getAnid();
		this.content=ta.getContent();
	}
	
	@Override
	public String toString() {
		return "OneAnswer [name=" + name + ", ico=" + ico + ", time=" + time + ", anid=" + anid + ", content=" + content
				+ "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIco() {
		return ico;
	}

	public void setIco(String ico) {
		this.ico = ico;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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
	

}
