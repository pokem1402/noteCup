package com.noteCup.www.controller;

import java.util.HashMap;
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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.noteCup.www.model.ContentInput;
import com.noteCup.www.service.ContentsMM;



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
	public String createPost(@ModelAttribute("content") ContentInput content, HttpServletRequest request, Errors errors) {
		cm.create(content);
		return "index";
	}
	
	@GetMapping("/view/item")
	public String contentFrm(WebRequest request, Model model, HttpSession ss) {
		Map<Object,Object> hm = new HashMap<Object,Object>();  
		hm.put( "cid", Integer.parseInt(request.getParameter("cid"))  );
		hm.put( "ctype", request.getParameter("ctype"));
		model.addAttribute("contentHtml", cm.read(hm));
		return "contentFrm";
	}
	
	
	
	@PostMapping("/edit/delete")
	public String delete(WebRequest request, Model model, HttpSession ss) {
		Map<Object,Object> hm = new HashMap<Object,Object>();  
		hm.put( "cid", Integer.parseInt( request.getParameter("cid") )  );
		hm.put("ctype", request.getParameter("ctype"));
		cm.delete(hm);
		return "/";
	}
	
	

}
