package com.websiteshop.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteshop.dao.CmtDAO;
import com.websiteshop.entity.Cmt;
import com.websiteshop.service.CmtService;

@Service
public class CmtServiceImpl implements CmtService {

	@Autowired
	CmtDAO cdao;

	@Override
	public <S extends Cmt> S save(S entity) {
		return cdao.save(entity);
	}

	@Override
	public Optional<Cmt> findById(Long id) {
		return cdao.findById(id);
	}

	@Override
	public void delete(Cmt entity) {
		cdao.delete(entity);
	}

	@Override
	public List<Cmt> findAll() {
		return cdao.findAll();
	}

}
