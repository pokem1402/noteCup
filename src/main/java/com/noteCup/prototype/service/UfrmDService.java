package com.noteCup.prototype.service;

import org.springframework.web.servlet.ModelAndView;

public interface UfrmDService {

	ModelAndView updateFrm(Long itemId);
	boolean delete(Long itemId);
	
}
