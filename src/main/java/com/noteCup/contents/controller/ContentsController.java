package com.noteCup.contents.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.noteCup.contents.model.domain.ContentWrapper;
import com.noteCup.contents.model.dto.ContentInput;
import com.noteCup.contents.service.ContentsMM;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping(value = "{mURL}")
@Controller
public class ContentsController {
	ModelAndView mav;

	@Autowired
	ContentsMM cm;

	@GetMapping("/content/edit/write")
	public String createFrm(@PathVariable String mURL, WebRequest request, Model model, HttpSession ss) {
		ContentInput contentFrm = new ContentInput();
		model.addAttribute("content", contentFrm);
//		model.addAttribute("action", "/")
		return "writeContent";
	}

	@PostMapping("/content/edit/write")
	public String createPost(@PathVariable String mURL, @ModelAttribute("content") ContentInput content,
			HttpServletRequest request, Errors errors, Principal principal, Authentication auth) {

		System.out.println(principal.getName());
		System.out.println(mURL);

		cm.create(content, principal.getName());

		return "index";
	}

	@GetMapping("/{cid}")
	public String contentFrm(@PathVariable String cid, WebRequest request, Model model, HttpSession ss) {
		Map<Object, Object> hm = new HashMap<Object, Object>();
		hm.put("cid", cid);
		hm.put("ctype", request.getParameter("ctype"));
		model.addAttribute("contentHtml", cm.read(hm));
		return "contentFrm";
	}

	@PostMapping("/content/edit/delete")
	public String delete(WebRequest request, Model model, HttpSession ss) {
		cm.delete(request.getParameter("cid"));
		return "redirect:/";
	}

	@GetMapping("/list")
	public String contentList(Model model, @RequestParam(required = false) String mid) {

		System.out.println(mid);

		List<ContentWrapper> contentList = cm.getContentsList(Long.parseLong(mid));

		log.warn(contentList.toString());

		model.addAttribute("contentlist", contentList.toString());

		return "contentList";
	}

}
