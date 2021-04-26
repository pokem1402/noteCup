package com.noteCup.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noteCup.www.model.MemberInput;


@Repository
public interface IMemberRepository extends JpaRepository<MemberInput, String> {
	
	List<MemberInput> findByMid(String mid);
	List<MemberInput> findByMidAndMpwd(String id, String pw);
	
}
