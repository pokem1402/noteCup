package com.noteCup.member.exception;

/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * Exception for that there is the user already when Registration
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.member.exception
 * @File		: UserAlreadyExistException.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 27.
 * @type		: UserAlreadyExistException
 * @version		: 
 * @formatter:on
 */
public class UserAlreadyExistException extends Exception {

	public UserAlreadyExistException(String errorMsg) {
		super(errorMsg);
	}
}