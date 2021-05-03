package com.noteCup.www.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noteCup.reply.controller.ReplyRestController;
import com.noteCup.reply.model.dto.ReplyInput;
import com.noteCup.reply.repository.IReplyRepository;
import com.noteCup.reply.service.ReplyMM;

@WebMvcTest(ReplyRestController.class)
class ReplyTest {
	
	@MockBean
	ReplyMM rm;
	
	@Autowired
	IReplyRepository replyRepository;
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	//https://royleej9.tistory.com/entry/Springboot-junit-controller-%ED%85%8C%EC%8A%A4%ED%8A%B8%ED%95%98%EA%B8%B0
	@Autowired
	private WebApplicationContext ctx;
	
	@Autowired
	private MockMvc mockMvc; //가짜 객체(따라쟁이 객체) 목객체.. 되게 중요함..? 레스트일때는 mock이라 안붙음?...
	

	final ReplyInput replyInput = ReplyInput.builder()
											.cid(1)
											.mid(1)
											.rid(1).rtext("Heollo world").build();
	
	/*
	 * @Before void setup() { this.mockMvc =
	 * MockMvcBuilders.webAppContextSetup(ctx).build(); }
	 */
	
	@Test
	void test() throws Exception{
		//when(rm.getReplyByCid(1)).thenReturn(A)
		this.mockMvc.perform(post("/1/1/reply/view")).andExpect(status().isOk()).andDo(print());
		
	}

}
