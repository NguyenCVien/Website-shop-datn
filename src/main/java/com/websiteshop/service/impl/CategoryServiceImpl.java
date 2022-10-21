package com.websiteshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	
	@Override
	public Optional<Category> findById(Integer id) {
		return cdao.findById(id);
	}

	@Override
	public <S extends Category> S save(S entity) {
		return cdao.save(entity);
	}
	@Override
	public List<Category> findByNameContaining(String name) {
		return cdao.findByNameContaining(name);
	}
	
//	@Override
//	public void delete(Category entity) {
//		cdao.delete(entity);
//	}

//	@Override
//	public Product findById(Integer id) {
//		return cdao.findById(id).get();
//	}

}
