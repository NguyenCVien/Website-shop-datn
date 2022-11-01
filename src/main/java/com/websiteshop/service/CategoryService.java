package com.websiteshop.service;

import java.util.List;
import java.util.Optional;

import com.websiteshop.entity.Category;

public interface CategoryService {

	List<Category> findAll();

	Optional<Category> findById(Integer id);

	List<Category> findByNameContaining(String name);

	//void delete(Integer id);

	<S extends Category> S save(S entity);

}
