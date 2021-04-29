package com.noteCup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noteCup.model.Board;

@Repository
public interface IBoardRepository extends JpaRepository<Board, Long> {

}
