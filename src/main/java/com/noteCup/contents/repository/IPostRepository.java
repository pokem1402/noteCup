package com.noteCup.contents.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.noteCup.contents.model.domain.ContentPost;

public interface IPostRepository extends JpaRepository<ContentPost, Long> {


	ContentPost findByCid(Object object);

}
