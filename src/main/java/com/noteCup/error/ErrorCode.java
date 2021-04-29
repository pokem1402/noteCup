package com.noteCup.error;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * ErrorCode for Validation
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.error
 * @File		: ErrorCode.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 27.
 * @type		: ErrorCode
 * @version		: 
 * @formatter:on
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {

	// @Valid
	NOT_NULL("NotNULL","ERROR_CODE_0001", "필수값이 누락되었습니다."),
	NOT_EMPTY("NotEmpty","ERROR_CODE_0002", "필수값이 누락되었습니다."),
	INVALID("Valid", "ERROR_CODE_0003", "잘못된 양식입니다."),
	UNMATCHED("Matches", "ERROR_CODE_0004", "두 값이 일치하지 않습니다."),
	UNDEFINED("UNDEFINED", "ERROR_CODE_0000", "정의되지 않은 오류");
	
	
	private String annotation;
	private String code;
	private String description;
	
	private boolean hasAnnotation(String anno) {
		return annotation.indexOf(anno) != -1;
	}
	
	public static ErrorCode findbyAnnotation(String annotation) {
		return Arrays.stream(ErrorCode.values())
				.filter(e ->e.hasAnnotation(annotation))
				.findAny()
				.orElse(UNDEFINED);
	}
	
}
