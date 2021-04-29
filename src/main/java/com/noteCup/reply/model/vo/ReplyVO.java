package com.noteCup.reply.model.vo;

import lombok.Getter;
import lombok.Setter;

/** @formatter:off
 * --------------------------------------
 * <Description>
 * 보여줄 내용을 담는 곳.
 * --------------------------------------
 * @Project	: noteCup
 * @Package	: com.noteCup.reply.model.vo
 * @File	: ReplyVO.java
 * --------------------------------------
 * @author	: daniel
 * @created	: 2021. 4. 29.
 * @version	: 1.0
 * @formatter:on
 */
@Getter
@Setter
public class ReplyVO {
	private long rid;
	private long mid;
	private String rtext;
}
