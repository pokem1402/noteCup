package com.noteCup.www.model;

import javax.persistence.CascadeType;
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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_contentWapper")
public class ContentWrapperInput {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cid")
	private long cid;
		
	
	@OneToOne(mappedBy = "contentWrapper")
	private ContentPost post;
	
	@OneToOne(mappedBy = "contentWrapper")
	private ContentScript script;
	
	@NotNull
	@ManyToOne(targetEntity=MemberInput.class, fetch=FetchType.LAZY)
	@JoinColumn(name="mid")
	private MemberInput memberInput;
	
	
	
}
