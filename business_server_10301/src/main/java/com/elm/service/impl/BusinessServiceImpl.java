package com.elm.service.impl;

import com.elm.mapper.BusinessMapper;
import com.elm.po.Business;
import com.elm.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService{
	
	@Autowired
	private BusinessMapper businessMapper;

	@Override
	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) {
		return businessMapper.listBusinessByOrderTypeId(orderTypeId);
	}
	
	@Override
	public Business getBusinessById(Integer businessId) {
		return businessMapper.getBusinessById(businessId);
	}
}
