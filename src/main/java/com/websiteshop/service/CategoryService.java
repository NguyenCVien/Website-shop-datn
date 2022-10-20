package com.websiteshop.service;

import java.util.List;

import com.websiteshop.entity.Category;

public interface CategoryService {

	List<Category> findAll();

	//Product findById(Integer id);

}
