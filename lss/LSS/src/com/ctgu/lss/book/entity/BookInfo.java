package com.ctgu.lss.book.entity;
public class BookInfo {
	private int ctrlno;
	private String book_name;
	private String author;
	private String publish;
	private String publish_year;
	private String book_num;
	private int holding_num;
	private int can_borrow;
	public int getCtrlno() {
		return ctrlno;
	}
	public void setCtrlno(int ctrlno) {
		this.ctrlno = ctrlno;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public String getPublish_year() {
		return publish_year;
	}
	public void setPublish_year(String publish_year) {
		this.publish_year = publish_year;
	}
	public String getBook_num() {
		return book_num;
	}
	public void setBook_num(String book_num) {
		this.book_num = book_num;
	}
	public int getHolding_num() {
		return holding_num;
	}
	public void setHolding_num(int holding_num) {
		this.holding_num = holding_num;
	}
	public int getCan_borrow() {
		return can_borrow;
	}
	public void setCan_borrow(int can_borrow) {
		this.can_borrow = can_borrow;
	}
	public BookInfo(int ctrlno, String book_name, String author, String publish, String publish_year, String book_num,
			int holding_num, int can_borrow) {
		super();
		this.ctrlno = ctrlno;
		this.book_name = book_name;
		this.author = author;
		this.publish = publish;
		this.publish_year = publish_year;
		this.book_num = book_num;
		this.holding_num = holding_num;
		this.can_borrow = can_borrow;
	}
	@Override
	public String toString() {
		return "Book [ctrlno=" + ctrlno + ", book_name=" + book_name + ", author=" + author + ", publish=" + publish
				+ ", publish_year=" + publish_year + ", book_num=" + book_num + ", holding_num=" + holding_num
				+ ", can_borrow=" + can_borrow + "]";
	}
	public BookInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
