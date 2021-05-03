package com.noteCup.www.reply.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.noteCup.reply.model.dto.ReplyInput;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class ReplyRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void TestCaseReply() throws Exception{
		
		// @formatter:off
		ReplyInput replyInput = ReplyInput.builder()
										.cid(1)
										.mid(1)
										.rtext("hello this is test")
										.build();
		
		mockMvc.perform(post("/rest/edit")
				.with(csrf()));
		//@formatter:on				
		
	}
	
	@Test
	void TestReplyInsert() throws Exception{

		RequestBuilder create_reply = post("/rest/edit")
												 .with(csrf());

		mockMvc.perform(create_reply)
				.andDo(print());
	}
	
	@Test
	void TestReplyUpdate() throws Exception{

		RequestBuilder update_reply = put("/rest/edit")
												 .with(csrf());

		mockMvc.perform(update_reply)
				.andDo(print());

	}
	
	@Test
	void TestReplyDelete() throws Exception{

		RequestBuilder delete_reply = delete("/rest/edit")
												 .with(csrf());

		mockMvc.perform(delete_reply)
				.andDo(print());

	}
}
