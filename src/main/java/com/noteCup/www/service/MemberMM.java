package com.noteCup.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;

import com.noteCup.www.model.MemberInput;
import com.noteCup.www.repository.IMemberRepository;

@Service
public class MemberMM {
	
	ModelAndView mav;
	
	@Autowired
	private IMemberRepository imdao;
	
	public boolean create(MemberInput mi) {
		
//		boolean check = imdao.create(mi);
//		
//		if(check) mav.setViewName("index");
//		else mav.setViewName("/");
//		

		MemberInput check = imdao.saveAndFlush(mi);
		
		return false;
	}
	
	public boolean update(MemberInput mi) {

		return false;
	}
	
	public boolean find(String id) {
		
		List<MemberInput> result = imdao.findByMid(id) ;

		return result.size() > 0;

	}

	public boolean signIn(MemberInput mi) {
		String id = mi.getMid();
		String pw = mi.getMpwd();
		List<MemberInput> check = imdao.findByMidAndMpwd(id,pw);
						
		return check.size() > 0;
	}

}
