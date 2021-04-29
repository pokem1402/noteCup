package com.noteCup.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noteCup.member.model.domain.MemberInfo;
import com.noteCup.member.model.domain.VerificationToken;

/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.member.repository
 * @File		: IVerificationTokenRepository.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 29.
 * @type		: IVerificationTokenRepository
 * @version		: 
 * @formatter:on
 */
public interface IVerificationTokenRepository extends JpaRepository<VerificationToken, Long>{

	VerificationToken findByToken(String token);
//	VerificationToken findByMemberInfo(MemberInfo user);
	void deleteById(Long mid);
		
}
