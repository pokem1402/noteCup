package com.noteCup.www.member.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.noteCup.member.model.dto.MemberInput;


@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class GuestRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	/** @formatter:off
	 * @method GuestRestControllerRest mockRegistration 
	 * 테스트를 위한 가계정 생성
	 * @throws Exception
	 * @author 김원빈
	 * @formatter:on
	 */
	@BeforeEach
	void TestCaseRegistration() throws Exception{
		
		// @formatter:off
		MemberInput member = MemberInput.builder()
							  .email("testAccount@email.com")
							  .introduction("자기소개")
							  .nickname("테스트 닉네임")
							  .password("1234")
							  .matchingPassword("1234")
							  .url("testAccount")
							  .build();
		mockMvc.perform(post("/registration")			
				.flashAttr("user", member)
				.with(csrf()))
				.andExpect(redirectedUrl("/"));
		//@formatter:on				
		
	}
	
	/** @formatter:off
	 * @method GuestRestControllerRest EmailDuplicationTest 
	 * Email Check Test 
	 * @throws Exception
	 * @author 김원빈
	 * @formatter:on
	 */
	@Test
	void EmailDuplicationTest() throws Exception{
		
		String not_exist_email = "notexsit@email.com";
		String exist_email = "exist@email.com";
		
		// @formatter:off
		RequestBuilder request_not_exist_email = post("/api/guest/registration/check/email")
												 .flashAttr("email", not_exist_email)
												 .with(csrf());

		RequestBuilder request_exist_email = post("/api/guest/registration/check/email")
												 .flashAttr("email", exist_email)
												 .with(csrf());

		
		mockMvc.perform(request_not_exist_email)
				.andDo(print());
		mockMvc.perform(request_exist_email)
				.andDo(print());

		// @formatter:on
	}

	/**
	 * 로그인 상태에서 기능을 테스트 하는 방법
	 * @throws Exception
	 */
	@Test
	@WithMockUser(username="testAccount@email.com", password="1234")
	void detailTest() throws Exception{
		mockMvc.perform(post("/api/member/test")
				.with(csrf())
				)
				.andDo(print());

	}
}
