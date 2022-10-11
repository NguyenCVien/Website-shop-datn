package com.websiteshop.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.websiteshop.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, String> {
	
	
}
