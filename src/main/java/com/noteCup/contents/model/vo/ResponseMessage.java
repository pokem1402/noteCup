package com.noteCup.contents.model.vo;

import lombok.Builder;
import lombok.Getter;

/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * ResponseEntity body로 사용될 메세지 오브젝트
 * message : 메세지
 * status  : 부수적으로 필요한 boolean value
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.contents.model.vo
 * @File		: ResponseMessage.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 5. 3.
 * @type		: ResponseMessage
 * @version		: 
 * @formatter:on
 */
@Getter
@Builder
public class ResponseMessage {

	private String message;
	private boolean status;
	
}
