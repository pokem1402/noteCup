package com.noteCup.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.noteCup.contents.model.vo.ResponseMessage;
import com.noteCup.member.constant.ResultCode;
import com.noteCup.member.service.MemberMM;

@RestController
@RequestMapping("/api/guest")
public class GuestRestController {

	
	@Autowired
	MessageSource messages;
	
	@Autowired
	MemberMM mService;
	
	@GetMapping("/registration/message")
	public ResponseEntity<?> getMessage(){
		return new ResponseEntity<>("message", HttpStatus.OK);
	}
	
	
	@PostMapping("/registration/check/email")
	public ResponseEntity<?> checkEmail(@ModelAttribute(name = "email") String email, HttpServletRequest request){
		
		ResultCode result = mService.checkEmailExist(email);
		
		
		ResponseMessage rm = ResponseMessage.builder()
								.message(messages.getMessage(result.getMessage()+".email", null, request.getLocale()))
								.status(result.getBoolValue())
								.build();
		
		return new ResponseEntity<>(rm, HttpStatus.OK);
		
	}

	@PostMapping("/registration/check/url")
	public ResponseEntity<?> checkUrl(@ModelAttribute(name = "url") String url, HttpServletRequest request){
		
		ResultCode result = mService.checkUrlExist(url);
		
		
		ResponseMessage rm = ResponseMessage.builder()
								.message(messages.getMessage(result.getMessage()+".url", null, request.getLocale()))
								.status(result.getBoolValue())
								.build();
		
		return new ResponseEntity<>(rm, HttpStatus.OK);
		
	}
	
}
