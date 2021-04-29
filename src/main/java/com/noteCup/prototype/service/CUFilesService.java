package com.noteCup.prototype.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface CUFilesService {

	boolean create(MultipartHttpServletRequest inputReq);
	boolean update(MultipartHttpServletRequest updateReq);
	
}
