package com.noteCup.member.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.noteCup.member.constant.ResultCode;
import com.noteCup.member.exception.UserAlreadyExistException;
import com.noteCup.member.model.domain.MemberInfo;
import com.noteCup.member.model.domain.VerificationToken;
import com.noteCup.member.model.dto.MemberInput;
import com.noteCup.member.model.vo.MemberView;
import com.noteCup.member.repository.IMemberRepository;
import com.noteCup.member.repository.IVerificationTokenRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
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
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		
		MemberInfo userInfo = memberRepository.findByEmail(email)
				.orElseThrow(()->new UsernameNotFoundException(email));
		//@formatter:off
		return userInfo.setAccountNonExpired(true)
						.setAccountNonLocked(true)
						.setEnabled(true)
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
	public UserDetails registerNewUserAccount(final MemberInput userform) throws UserAlreadyExistException {
				
		if(checkEmailExist(userform.getEmail()).equals(ResultCode.DUPLICATE)) {
			throw new UserAlreadyExistException("There is an account with that email address: " + userform.getEmail());
		}
		MemberInfo user = userform.toEntity();
		
		return memberRepository.save(user);
	}
	
	@Override
	public ResultCode checkEmailExist(final String email) {
		
		return ResultCode.getDupByBool(memberRepository.countByEmail(email) > 0);
	}
	
	
	@Override
	public ResultCode checkUrlExist(final String url) {
		
		return ResultCode.getDupByBool(memberRepository.countByUrl(url) > 0);
	
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
	public UserDetails getMember(final String verificationToken) {
		return tokenRepository.findByToken(verificationToken).getMemberInfo();
	}

	@Override
	public void saveRegisteredUser(final MemberInfo user) {
		memberRepository.save(user);
	}

	@Override
	public void createVerificationToken(final MemberInfo user, final String token) {
		VerificationToken newToken = new VerificationToken(token, user);
		
		log.warn(newToken.toString());
		
		tokenRepository.save(newToken);
	}

	@Override
	public VerificationToken getVerificationToken(final String VerificationToken) {
		return tokenRepository.findByToken(VerificationToken);
	}

	public List<MemberView> getMemberViewList() {
		List<MemberInfo> mList = memberRepository.findAll();
		return this.getMembersView(mList);
	}

	public MemberView getMemberView(long mid) {
		
		return getMembersView(Arrays.asList(memberRepository.findByMid(mid))).get(0);
	}
	private List<MemberView> getMembersView(List<MemberInfo> mList){
		
		ArrayList<MemberView> memberList = new ArrayList<MemberView>();
		
		mList.forEach(m->{
			
			MemberView member = MemberView.builder()
								.mid(m.getMid())
								.nickname(m.getNickname())
								.introduction(m.getIntroduction())
								.build();
			memberList.add(member);
		});
		
		return memberList;
		
	}

	public void deleteVerificationToken(long mid) {
				
		tokenRepository.deleteById(mid);
		
	}



}
