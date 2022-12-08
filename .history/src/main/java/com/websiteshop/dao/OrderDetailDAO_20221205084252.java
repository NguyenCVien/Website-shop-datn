package com.websiteshop.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.websiteshop.entity.Account;
import com.websiteshop.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {
	@Query("SELECT o FROM OrderDetail o WHERE o.status =?1")
	List<OrderDetail> findByStatus(String status);

	@Query("SELECT o FROM Order AS OD INNER JOIN o.Account AS Acc ON OD.Username = Acc.Username inner join o.OrderDetail as odt on OD.OrderId = odt.OrderId o WHERE o.status =?1")
	List<OrderDetail> findByStatusById(Account username, String status);

}