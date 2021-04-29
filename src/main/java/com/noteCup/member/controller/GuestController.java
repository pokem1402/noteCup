package com.noteCup.member.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.noteCup.member.exception.UserAlreadyExistException;
import com.noteCup.member.model.dto.MemberInput;
import com.noteCup.member.service.MemberMM;

/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * MemberController for Guest Service
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.member.controller
 * @File		: GuestController.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 27.
 * @type		: GuestController
 * @version		: 1.0
 * @formatter:on
 */
@Controller
public class GuestController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	ModelAndView mav;

	@Autowired
	MemberMM mService;

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}
	
	@GetMapping("/registration")
	public String registrationForm(WebRequest request, Model model) {
		MemberInput userform = new MemberInput();
		model.addAttribute("user", userform);
		return "registration";
	}

	@PostMapping("/registration")
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid final MemberInput userDto) {
		
		mav = new ModelAndView();
		mav.setViewName("/registration");
		
		LOGGER.warn("Registering user account with information : {}", userDto);
		
		try {
			Long id = mService.registerNewUserAccount(userDto);

		} catch (UserAlreadyExistException uaeEx) {
			mav.addObject("message", "An account for that username/email already exist");
			return mav;
		}
		return new ModelAndView("successRegistration", "user", userDto);
	}

}