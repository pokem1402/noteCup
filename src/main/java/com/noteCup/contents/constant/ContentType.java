package com.noteCup.contents.constant;

import java.util.Arrays;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ContentType {

	POST("POST"),
	SCRIPT("SCRIPT");
	
	private String type;
	
	private String getType() {
		return type;
	}
	
	public static ContentType findType(String type) {
		return Arrays.stream(ContentType.values())
				.filter(e->e.getType().equals(type))
				.findAny()
				.orElseThrow();
	}
	
	
	
}
