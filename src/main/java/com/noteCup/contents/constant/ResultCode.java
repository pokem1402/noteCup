package com.noteCup.contents.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ResultCode {

	// @formatter:off
	EXIST(1, true, "exist"),
	NONEXIST(0, false, "nonexist"),
	DUPLICATE(1, true, "auth.message.duplicate.exist"),
	NOTDUPLICATE(0, false, "auth.message.duplicate.nonexist"),
	UNDEFINED(-1, false, "undefined");
	// @formatter:on

	private int returnValue;
	private boolean boolValue;
	private String msg;

	public static ResultCode getDupByInt(int value) {

		switch (value) {

		case 1:
			return DUPLICATE;
		case 0:
			return NOTDUPLICATE;
		default:
			return UNDEFINED;
		}
	}
	public static ResultCode getDupByBool(boolean value) {
		if(value) return DUPLICATE;
		else return NOTDUPLICATE;
	}
	public String getMessage() {
		return this.msg;
	}
	public boolean getBoolValue() {
		return this.boolValue;
	}
}
