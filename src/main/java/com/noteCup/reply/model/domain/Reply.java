package com.noteCup.reply.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.util.Assert;

import com.noteCup.contents.model.domain.ContentWrapper;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/** @formatter:off
 * --------------------------------------
 * <Description>
 * Entity임... 테이블 생성.
 * --------------------------------------
 * @Project	: noteCup
 * @Package	: com.noteCup.reply.model.domain
 * @File	: Reply.java
 * --------------------------------------
 * @author	: daniel
 * @created	: 2021. 4. 29.
 * @version	: 1.0
 * @formatter:on
 */
@Getter
@Setter
@NoArgsConstructor 
@Entity
@ToString
@Table(name = "tbl_reply")
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rid")
	private long rid;
	
	/*@Column(name = "cid")
	private long cid;*/
	
	@Column(name = "mid")
	private long mid;
	
	@Column(name="rtext")
	@NotEmpty
	@NotNull
	@Size(max=255)
	private String rtext;
	
	@ManyToOne //is it okay?
	@ToString.Exclude
	@Cascade(CascadeType.REMOVE)
	@PrimaryKeyJoinColumn(name = "cid", referencedColumnName = "cid") 
	private ContentWrapper contentWrapper; // cid
	
	@Builder
	public Reply(ContentWrapper contentWrapper, long rid , String rtext, long mid) {
		Assert.hasText(contentWrapper.getCid()+"", "cid must not be empty");
		Assert.hasText(rtext, "text must not be empty");
		this.contentWrapper = contentWrapper;
		this.rid = rid; 
		this.mid = mid;
		this.rtext = rtext;
	}
	
	
}
