package com.noteCup.member.model.vo;

import java.util.List;

import com.noteCup.contents.model.domain.ContentWrapper;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberView {
	
	private long mid;
	
	private String nickname;
	
	private String introduction;
	
	private List<ContentWrapper> writings;
	
}