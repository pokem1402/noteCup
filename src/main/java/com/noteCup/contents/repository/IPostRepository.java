package com.noteCup.contents.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noteCup.contents.model.domain.ContentPost;

public interface IPostRepository extends JpaRepository<ContentPost, Long> {


	ContentPost findByCid(Object object);

	List<ContentPost> findByCtitleContainingIgnoreCaseOrCtextContainingIgnoreCase(String query, String query2);

	//List <ContentPost> findByCid(Long cid);
}
