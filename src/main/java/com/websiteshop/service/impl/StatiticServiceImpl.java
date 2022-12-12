package com.websiteshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.websiteshop.dao.StatiticDAO;
import com.websiteshop.entity.Order;
import com.websiteshop.entity.Statitics;
import com.websiteshop.service.StatiticService;

@Service
public class StatiticServiceImpl implements StatiticService {

	@Autowired
	StatiticDAO sdao;

	@Override
	public List<Statitics> findAll() {
		return sdao.findAll();
	}

	@Override
	public Statitics SLOrder() {
		return sdao.SLOrder();
	}
	
	
	
}
