package com.noteCup.reply.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.noteCup.reply.model.dto.ReplyInput;
import com.noteCup.reply.model.vo.ReplyVO;
import com.noteCup.reply.repository.IReplyRepository;

/** @formatter:off
 * --------------------------------------
 * <Description>
 * 댓글 서비스클래스..
 * --------------------------------------
 * @Project	: noteCup
 * @Package	: com.noteCup.reply.service
 * @File	: ReplyMM.java
 * --------------------------------------
 * @author	: daniel
 * @created	: 2021. 4. 29.
 * @version	: 1.0
 * @formatter:on
 */
@Service
public class ReplyMM {
	
	@Autowired
	IReplyRepository replyRepository;
	
	@Autowired
	MessageSource messages;

	public List <ReplyVO> getReplyByCid(long cid) {
		/*ReplyInput replyInput = ReplyInput.builder().cid(cid).build();*/
		
		List <ReplyVO> replyList = replyRepository.findByCid(cid); //vo에 담을게 rid, rtext, mid(작성자)임.
		return replyList; 
	}

	public void create(ReplyInput replyInput) {
		ReplyInput result = replyRepository.save(replyInput);
		if(result==null) {
			messages.getMessage("reply.message.insert.fail", null, Locale.KOREAN);
		}
	}

	public void update(ReplyInput replyInput) {
		ReplyInput result = replyRepository.save(replyInput); //이렇게 하면 되나...?
		if(result==null) {
			messages.getMessage("reply.message.update.fail", null, Locale.KOREAN);
		}
	}

	public void delete(ReplyInput replyInput) {
		replyRepository.deleteById(replyInput.getRid());//댓글 primary 번호로 지움?..
		
	}

	

}
