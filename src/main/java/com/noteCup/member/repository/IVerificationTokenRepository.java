package com.noteCup.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noteCup.member.model.domain.MemberInfo;
import com.noteCup.member.model.domain.VerificationToken;

public interface IVerificationTokenRepository extends JpaRepository<VerificationToken, Long>{

	VerificationToken findByToken(String token);
	VerificationToken findByMemberInfo(MemberInfo user);
		
}
