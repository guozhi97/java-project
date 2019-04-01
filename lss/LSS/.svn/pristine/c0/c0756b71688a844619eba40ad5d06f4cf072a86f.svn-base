package com.ctgu.lss.address.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.ctgu.lss.address.entity.Address;
import com.ctgu.lss.address.service.AddressService;

@Controller
public class AddressController {
	@Resource
	private AddressService addressService;
	
	
	@RequestMapping("addAddress")
	public void addAddress(String user_id,String user_name,String address,String mobile,HttpServletResponse response){
		Address addr = new Address(0, user_id, user_name, address, mobile, 0);
		System.out.println("-------------------user_id="+user_id );
		try {
			if(addressService.addNewAddress(addr)){
		/*		List<Address> list = addressService.getAllCustomerAddress(user_id);
				JSONObject data = new JSONObject();
				JSONArray addressList = JSONArray.fromObject(list);
				data.put("addressList", addressList);*/
				PrintWriter out = response.getWriter(); 
				out.print("ok");
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("removeAddress")
	public void removeAddress(String address_id,HttpServletResponse response){
		System.out.println(address_id);
		try {
			if (addressService.deleteAddress(address_id)) {
				PrintWriter out = response.getWriter();
				out.print("ok");
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
