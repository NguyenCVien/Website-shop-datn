package com.websiteshop.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.websiteshop.entity.Statitics;

@Repository
public interface StatiticDAO extends JpaRepository<Statitics, Long> {
	
	@Query(value = "SELECT count(orderId) FROM Orders where MONTH(CreateDay) = 2 and YEAR(CreateDay) = 2022", nativeQuery = true)
	Statitics SLOrder();
}
