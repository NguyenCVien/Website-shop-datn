package com.websiteshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.websiteshop.entity.Order;
import com.websiteshop.entity.Statitics;

@Service
public interface StatiticService {

	List<Statitics> findAll();

	Statitics SLOrder();

}
