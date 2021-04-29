package com.noteCup.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.noteCup.member.model.domain.MemberInfo;
import com.noteCup.member.model.vo.MemberView;
import com.noteCup.search.service.SearchService;

@RequestMapping(value = "/search")
@Controller

public class SearchController {

	@Autowired
	SearchService SearchService;
	
	@GetMapping("")
	public String MemberList() {		
		
		return "search";
	}
	
	@GetMapping("/member")
	public String searchMember(@RequestParam(value = "member") String member, Model model) {
		//List<MemberView> mSearch = SearchService.searchMember(member);
		System.out.println(member);
		return "memberSearch";
	}  
}
