package com.noteCup.member.model.vo;

import java.util.List;

import com.noteCup.contents.model.domain.ContentWrapper;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.member.model.vo
 * @File		: MemberView.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 29.
 * @type		: MemberView
 * @version		: 
 * @formatter:on
 */
@Getter
@Setter
@Builder
public class MemberView {
	
	private long mid;
	
	private String nickname;
	
	private String introduction;
	
	private List<ContentWrapper> writings;
	
}