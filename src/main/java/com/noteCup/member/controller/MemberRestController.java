package com.noteCup.member.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noteCup.member.model.dto.MemberInput;

import lombok.extern.log4j.Log4j2;

/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.member.controller
 * @File		: MemberRestController.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 27.
 * @type		: MemberRestController
 * @version		: 
 * @formatter:on
 */
@Log4j2
@RestController 
@RequestMapping("/member/rest")
public class MemberRestController {

	
	@PostMapping("/getname")
	public ResponseEntity<?> getName(Principal principal){
		
		String [] tokens = principal.toString().split(",");
		
		
		String nickname  = null;
		MemberInput temp = new MemberInput();
		
		for(String token : tokens) {
			if(token.indexOf("nickname=") > 0 ) {
				nickname = token.substring("nickname=".length()+1);
				try {
					temp.setNickname("");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
				
		return new ResponseEntity<>(nickname, HttpStatus.OK);
		
	}

}
