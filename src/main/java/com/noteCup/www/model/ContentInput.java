package com.noteCup.www.model;

import lombok.Setter;
import lombok.ToString;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ContentInput {

	
	private String title;
	private String text;
	private String contentType;
	
}
