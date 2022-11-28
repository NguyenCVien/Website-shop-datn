package com.websiteshop.service;

import java.util.List;
import java.util.Optional;
import com.websiteshop.entity.Comment;

public interface CommentService {

	List<Comment> findAll();

	void delete(Comment entity);

	Optional<Comment> findById(Long id);

	<S extends Comment> S save(S entity);

	<S extends Cmt> S save(S entity);

}
