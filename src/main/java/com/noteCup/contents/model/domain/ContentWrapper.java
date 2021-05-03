package com.noteCup.contents.model.domain;

import java.sql.Date;

import javax.persistence.Basic;
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
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.noteCup.member.model.domain.MemberInfo;
import com.noteCup.org.model.domain.Book;
import com.noteCup.org.model.domain.Page;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Entity
@Builder
@Table(name = "tbl_contentWapper")
public class ContentWrapper {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cid")
	private long cid;

	@Setter
	@OneToOne(mappedBy = "contentWrapper")
	private ContentPost post;

	@Setter
	@OneToOne(mappedBy = "contentWrapper")
	private ContentScript script;

	@Setter
	@OneToOne(mappedBy = "contentWrapper")
	private ContentMemo memo;
	
	@Setter
	@OneToOne(mappedBy = "contentWrapper")
	private ContentLink link;
	
	@Setter
	@OneToOne(mappedBy = "contentWrapper")
	private ContentPhotos photos;
	
	@Setter
	@Basic(optional = false)
	@ManyToOne(targetEntity=MemberInfo.class, fetch=FetchType.LAZY)
	@JoinColumn(name="mid", updatable = false, nullable = false)
	private MemberInfo memberInfo;

	@Setter
	@Basic(optional = false)
	@Size(max = 10)
	@Column(name = "contentType", updatable = false)
	private String contentType;	
	
	@Basic(optional = false)
	@Column(name = "cView", insertable = false)
	@ColumnDefault("0")
	private long view;
	
	@Basic(optional = false)
	@Column(name = "cLike", insertable = false)
	@ColumnDefault("0")
	private long like;
	
	@Basic(optional = false)
	@Column(name = "cDateCreated", updatable = false)
	@CreationTimestamp
	private Date created;
	
	@Basic(optional = false)
	@Column(name = "cDateUpdated")
	@UpdateTimestamp
	private Date updated;
	
	@Basic(optional = false)
	@Column(name = "isPublish")
	@ColumnDefault("0")
	private boolean publish;
	
	@Basic(optional = false)
	@Column(name = "cPublicity")
	@ColumnDefault("1")
	private boolean publicity;	
	
	@Setter
	@Basic(optional = false)
	@ManyToOne(targetEntity = Book.class,
	fetch = FetchType.LAZY)
	@JoinColumn(name="bid")
	private Book book;
	
	@Setter
	@Basic(optional = false)
	@ManyToOne(targetEntity = Page.class,
	fetch = FetchType.LAZY)
	@JoinColumn(name="pageId")
	private Page page;
	
	
	
	
}
