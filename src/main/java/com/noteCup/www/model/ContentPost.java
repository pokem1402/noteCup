package com.noteCup.www.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.util.Assert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "tbl_content")
public class ContentPost implements Serializable {

	private static final long serialVersionUID = 494879747035498206L;

	@Id
	@Column(name = "cid")
	private long cid;

	@Column(name = "ctitle")
	@NotEmpty
	@NotNull
	private String ctitle;

	@Column(name = "ctext")
	@NotEmpty
	@NotNull
	private String ctext;

	@OneToOne
	@Cascade(CascadeType.ALL)
	@PrimaryKeyJoinColumn(name = "cid", referencedColumnName = "cid") // tbl_contents_cid?...or just cid?
	private ContentWrapperInput contentWrapper; // cid

	@Builder
	public ContentPost(ContentWrapperInput contentWrapper, String ctitle, String ctext) {
		Assert.hasText(contentWrapper.getCid()+"", "cid must not be empty");
		Assert.hasText(ctext, "text must not be empty");
		Assert.hasText(ctitle, "title must not be empty");
		this.contentWrapper = contentWrapper;
		this.cid = contentWrapper.getCid();
		this.ctitle = ctitle;
		this.ctext = ctext;
	}
}
