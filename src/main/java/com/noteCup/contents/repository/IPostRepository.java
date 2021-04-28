package com.noteCup.contents.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noteCup.contents.model.domain.ContentPost;

public interface IPostRepository extends JpaRepository<ContentPost, Long> {


	ContentPost findByCid(Object object);

}
