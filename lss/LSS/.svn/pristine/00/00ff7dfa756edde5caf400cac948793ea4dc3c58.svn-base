package com.ctgu.lss.address.dao;

import java.util.List;

import com.ctgu.lss.address.entity.Address;
import com.ctgu.lss.common.IBaseDAO;

public interface AddressDao extends IBaseDAO<String, Address> {
	public Address findById(int address_id);
	public List<Address> findAllByCustomerId(String user_id);
	public int getAllCount();
	public boolean doInsert(Address address);
	public boolean doUpdate(Address address);
	public boolean doDelete(int address_id);
}
