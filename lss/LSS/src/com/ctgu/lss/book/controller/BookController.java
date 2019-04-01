package com.ctgu.lss.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ctgu.lss.book.entity.BookInfo;
import com.ctgu.lss.book.service.BookService;

@Controller
public class BookController {
	@Resource
	private BookService bookService;
	@RequestMapping("searchBook")
	public void searchBook(String anywords, int searchType, int page,
			HttpServletResponse resp) {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = resp.getWriter();
			if (anywords.equals(""))
				out.println("failed");
			else {
				List<BookInfo> list = bookService.getSearchBooks(anywords,
						searchType, page);
				if (list == null)
					out.println("failed");
				else {
					JSONObject data = new JSONObject();
					JSONArray jsonArray = JSONArray.fromObject(list);
					data.put("bookMessage", jsonArray);
					data.put("resultPages", bookService.getResultPage());
					System.out.println(data.toString());
					out.println(data.toString());
				}
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("add")
	public void addToBuyingCar(HttpSession session,HttpServletResponse response,String ctrlno, 
			String book_name,String author,String publish,String publish_year,String book_num,
			String holding_num,String can_borrow){
		int cNo = Integer.parseInt(ctrlno);
		int hNum = Integer.parseInt(holding_num);
		int canB = Integer.parseInt(can_borrow);
		System.out.println(book_name);
		BookInfo b = new BookInfo(cNo, book_name, author, publish, publish_year, book_num, hNum, canB);
		List<BookInfo> list = (List<BookInfo>) session.getAttribute("bookList");
		if (list == null) {
			list = new ArrayList<BookInfo>();
		}
		if (!list.contains(b)) {
			list.add(b);
		}
		try {
			PrintWriter out = response.getWriter();
			out.print(list.size());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(list);
		session.setAttribute("bookList", list);
	}
	
	
	@RequestMapping("remove")
	public void remove(HttpSession session,HttpServletResponse response,String book_num){
		try {
			PrintWriter out = response.getWriter();
			List<BookInfo> list = (List<BookInfo>) session.getAttribute("bookList");
			if (list == null) {
				out.print(0);
			}else{
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getBook_num().equals(book_num)) {
						list.remove(i);
					}
				}
				out.print(list.size());
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("myshopcart")
	public String shopCar(){
		return "jsp/shopcart";
	}
	
	
}
