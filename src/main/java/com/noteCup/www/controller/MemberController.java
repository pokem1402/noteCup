package com.noteCup.www.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.noteCup.www.model.MemberInput;
import com.noteCup.www.service.MemberMM;


@RequestMapping(value = "/member")
@Controller
public class MemberController {

	ModelAndView mav;

	@Autowired
	MemberMM mm;

	@GetMapping("/auth/join")
	public String showRegistrationForm(Model model) {
		MemberInput md = new MemberInput();
		model.addAttribute("user", md);
		return "joinFrm";
	}

	@PostMapping("/auth/join")
	public String registerUserAccount(@ModelAttribute("user") MemberInput mi, HttpServletRequest request,
			Errors errors) {
		mm.create(mi);
		return "index";
	}

	@GetMapping("/auth/login")
	public String signInFrm(WebRequest request, Model model) {
		MemberInput md = new MemberInput();
		model.addAttribute("user", md);
		
		return "loginFrm";
	}
	
	@PostMapping("/auth/login")
	public String signIn(@ModelAttribute("user") MemberInput mi, Model model, HttpSession httpsession) {
		boolean check = mm.signIn(mi);
		
		
		if(check) { 
			model.addAttribute("user", mi);
			httpsession.setAttribute("id", mi.getMid() );
			
			
			return "redirect:/";
		}
		return "loginFrm";
	}
	
	
	@GetMapping("/auth/logout")
	public String logOut(Model model, HttpSession httpsession) {
		httpsession.invalidate();
		return "redirect:/";
	}
}
