package com.noteCup.reply.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noteCup.reply.model.domain.Reply;
import com.noteCup.reply.model.dto.ReplyInput;
import com.noteCup.reply.model.vo.ReplyVO;
import com.noteCup.reply.service.ReplyMM;

import lombok.extern.log4j.Log4j2;

/** @formatter:off
 * --------------------------------------
 * <Description>
 * 댓글은 대부분 레스트컨트롤러만 활용해야할듯?...
 * --------------------------------------
 * @Project	: noteCup
 * @Package	: com.noteCup.reply.controller
 * @File	: ReplyRestController.java
 * --------------------------------------
 * @author	: daniel
 * @created	: 2021. 4. 29.
 * @version	: 1.0
 * @formatter:on
 */
//@RequestMapping(value = "/reply")
@Log4j2
@Controller // 추후에 레스트로 바꿔야함 왜냐면 지금 testreply때문에 만들어좀.
public class ReplyRestController {
	
	ObjectMapper objectMapper;
	
	ModelAndView mav;

	@Autowired
	ReplyMM rm;
	
	@GetMapping("/testreply")
	public String testReply() {
		return "testReply.html";
	}
	
	/*@GetMapping("/test/reply/view") //pid = 개인 url
	public ModelAndView getReplyBycid2(WebRequest request, Model model, HttpSession ss) {
		List <Reply> replyList = rm.getReplyByCid(Long.parseLong(request.getParameter("cid")));
		objectMapper = new ObjectMapper();
		mav = new ModelAndView();
		String json = null;
		try {
			json = objectMapper.writeValueAsString(replyList);
			mav.addObject("replyJson",json);
			mav.setViewName("testReply");
		} catch (JsonProcessingException e) {
			log.warn("::::::json parsing Error");
			e.printStackTrace();
		}
		return mav;
	}*/
	

	@PostMapping("/{pid}/{cid}/reply/view") //pid = 개인 url cid ="콘텐츠번호"
	public String getReplyBycid(@PathVariable String pid, @PathVariable String cid, WebRequest request, Model model, HttpSession ss) {
		List <Reply> replyList = rm.getReplyByCid(Long.parseLong(cid));
		objectMapper = new ObjectMapper();
		String json = null;
		try {
			json = objectMapper.writeValueAsString(replyList);
		} catch (JsonProcessingException e) {
			log.warn("::::::json parsing Error");
			e.printStackTrace();
		}
		return json;
	}
	
	@PostMapping("/rest/edit")//댓글 삽입하고 나서 .... String "reply/rest/view" + cid 로 넘겨주면 됨?...
	public String insert(@ModelAttribute("reply") ReplyInput replyInput,Principal principal) {
		rm.create(replyInput, principal.getName());
		return "/" + 1 + "/" + replyInput.getCid()+ "/reply/view";
	}
	
	@PatchMapping("/rest/edit")//자원 부분 교체
	public String update(@ModelAttribute("reply") ReplyInput replyInput,Principal principal) {
		rm.update(replyInput, principal.getName());
		return "/" + 1 + "/" + replyInput.getCid()+ "/reply/view";
	}
	
	@DeleteMapping("/rest/edit")
	public String delete(@ModelAttribute("reply") ReplyInput replyInput,Principal principal) {
		rm.delete(replyInput, principal.getName());
		return "/" + 1 + "/" + replyInput.getCid()+ "/reply/view";
	}
	
	
	
}
