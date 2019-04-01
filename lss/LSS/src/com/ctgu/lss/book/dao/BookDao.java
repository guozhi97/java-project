package com.ctgu.lss.book.dao;

import java.util.List;

import com.ctgu.lss.book.entity.Book;

public interface BookDao {
	public Book findById(int book_id);
	public List<Book> findBooksByOrderId(String order_id);
	public List<Book> findAll();
	public boolean deleteBookByOrderId(String order_id);
	public boolean doInsert(Book book);
}
