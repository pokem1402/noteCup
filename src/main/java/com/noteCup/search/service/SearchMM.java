package com.noteCup.search.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noteCup.member.model.domain.MemberInfo;
import com.noteCup.member.model.vo.MemberView;
import com.noteCup.member.repository.IMemberRepository;
import com.noteCup.reply.repository.IReplyRepository;

@Service
public class SearchMM {

	
	  @Autowired 
	  IMemberRepository memberRepository;
	  
	  @Transactional 
	  public List<MemberView> searchMember(String member) {
	 
		//List<MemberInfo> mInfo =
			  
		return null;
	  }
	 

	

}
