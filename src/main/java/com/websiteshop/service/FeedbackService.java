package com.websiteshop.service;

import java.util.List;
import java.util.Optional;
import com.websiteshop.entity.Feedback;

public interface FeedbackService {

	void delete(Feedback entity);

	Optional<Feedback> findById(Long id);

	List<Feedback> findAll();

}
