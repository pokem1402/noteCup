package com.noteCup.contents.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.util.Assert;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.contents.model.domain
 * @File		: ContentScript.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 29.
 * @type		: ContentScript
 * @version		: 
 * @formatter:on
 */
@Getter
@Setter
@NoArgsConstructor 
@Entity
@ToString
@Table(name = "tbl_content")
public class ContentScript implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3261856128882160625L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cid")
	private long cid;
	
	@Column(name="ctext")
	@NotEmpty
	@NotNull
	@Size(max=255)
	private String ctext;
	
	@OneToOne
	@ToString.Exclude
	@Cascade(CascadeType.REMOVE)
	@PrimaryKeyJoinColumn(name = "cid", referencedColumnName = "cid") // tbl_contents_cid?...or just cid?
	private ContentWrapper contentWrapper; // cid
	
	@Builder
	public ContentScript(ContentWrapper contentWrapper, String ctext) {
		Assert.hasText(contentWrapper.getCid()+"", "cid must not be empty");
		Assert.hasText(ctext, "text must not be empty");
		this.contentWrapper = contentWrapper;
		this.ctext = ctext;
	}
	

}
