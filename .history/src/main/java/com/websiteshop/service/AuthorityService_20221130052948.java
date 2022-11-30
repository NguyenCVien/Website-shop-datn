package com.websiteshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.websiteshop.entity.Authority;

public interface AuthorityService {

	public List<Authority> findAll();

	<S extends Authority> S save(S entity);

	Optional<Authority> findById(Integer id);

	public List<Authority> findAuthoritiesOfAdministrators();

	public Authority create(Authority auth);

	public void deleteById(Authority authority);

	public void delete(Authority entity);

	public void deleteAll();

}
