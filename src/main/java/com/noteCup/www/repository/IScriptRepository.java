package com.noteCup.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noteCup.www.model.ContentScript;


public interface IScriptRepository extends JpaRepository<ContentScript, Long> {

	void deleteByCid(Object object);

}
