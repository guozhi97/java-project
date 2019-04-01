package com.ctgu.lss.address.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ctgu.lss.address.dao.AddressDao;
import com.ctgu.lss.address.entity.Address;

@Service
public class AddressService {
	@Resource
	private AddressDao addressDao;

	public boolean addNewAddress(Address vo) throws Exception {

		return addressDao.doInsert(vo);
	}

	public boolean updateAddress(Address vo) throws Exception {
		return addressDao.doUpdate(vo);
	}

	public List<Address> getAllCustomerAddress(String user_id)
			throws Exception {
		return addressDao.findAllByCustomerId(user_id);
	}

	public Address getAddressById(int address_id) throws Exception {
		return addressDao.findById(address_id);
	}

	public boolean deleteAddress(String address_id) throws Exception {
		return addressDao.doDelete(address_id);
	}

	public boolean isDefaultAddress(String address_id) throws Exception {
		if (null == addressDao.findById(address_id))
			return false;
		else
			return true;
	}
}
