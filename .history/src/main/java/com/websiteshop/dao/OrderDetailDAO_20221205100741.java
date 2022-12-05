package com.websiteshop.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.websiteshop.entity.Account;
import com.websiteshop.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {
	@Query(value = "SELECT * FROM OrderDetails WHERE status =?1", nativeQuery = true)
	List<OrderDetail> findByStatus(String status);

	@Query(value = "  SELECT * FROM Orders AS OD INNER JOIN Accounts AS Acc ON OD.Username = Acc.Username inner join OrderDetails as odt on OD.OrderId = odt.OrderId where Status = ?1, ?2", nativeQuery = true)
	List<OrderDetail> findByStatusById(String username, String status);

}
