package com.ctgu.lss.delivarea.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ctgu.lss.delivarea.dao.DelivAreaDao;
import com.ctgu.lss.delivarea.entity.DelivArea;
@Service
public class DelivAreaService {
	@Resource
	private DelivAreaDao delivAreaDao;
	public List<DelivArea> getAllDeliv(){
		return delivAreaDao.findAll();
	}
}
