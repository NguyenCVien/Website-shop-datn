package com.websiteshop.service;

import java.util.List;
import java.util.Optional;

import com.websiteshop.entity.Cmt;

public interface CmtService {

	List<Cmt> findAll();

	void delete(Cmt entity);

	Optional<Cmt> findById(Long id);

	<S extends Cmt> S save(S entity);

}
