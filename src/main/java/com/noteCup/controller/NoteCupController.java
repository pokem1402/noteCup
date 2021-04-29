package com.noteCup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoteCupController {

	
	/*
	 * @GetMapping("/") public String home(Model model){
	 * //model.addAttribute("data","어서오세요!"); return "main"; }
	 */
	@GetMapping("/")
    public String main(){
        //model.addAttribute("data","어서오세요!");
        return "main";
    }
	
}
