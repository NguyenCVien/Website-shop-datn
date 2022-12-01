package com.websiteshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.websiteshop.entity.Account;

public interface UserDAO extends JpaRepository<Account, String> {

}
