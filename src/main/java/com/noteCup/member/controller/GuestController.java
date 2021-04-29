package com.noteCup.member.controller;

import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.noteCup.member.exception.UserAlreadyExistException;
import com.noteCup.member.model.domain.MemberInfo;
import com.noteCup.member.model.domain.VerificationToken;
import com.noteCup.member.model.dto.MemberInput;
import com.noteCup.member.registration.OnRegistrationCompleteEvent;
import com.noteCup.member.service.MemberMM;

import lombok.extern.log4j.Log4j2;

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
@Log4j2
@Controller
public class GuestController {

	@Autowired
	ApplicationEventPublisher eventPublisher;

	@Autowired
	MessageSource messages;
	
	@Autowired
	MemberMM mService;
	
	ModelAndView mav;

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
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid final MemberInput userDto, HttpServletRequest request, Errors errors) {
		

		try {
			MemberInfo userRegistered = (MemberInfo) mService.registerNewUserAccount(userDto);

			String appUrl = request.getContextPath();
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(userRegistered, request.getLocale(), appUrl));
			
		} catch (UserAlreadyExistException uaeEx) {
			mav = new ModelAndView("/registration", "user", userDto);
			mav.addObject("message", "An account for that username/email already exist");
			return mav;
		} catch (RuntimeException ex) {
			return new ModelAndView("emailError", "user", userDto);
		}
		return new ModelAndView("/", "user", userDto);
	}


	
	@GetMapping("/regstration/Confirm")
	public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token){
		
		
		Locale locale = request.getLocale();
		VerificationToken verificationToken = mService.getVerificationToken(token);
		if(verificationToken == null) {
			String messageInvalid = messages.getMessage("auth.message.invaildToken", null, locale);
			model.addAttribute("message", messageInvalid);
			return "redirect:/badUser.html?lang=" + locale.getLanguage();
		}
		
		MemberInfo user = verificationToken.getMemberInfo();
		Calendar cal = Calendar.getInstance();
		if((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			String messageExpired = messages.getMessage("auth.message.expired", null, locale);
			model.addAttribute("message", messageExpired);
			return "redirect:/badUser.html?lang="+locale.getLanguage();
		}
		
		user.setEnabled(true);
		mService.saveRegisteredUser(user);
		return "redirect:/login.html?lang=" + request.getLocale().getLanguage();
	}
	
}