package com.websiteshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.websiteshop.entity.Authority;

public interface AuthorityService {

	public List<Authority> findAll();

	<S extends Authority> S save(S entity);

	public List<Authority> findAuthoritiesOfAdministrators();

	public Authority create(Authority auth);

	public Authority delete(Integer id);
}
