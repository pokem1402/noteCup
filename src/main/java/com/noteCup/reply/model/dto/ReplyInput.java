package com.noteCup.reply.model.dto;



import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/** @formatter:off
 * --------------------------------------
 * <Description>
 * 데이터 insert 혹은 update? 할때..
 * --------------------------------------
 * @Project	: noteCup
 * @Package	: com.noteCup.reply.model.dto
 * @File	: ReplyInput.java
 * --------------------------------------
 * @author	: daniel
 * @created	: 2021. 4. 29.
 * @version	: 1.0
 * @formatter:on
 */
@Getter
@Setter
public class ReplyInput {
	
	private long rid;//댓글 번호...?
	private long cid;//글 번호
	private long mid;//댓글 작성자 아이디
	private String rtext;//댓글 내용
	
	
	@Builder
	public ReplyInput(long rid, long cid, long mid, String rtext) {
		this.cid = cid;
		this.rid = rid; 
		this.mid = mid;
		this.rtext = rtext;
	}
	
}
