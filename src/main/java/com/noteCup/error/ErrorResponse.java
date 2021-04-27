package com.noteCup.error;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.error
 * @File		: ErrorResponse.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 27.
 * @type		: ErrorResponse
 * @version		: 
 * @formatter:on
 */
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorResponse {

	@NotNull
	private String code;
	@NotNull
	private String message;		// 에러 메시지
	private String description;	// 에러 설명
	
}