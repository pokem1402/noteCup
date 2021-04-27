package com.noteCup.member.controllerAdvice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.noteCup.error.ErrorCode;
import com.noteCup.error.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * Registration Error Handler
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.member.controllerAdvice
 * @File		: RegistrationAdvice.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 27.
 * @type		: RegistrationAdvice
 * @version		: 1.0
 * @formatter:on
 */
@Slf4j
@ControllerAdvice
public class RegistrationAdvice{

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErrorResponse>> methodValidException(MethodArgumentNotValidException e,
			HttpServletRequest request) {

		LOGGER.warn("MethodArguwmentNotValidException 발생, url : {}, trace : {}", request.getRequestURI(),
				e.getStackTrace());

		List<ErrorResponse> errorResponses = makeErrorResponses(e.getBindingResult());
		return new ResponseEntity<List<ErrorResponse>>(errorResponses, HttpStatus.BAD_REQUEST);
	}

	private List<ErrorResponse> makeErrorResponses(BindingResult bindingResult) {

		List<ErrorResponse> errorResponse = new ArrayList<ErrorResponse>();
		
		if (bindingResult.hasErrors()) {

			List<ObjectError> list = bindingResult.getAllErrors();
			list.forEach(e -> {

				String bindingErrorCode = e.getCode();
				String message = e.getDefaultMessage();
				ErrorCode errorCode = ErrorCode.findbyAnnotation(bindingErrorCode);
				String code = errorCode.getCode();
				String description = errorCode.getDescription();

				errorResponse.add(new ErrorResponse(code, message, description));

			});
		}
		return errorResponse;
	}

}
