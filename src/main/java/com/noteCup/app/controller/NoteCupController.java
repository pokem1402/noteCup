package com.noteCup.app.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoteCupController {

	@GetMapping("/")
	public String main(Principal principal) {

		if (principal == null) // 비로그인
		{
			return "main";			
		} else {
			return "myMain";
		}

	}

}
