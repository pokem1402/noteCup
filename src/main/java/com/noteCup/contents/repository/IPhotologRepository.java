package com.noteCup.contents.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noteCup.contents.model.domain.ContentPhotos;

public interface IPhotologRepository extends JpaRepository<ContentPhotos, Long> {

}
