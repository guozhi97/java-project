package com.ctgu.springmvc.entity;


public class Problem {
	private Integer pid;
	private String title;
	private String time;
	private String content;
	private Integer sum;
	public Problem() {
	}

	@Override
	public String toString() {
		return "Problem [pid=" + pid + ", title=" + title + ", time=" + time + ", content=" + content + ", sum=" + sum
				+ "]";
	}

	public Problem(Integer pid, String title, String time, String content, Integer sum) {
		this.pid = pid;
		this.title = title;
		this.time = time;
		this.content = content;
		this.sum = sum;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
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
}
