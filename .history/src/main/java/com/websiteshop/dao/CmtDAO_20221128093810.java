package com.websiteshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websiteshop.entity.Cmt;
import com.websiteshop.entity.Comment;

@Repository
public interface CmtDAO extends JpaRepository<Cmt, Long> {

}
