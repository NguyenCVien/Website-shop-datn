package com.websiteshop.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.websiteshop.entity.Account;
import com.websiteshop.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {
	// @Query(value = "SELECT * FROM OrderDetails WHERE status =?1", nativeQuery =
	// true)
	// List<OrderDetail> findByStatus(String status);

	@Query(value = "SELECT Status = N'Đang vận chuyển',Discount, Price,OrderDetailId, Username, CreateDay, TelePhone, Address, Name, Email FROM OrderDetails odt inner join Orders od on odt.OrderId = od.OrderId WHERE username= ?1", nativeQuery = true)
	List<OrderDetail> findByStatus(String status);

}
