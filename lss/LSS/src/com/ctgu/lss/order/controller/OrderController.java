package com.ctgu.lss.order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ctgu.lss.address.entity.Address;
import com.ctgu.lss.address.service.AddressService;
import com.ctgu.lss.book.entity.Book;
import com.ctgu.lss.book.entity.BookInfo;
import com.ctgu.lss.book.service.BookService;
import com.ctgu.lss.delivarea.entity.DelivArea;
import com.ctgu.lss.delivarea.service.DelivAreaService;
import com.ctgu.lss.dept.entity.Department;
import com.ctgu.lss.dept.service.DepartmentService;
import com.ctgu.lss.order.entity.Order;
import com.ctgu.lss.order.entity.OrderDetail;
import com.ctgu.lss.order.service.OrderService;
import com.ctgu.lss.user.entity.User;
@Controller
public class OrderController {
	@Resource
	private AddressService addressService;
	@Resource
	private DelivAreaService delivAreaService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private OrderService orderService;
	@Resource
	private BookService bookService;
	@RequestMapping(value = "commit",method = RequestMethod.GET)
	public String commit(ModelMap map,HttpSession session){
		String user_id = (String) session.getAttribute("userId");
		List<DelivArea> delivList = delivAreaService.getAllDeliv();
		List<Department> departmentList  = departmentService.findAll();
		try {
			List<Address> addressList = addressService.getAllCustomerAddress(user_id);
			System.out.println(addressList);
			map.addAttribute("addressList",addressList);
			map.addAttribute("delivList",delivList);
			map.addAttribute("departmentList",departmentList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "jsp/pay";
	}
	
	@RequestMapping("order")
	public String order(){
		return "jsp/order";
	}
	@RequestMapping("orderDetail")
	public String orderDetail(){
		return "jsp/orderinfo";
	}
	
	@RequestMapping("doOrder")
	public void doOrder(HttpSession session,HttpServletResponse response,int address_id,String user_id,String extra){
		try {
			PrintWriter out = response.getWriter();
			List<BookInfo> list = (List<BookInfo>) session.getAttribute("bookList");
			String order_id = orderService.getOrder_id(user_id);
			Order order = new Order(order_id, user_id, address_id, null, null, 0, 0, null, null, extra);
			if (orderService.insertOrder(order)) {
				bookService.insertBookInfo(list, order_id);
				out.print("ok");
			}else{
				out.print("failed");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	@RequestMapping("getMyOrder")
	public String getMyOrder(ModelMap map,HttpSession session){
		String userId = (String) session.getAttribute("userId");
		if(userId == null){
			return "login";
		}
		List<Order> orderList = orderService.findAllOrderByCustomerId(userId);
		List<OrderDetail> orderDetailList = new  ArrayList<OrderDetail>();
		OrderDetail orderDetail = null;
//		map.put("orderList", list);
		for (int i = 0; i < orderList.size(); i++) {
			orderDetail = new OrderDetail();
			Order order = orderList.get(i);
			try {
				Address address = addressService.getAddressById(order.getAddress_id());
				List<Book> bookList = bookService.findBooksByOrderId(order.getOrder_id());
				System.out.println("bookList=" +bookList);
				orderDetail.setOrder(order);
				orderDetail.setAddress(address);
				orderDetail.setList(bookList);
				orderDetailList.add(orderDetail);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(orderDetailList);
		map.put("list", orderDetailList);
		return "jsp/order";
	}
	@RequestMapping("deleteOrder")
	public String deleteOrder(String order_id){
		orderService.doDelete(order_id);
		bookService.doDelete(order_id);
		return "redirect:getMyOrder";
	}
	
}
