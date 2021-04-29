package com.noteCup.contents.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.util.Assert;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Entity
@Table(name = "tbl_content")
public class ContentPhotos {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name = "cid")
	private long cid;
	
	@Column(name = "ctext")
	private String ctext;
	
	@OneToOne
	@ToString.Exclude
	@Cascade(CascadeType.REMOVE)
	@PrimaryKeyJoinColumn(name = "cid", referencedColumnName = "cid") // tbl_contents_cid?...or just cid?
	private ContentWrapper contentWrapper; // cid

	@Builder
	public ContentPhotos(ContentWrapper contentWrapper,
						String ctext){
		
		Assert.hasText(contentWrapper.getCid()+"", "cid must not be empty");
		Assert.hasText(ctext, "text must not be empty");
		this.contentWrapper = contentWrapper;
		this.contentWrapper.setPhotos(this);
		this.ctext = ctext;
		
	}
}
