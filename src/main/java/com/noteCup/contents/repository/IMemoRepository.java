package com.noteCup.contents.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noteCup.contents.model.domain.ContentMemo;

public interface IMemoRepository extends JpaRepository<ContentMemo, Long> {

}
