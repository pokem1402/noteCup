package com.noteCup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.noteCup.model.Board;
import com.noteCup.repository.IBoardRepository;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	IBoardRepository boardRepository;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Board> boards = boardRepository.findAll();
		model.addAttribute("boards", boards);
		return "board/list";
	}
	
}
