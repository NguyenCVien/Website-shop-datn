package com.websiteshop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.websiteshop.dao.FeedbackDAO;
import com.websiteshop.dao.OrderDAO;
import com.websiteshop.entity.Feedback;
import com.websiteshop.entity.Order;
import com.websiteshop.service.FeedbackService;
import com.websiteshop.service.OrderService;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	FeedbackDAO fdao;

	@Override
	public List<Feedback> findAll() {
		return fdao.findAll();
	}

	@Override
	public Optional<Feedback> findById(Long id) {
		return fdao.findById(id);
	}

	@Override
	public void delete(Feedback entity) {
		fdao.delete(entity);
	}
}
