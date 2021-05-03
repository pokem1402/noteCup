package com.noteCup.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noteCup.member.model.domain.MemberInfo;


@Repository
public interface IMemberRepository extends JpaRepository<MemberInfo, Long> {
	
	Optional<MemberInfo> findByEmail(String email);
	int countByEmail(String email);
	int countByNickname(String nickname);
	int countByMid(String id);
	int countByUrl(String url);
	
	MemberInfo findByNickname(String nickname);//by simok
	MemberInfo findByMid(Long mid);

}
