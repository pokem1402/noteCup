package com.noteCup.contents.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noteCup.contents.model.domain.ContentScript;


public interface IScriptRepository extends JpaRepository<ContentScript, Long> {

	void deleteByCid(Object object);

	ContentScript findByCid(Object object);
	
	//List <ContentScript> findByCid(Long cid);

}
