package com.noteCup.contents.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noteCup.contents.model.domain.ContentLink;

public interface ILinkRepository extends JpaRepository<ContentLink, Long>{

}
