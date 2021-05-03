package com.noteCup.reply.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.noteCup.contents.model.domain.ContentWrapper;
import com.noteCup.member.model.domain.MemberInfo;
import com.noteCup.member.repository.IMemberRepository;
import com.noteCup.reply.model.domain.Reply;
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
 * @version	: 1.0.1
 * @formatter:on
 */
@Service
public class ReplyMM {
	
	@Autowired
	IReplyRepository replyRepository;
	
	@Autowired
	IMemberRepository memberRepository;
	
	@Autowired
	MessageSource messages;

	public List <Reply> getReplyByCid(long cid) {
		/*ReplyInput replyInput = ReplyInput.builder().cid(cid).build();*/
		ContentWrapper contentWrapper = ContentWrapper.builder()
										.cid(cid)
										.build();
		List <Reply> replyList = replyRepository.findByContentWrapper(contentWrapper); //vo에 담을게 rid, rtext, mid(작성자)임.
		
		return replyList; 
	}

	public void create(ReplyInput replyInput, String email) {
		
		Optional<MemberInfo> member = memberRepository.findByEmail(email);// 이 부분이 중요함...
		
		ContentWrapper contentWrapper = ContentWrapper.builder()
				.cid(replyInput.getCid())
				.build();
		
		
		Reply reply = Reply.builder().mid( member.get().getMid() ) // 이 부분이 중요함...
						.rtext(replyInput.getRtext())
						.contentWrapper(contentWrapper).build();
		
		Reply result = replyRepository.save(reply);
		if(result==null) {
			messages.getMessage("reply.message.insert.fail", null, Locale.KOREAN);
		}
	}

	public void update(ReplyInput replyInput, String email) {
		
		Optional<MemberInfo> member = memberRepository.findByEmail(email);
		
		ContentWrapper contentWrapper = ContentWrapper.builder()
				.cid(replyInput.getCid())
				.build();
		
		Reply reply = Reply.builder().mid( member.get().getMid() )
						.rtext(replyInput.getRtext())
						.contentWrapper(contentWrapper).build();
		
		Reply result = replyRepository.save(reply); //이렇게 하면 되나...?
		if(result==null) {
			messages.getMessage("reply.message.update.fail", null, Locale.KOREAN);
		}
	}

	public void delete(ReplyInput replyInput, String email) {
		
		Optional<Reply> reply = replyRepository.findById(replyInput.getRid());
		Optional<MemberInfo> member = memberRepository.findByEmail(email);
		Long loginedId = member.get().getMid();
		Long replyMid = reply.get().getMid();
		
		if(loginedId == replyMid) {
			replyRepository.deleteById(replyInput.getRid());//댓글 primary 번호로 지움?..
		}else {
			messages.getMessage("reply.message.delete.fail", null, Locale.KOREAN);
		}
	}

	

}
