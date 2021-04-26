package com.noteCup.www.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.noteCup.www.model.ContentPost;

public interface IPostRepository extends JpaRepository<ContentPost, Long> {


	ContentPost findByCid(Object object);

}
