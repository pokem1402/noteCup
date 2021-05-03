package com.noteCup.www.member.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.noteCup.member.model.dto.MemberInput;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional // 별도의 표기가 없을 시 테스트 후 RollBack
@TestInstance(Lifecycle.PER_CLASS)
class GuestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	/**
	 * Test 계정 생성
	 * @throws Exception
	 */
	@BeforeAll
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
	 * @method GuestControllerTest Registration_test 
	 * 계정 생성 테스트 함수
	 * @throws Exception
	 * @author 김원빈
	 * @formatter:on
	 */
	@Test
	void Registration_test() throws Exception{
	
		// @formatter:off
		
		MemberInput member_email_dup = MemberInput.builder()
										  .email("testAccount@email.com")
										  .introduction("자기소개")
										  .nickname("테스트 닉네임")
										  .password("1234")
										  .matchingPassword("1234")
										  .url("testAccountUrl")
										  .build();

		MemberInput member_url_dup = MemberInput.builder()
										  .email("testAccount2@email.com")
										  .introduction("자기소개")
										  .nickname("테스트 닉네임")
										  .password("1234")
										  .matchingPassword("1234")
										  .url("testAccount")
										  .build();

		
		
//		mockMvc.perform(post("/registration")			
//				.flashAttr("user", member_email_dup)
//				.with(csrf()))
//				.andExpect(redirectedUrl("/"));

//		mockMvc.perform(post("/registration")			
//				.flashAttr("user", member_url_dup)
//				.with(csrf()))
//				.andExpect(redirectedUrl("/"));

//		RequestBuilder request = post("/registration")
//									.param("email", "testAccount@email.com")
//									.param("nickname", "테스트 닉네임")
//									.param("password", "1234")
//									.param("matchingPassword", "1234")
//									.param("url", "testAccount")
//									.param("introtudction", "null")
//									.with(csrf());
//		
//		mockMvc.perform(request)
//				.andExpect(redirectedUrl("/"));
		// @formatter:on
		
	}
	
//	@Test
	void Login_Test() throws Exception{
		
		// @formatter:off
	
		RequestBuilder login_request = post("/login")
							.param("username", "wbkim91@gmail.com")
							.param("password", "1234")
							.with(csrf());
		
		mockMvc.perform(login_request)
				.andExpect(redirectedUrl("/"));
		
	
	}
	

}
