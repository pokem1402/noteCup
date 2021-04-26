package com.noteCup.www.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noteCup.www.model.ContentPost;


public interface IPostRepository extends JpaRepository<ContentPost, Long> {

	void deleteByCid(Object object);


}
