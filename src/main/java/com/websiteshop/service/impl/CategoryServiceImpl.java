package com.websiteshop.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteshop.dao.CategoryDAO;
import com.websiteshop.entity.Category;
import com.websiteshop.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDAO cdao;

	@Override
	public List<Category> findAll() {
		return cdao.findAll();
	}

//	@Override
//	public Product findById(Integer id) {
//		return cdao.findById(id).get();
//	}

	
}
