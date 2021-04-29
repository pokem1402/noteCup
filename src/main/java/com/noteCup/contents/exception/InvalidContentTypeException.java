package com.noteCup.contents.exception;

/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.contents.exception
 * @File		: InvalidContentTypeException.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 29.
 * @type		: InvalidContentTypeException
 * @version		: 
 * @formatter:on
 */
public class InvalidContentTypeException extends Exception {

	public InvalidContentTypeException(String errorMsg) {
		super(errorMsg);
	}
}
