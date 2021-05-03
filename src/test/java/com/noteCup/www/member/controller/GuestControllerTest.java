package com.noteCup.www.member.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.noteCup.member.model.dto.MemberInput;

@AutoConfigureMockMvc
@SpringBootTest
class GuestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	/** @formatter:off
	 * @method GuestControllerTest Registration_test 
	 * 계정 생성 테스트 함수
	 * @throws Exception
	 * @author 김원빈
	 * @formatter:on
	 */
	@Test
	@Transactional // 별도의 표기가 없을 시 테스트 후 RollBack
	void Registration_test() throws Exception{
	
		// @formatter:off
		MemberInput member = MemberInput.builder()
							  .email("wbkim91@gmail.com")
							  .introduction("자기소개")
							  .nickname("김원빈")
							  .password("1234")
							  .matchingPassword("1234")
							  .url("pokem")
							  .build();
		mockMvc.perform(post("/registration")			
				.flashAttr("user", member)
				.with(csrf())
				)
		.andExpect(redirectedUrl("/"));
				
		

		// @formatter:on

		//@formatter:off
//		RequestBuilder request = post("/registration")
//									.param("email", "pokem@naver.com")
//									.param("nickname", "김원빈")
//									.param("password", "1234")
//									.param("matchingPassword", "1234")
//									.param("url", "pokem")
//									.param("introtudction", "null")
//									.with(csrf());
//		
//		mockMvc.perform(request)
//				.andExpect(redirectedUrl("/"));
		// @formatter:on
		
	}
	
}
