package com.noteCup.member.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.noteCup.member.constant.ResultCode;
import com.noteCup.member.exception.UserAlreadyExistException;
import com.noteCup.member.model.domain.MemberInfo;
import com.noteCup.member.model.domain.VerificationToken;
import com.noteCup.member.model.dto.MemberInput;
import com.noteCup.prototype.service.CUFilesService;
import com.noteCup.prototype.service.UfrmDService;

public interface IMemberService extends CUFilesService, UfrmDService {

	
	/** @formatter:off
	 * @method IMemberService registerNewUserAccount 
	 * : MemberInput에 입력된 정보를 바탕으로 유저 데이터를 Persist하는 함수
	 * @param userform
	 * @return
	 * @throws UserAlreadyExistException
	 * @author 김원빈
	 * @formatter:on
	 */
	UserDetails registerNewUserAccount(MemberInput userform) throws UserAlreadyExistException;

	/** @formatter:off
	 * @method IMemberService checkEmailExist 
	 * : 이메일 중복 검사 함수
	 * @param email
	 * @return
	 * @author 김원빈
	 * @formatter:on
	 */
	ResultCode checkEmailExist(String email);

	/** @formatter:off
	 * @method IMemberService checkUrlExist 
	 * : url 중복 검사 함수
	 * @param url
	 * @return
	 * @author 김원빈
	 * @formatter:on
	 */
	ResultCode checkUrlExist(String url);
	
	
	UserDetails getMember(String verificationToken);
	
	void saveRegisteredUser(MemberInfo user);
	
	void createVerificationToken(MemberInfo user, String token);
	
	VerificationToken getVerificationToken(String VerificationToken);
	
}
