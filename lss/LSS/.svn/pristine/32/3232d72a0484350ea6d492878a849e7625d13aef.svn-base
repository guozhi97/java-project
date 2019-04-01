package com.ctgu.lss.book.service;

import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ctgu.lss.book.dao.BookDao;
import com.ctgu.lss.book.entity.Book;
import com.ctgu.lss.book.entity.BookInfo;

@Service
public class BookService {
	@Resource
	private BookDao bookDao; 
	private BookUtils bk;
	public Book findById(int book_id){
		return bookDao.findById(book_id);
	}
	public List<Book> findBooksByOrderId(String order_id){
		return bookDao.findBooksByOrderId(order_id);
	}
	public boolean doDelete(String order_id){
		return bookDao.deleteBookByOrderId(order_id);
	}
	public boolean doInsert(Book book){
		return bookDao.doInsert(book);
	}
	public List<BookInfo> getSearchBooks(String anywords, int searchType, int page) {
		String st = URLEncoder.encode(anywords);
		String URL_PATH = "http://210.42.38.33/searchresult.aspx?anywords="
				+ st.trim()
				+ "&dt=ALL&cl=ALL&dept=ALL&sf=M_PUB_YEAR&ob=DESC&page=" + page
				+ "&dp=20&sm=table";// 这就是那个url
		System.out.println(URL_PATH);
		
		bk = new BookUtils(URL_PATH);
		List<BookInfo> list = bk.getSearchBookMessage();
		return list;
	}
	
	public int getResultPage(){
		return bk.getResultPages();
	}
	
	
	public void insertBookInfo(List<BookInfo> list,String order_id){
		for (int i = 0; i < list.size(); i++) {
			Book book = new Book(0, order_id, list.get(i).getBook_num(), list.get(i).getBook_name(), list.get(i).getAuthor(), 0);
			bookDao.doInsert(book);
		}
	}
	
	
	
	
}
