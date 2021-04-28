package com.noteCup.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.noteCup.member.service.MemberMM;


/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.member.controller
 * @File		: MemberController.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 27.
 * @type		: MemberController
 * @version		: 
 * @formatter:on
 */
@RequestMapping(value = "/member")
@Controller
public class MemberController {

	ModelAndView mav;

	@Autowired
	MemberMM mm;
	
	@GetMapping("/loginSuccess")
	public String loginSuccess(WebRequest request, Model model) {

		model.addAttribute("message", "loginSuccess");
		return "loginSuccess";
	}
	
	
}
