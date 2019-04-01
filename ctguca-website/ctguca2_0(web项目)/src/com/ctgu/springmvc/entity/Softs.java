package com.ctgu.springmvc.entity;


public class Softs {
	private Integer soid;
	private String name;
	private String url;
	
	
	public Softs() {
	}


	public Softs(Integer soid, String name, String url) {
		this.soid = soid;
		this.name = name;
		this.url = url;
	}


	@Override
	public String toString() {
		return "Softs [soid=" + soid + ", name=" + name + ", url=" + url + "]";
	}


	public Integer getSoid() {
		return soid;
	}


	public void setSoid(Integer soid) {
		this.soid = soid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}

	
	
}
