package com.noteCup.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noteCup.www.model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
