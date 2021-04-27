package com.noteCup.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.noteCup.member.exception.UserAlreadyExistException;
import com.noteCup.member.model.domain.MemberInfo;
import com.noteCup.member.model.dto.MemberInput;
import com.noteCup.member.repository.IMemberRepository;

@Service
public class MemberMM implements UserDetailsService, IMemberService{
	
	ModelAndView mav;
	
	@Autowired
	private IMemberRepository memberRepository;
	

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
						.setCredentialsNonExpired(true)
						.setEnabled(true);
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
	public Long registerNewUserAccount(MemberInput userform) throws UserAlreadyExistException {
				
		if(emailExist(userform.getEmail())) {
			throw new UserAlreadyExistException("There is an account with that email address: " + userform.getEmail());
		}
		MemberInfo user = userform.toEntity();
		
		return memberRepository.save(user).getMid();
	}
	
	private boolean emailExist(String email) {
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

}
