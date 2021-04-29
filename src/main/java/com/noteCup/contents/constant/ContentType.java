package com.noteCup.contents.constant;

import java.util.Arrays;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.noteCup.contents.exception.InvalidContentTypeException;
import com.noteCup.contents.model.domain.ContentPost;
import com.noteCup.contents.model.domain.ContentWrapper;
import com.noteCup.contents.model.dto.ContentInput;
import com.noteCup.contents.repository.IContentWrapperRepository;
import com.noteCup.util.Util;

import javassist.expr.Instanceof;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.contents.constant
 * @File		: ContentType.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 29.
 * @type		: ContentType
 * @version		: 1.0
 * @formatter:on
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ContentType {

	// @formatter:off
	POST("POST"),
	SCRIPT("SCRIPT"),
	PHOTOLOG("PHOTOLOG"),
	HYPERLINK("HYPERLINK"),
	MEMO("MEMO");
	// @formatter:on

	private String type;

	public ContentType getContentType() {
		return this;
	}

	public static ContentType findType(String type) {
		return Arrays.stream(ContentType.values()).filter(e -> e.getType().equals(type)).findAny().orElseThrow();
	}


}
