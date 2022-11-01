package com.websiteshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.websiteshop.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String> {

}
