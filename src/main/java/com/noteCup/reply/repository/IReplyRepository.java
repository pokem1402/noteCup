package com.noteCup.reply.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noteCup.reply.model.dto.ReplyInput;
import com.noteCup.reply.model.vo.ReplyVO;

/** @formatter:off
 * --------------------------------------
 * <Description>
 * 댓글 리포지토리임.
 * --------------------------------------
 * @Project	: noteCup
 * @Package	: com.noteCup.reply.repository
 * @File	: IReplyRepository.java
 * --------------------------------------
 * @author	: daniel
 * @created	: 2021. 4. 29.
 * @version	: 1.0
 * @formatter:on
 */
@Repository
public interface IReplyRepository extends JpaRepository<ReplyInput,  Long>{

	List<ReplyVO> findByCid(long cid);

}
