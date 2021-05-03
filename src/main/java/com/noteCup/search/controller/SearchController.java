package com.noteCup.search.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.noteCup.member.model.domain.MemberInfo;
import com.noteCup.member.model.vo.MemberView;
import com.noteCup.search.service.SearchMM;

@RequestMapping(value = "/search")
@Controller
public class SearchController {

	@Autowired
	SearchMM sm;
	
	/**
	 * descript : 시목아 이 메소드는 사실 필요없을거같아 페이지이동해서 검색창을 가는 거보다, 검색창을 header에 넣어서 하는게 
	 * @return		나은거같음.
	 * daniel
	 * 2021. 4. 30.
	 */
	@GetMapping("/")
	public String MemberList() {
		
		return "search";
	}
	
	@GetMapping("/member")
	public String searchMember(@RequestParam(value = "member") String member, Model model) {
		List<MemberView> mSearch = sm.searchMember(member);
		System.out.println(member);
		return "memberSearch";
	}  
	
	/**
	 * descript : 이 메소드는 req에서 get param해서 검색 type과 검색쿼리를 가져와서 type별로 어떤 repo를 탈지 서비스클래스
	 * 				에서 정의할려고..
	 * @param model
	 * @return
	 * daniel
	 * 2021. 4. 30.
	 * searchSomething
	 */
	@GetMapping("")
	public ModelAndView searchSomething(WebRequest req) {		
		Map<String, String> hm = new HashMap<String,String>();
		hm.put("type", req.getParameter("type"));
		hm.put("query", req.getParameter("query"));
		ModelAndView mav = sm.searchSomething(hm);
		return mav;
	}
	
}
