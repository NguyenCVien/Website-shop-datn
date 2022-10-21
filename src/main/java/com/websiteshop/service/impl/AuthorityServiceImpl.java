package com.websiteshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteshop.dao.AccountDAO;
import com.websiteshop.dao.AuthorityDAO;
import com.websiteshop.entity.Account;
import com.websiteshop.entity.Authority;
import com.websiteshop.service.AuthorityService;


@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	AuthorityDAO dao;
	
	@Autowired
	AccountDAO acdao;

	@Override
	public List<AuthorityDAO> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
//		List<Account> accounts = acdao.getAdministratiors();
//		return dao.authoritiesOf(accounts);
		return null;
	}
	
	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
		
	}

	@Override
	public Authority create(Authority auth) {
		return dao.save(auth);
	}
}
