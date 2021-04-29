package com.noteCup.contents.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping(value = "/content")
@Controller
public class ContentsController {
	ModelAndView mav;

	@Autowired
	ContentsMM cm;

	@GetMapping("/edit/write")
	public String createFrm(WebRequest request, Model model, HttpSession ss) {
		// if(ss.getAttribute("id")==null) return "redirect:/";
		// ContentsInput ci = new ContentsInput();
		ContentInput contentFrm = new ContentInput();
		// model.addAttribute("content", ci);
		model.addAttribute("content", contentFrm);
		return "writeContent";
	}

	@PostMapping("/edit/write")
	public String createPost(@ModelAttribute("content") ContentInput content, HttpServletRequest request, Errors errors,
			Principal principal) {

		System.out.println(principal.getName());

		cm.create(content, principal.getName());

		return "index";
	}

	@GetMapping("/view/item")
	public String contentFrm(WebRequest request, Model model, HttpSession ss) {
		Map<Object, Object> hm = new HashMap<Object, Object>();
		hm.put("cid", request.getParameter("cid"));
		hm.put("ctype", request.getParameter("ctype"));
		model.addAttribute("contentHtml", cm.read(hm));
		return "contentFrm";
	}

	@PostMapping("/edit/delete")
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
