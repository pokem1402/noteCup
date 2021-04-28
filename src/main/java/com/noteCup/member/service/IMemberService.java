package com.noteCup.member.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.noteCup.member.exception.UserAlreadyExistException;
import com.noteCup.member.model.domain.MemberInfo;
import com.noteCup.member.model.domain.VerificationToken;
import com.noteCup.member.model.dto.MemberInput;
import com.noteCup.prototype.service.CUFilesService;
import com.noteCup.prototype.service.UfrmDService;

public interface IMemberService extends CUFilesService, UfrmDService {

	UserDetails registerNewUserAccount(MemberInput userform) throws UserAlreadyExistException;

	boolean checkEmailExist(String email);

	UserDetails getMember(String verificationToken);
	
	void saveRegisteredUser(MemberInfo user);
	
	void createVerificationToken(MemberInfo user, String token);
	
	VerificationToken getVerificationToken(String VerificationToken);
	
}
