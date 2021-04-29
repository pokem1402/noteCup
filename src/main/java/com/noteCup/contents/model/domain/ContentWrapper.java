package com.noteCup.contents.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;

import com.noteCup.member.model.domain.MemberInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_contentWapper")
//@DynamicInsert
public class ContentWrapper {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cid")
	private long cid;
	
	@OneToOne(mappedBy = "contentWrapper")
	private ContentPost post;
	
	@OneToOne(mappedBy = "contentWrapper")
	private ContentScript script;
	
	@NotNull
	@ManyToOne(targetEntity=MemberInfo.class, fetch=FetchType.LAZY)
	@JoinColumn(name="mid")
	private MemberInfo memberInfo;
	
	
	
}
