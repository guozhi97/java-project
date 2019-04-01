package com.ctgu.springmvc.entity;

//function arc(mico,mname,mdisc,mtitle,mcontent,marid,mtime,mapploud){//一篇文章或提问的对象
public class OneArcticle {
	
	private String ico;//用户头像
	private String name;//用户名字
	private String disc;//用户个性签名
	private String title;//提问或者文章的标题
	private String content;//提问或文章的内容
	private Integer arid;//文章的id号
	private String time;//发表时间
	private Integer apploud;//点赞数
	private Integer ansnum;//评论的json数组字符串
	
	public OneArcticle() {
		// TODO Auto-generated constructor stub
	}

	public OneArcticle(String ico, String name, String disc, String title, String content, Integer arid, String time,
			Integer apploud, Integer ansnum) {
		this.ico = ico;
		this.name = name;
		this.disc = disc;
		this.title = title;
		this.content = content;
		this.arid = arid;
		this.time = time;
		this.apploud = apploud;
		this.ansnum = ansnum;
	}

	public void comeOn(TbArcticle ta,TbUser tu) {//从这里像个对象获取值
		setIco(tu.getIco());
		setName(tu.getName());
		setDisc(tu.getDisc());
		setTitle(ta.getTitle());
		setContent(ta.getContent());
		setArid(ta.getArid());
		setTime(ta.getTime());
		setApploud(ta.getApploud());
	}
	
	@Override
	public String toString() {
		return "OneArcticle [ico=" + ico + ", name=" + name + ", disc=" + disc + ", title=" + title + ", content="
				+ content + ", arid=" + arid + ", time=" + time + ", apploud=" + apploud + ", ansnum=" + ansnum
				+ "]";
	}

	public Integer getAnsnum() {
		return ansnum;
	}

	public void setAnsnum(Integer ansnum) {
		this.ansnum = ansnum;
	}

	public String getIco() {
		return ico;
	}

	public void setIco(String ico) {
		this.ico = ico;
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

	public Integer getApploud() {
		return apploud;
	}

	public void setApploud(Integer apploud) {
		this.apploud = apploud;
	}


}
