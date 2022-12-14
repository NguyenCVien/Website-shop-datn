package com.websiteshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.websiteshop.entity.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
	List<Product> findByNameContaining(String name);

	@Query("SELECT p FROM Product p WHERE p.category.categoryId=?1")
	List<Product> findByCategoryId(String cid);

}
