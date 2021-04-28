package com.noteCup.member.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.noteCup.member.exception.UserAlreadyExistException;
import com.noteCup.member.model.domain.MemberInfo;
import com.noteCup.member.model.domain.VerificationToken;
import com.noteCup.member.model.dto.MemberInput;
import com.noteCup.member.repository.IMemberRepository;
import com.noteCup.member.repository.IVerificationTokenRepository;

@Service
public class MemberMM implements UserDetailsService, IMemberService{
	
	ModelAndView mav;
	
	@Autowired
	private IMemberRepository memberRepository;
	
	@Autowired
	private IVerificationTokenRepository tokenRepository;

	/** @formatter:off
	 * @Override 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 * @method MemberMM loadUserByUsername
	 *
	 * @param email
	 * @return MemberInfo
	 * @throws UsernameNotFoundException
	 * @author 김원빈
	 * @Date 2021.04.27
	 * @formatter:on
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		MemberInfo userInfo = memberRepository.findByEmail(email)
				.orElseThrow(()->new UsernameNotFoundException(email));
		//@formatter:off
		return userInfo.setAccountNonExpired(true)
						.setAccountNonLocked(true)
						.setCredentialsNonExpired(true);
		//@formatter:on
	}
	
	@Override
	public boolean create(MultipartHttpServletRequest inputReq) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/** @formatter:off
	 * @method MemberMM registerNewUserAccount 
	 *
	 * @param userform
	 * @return MID
	 * @throws UserAlreadyExistException
	 * @author 김원빈
	 * @formatter:on
	 */
	@Override
	public UserDetails registerNewUserAccount(MemberInput userform) throws UserAlreadyExistException {
				
		if(checkEmailExist(userform.getEmail())) {
			throw new UserAlreadyExistException("There is an account with that email address: " + userform.getEmail());
		}
		MemberInfo user = userform.toEntity();
		
		return memberRepository.save(user);
	}
	
	@Override
	public boolean checkEmailExist(String email) {
		return memberRepository.countByEmail(email) > 0;
	}
	
	public boolean update(MemberInput mi) {

		return false;
	}
	
	@Override
	public boolean update(MultipartHttpServletRequest updateReq) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ModelAndView updateFrm(Long itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long itemId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserDetails getMember(String verificationToken) {
		return tokenRepository.findByToken(verificationToken).getMemberInfo();
	}

	@Override
	public void saveRegisteredUser(MemberInfo user) {
		memberRepository.save(user);
	}

	@Override
	public void createVerificationToken(MemberInfo user, String token) {
		VerificationToken newToken = new VerificationToken(token, user);
		tokenRepository.save(newToken);
	}

	@Override
	public VerificationToken getVerificationToken(String VerificationToken) {
		return tokenRepository.findByToken(VerificationToken);
	}


}
