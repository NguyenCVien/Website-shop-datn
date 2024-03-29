package com.websiteshop.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.websiteshop.entity.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order, Long> {

	@Query("SELECT o FROM Order o WHERE o.account.username=?1")
	Page<Order> findByUsername(String username, Pageable pageable);

	@Query(value = "DELETE FROM Orders WHERE username=?1", nativeQuery = true)
	Order deleteOrderByUsername(String username);

	List<Order> findByNameContaining(String name);

	Page<Order> findByNameContaining(String name, Pageable pageable);

	@Query(value = "select * from Orders where Status like ?1 and Username like ?2", nativeQuery = true)
	List<Order> findByStatus(String status, String username);

	@Query(value = "SELECT * FROM Orders WHERE status =?1", nativeQuery = true)
	List<Order> findByStatus(String status);

}
