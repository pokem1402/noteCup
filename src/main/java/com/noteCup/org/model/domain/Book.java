package com.noteCup.org.model.domain;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

import com.noteCup.member.model.domain.MemberInfo;
import com.noteCup.theme.model.domain.Theme;

import lombok.Getter;
import lombok.Setter;

/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.org.model.domain
 * @File		: Book.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 29.
 * @type		: Book
 * @version		: 
 * @formatter:on
 */
@Entity
@Getter
@Table(name = "tbl_book")
public class Book {

	@Id
	@Column(name = "bid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Setter
	@Size(max = 120, min = 1)
	@Column(name = "bname")
	@Basic(optional = false)
	private String name;

	@ManyToMany
	// @formatter:off
	@JoinTable(name = "books_themes",
			   joinColumns = @JoinColumn(name = "book_id",
			   							 referencedColumnName = "bid"),
			   inverseJoinColumns = @JoinColumn(name = "theme_id",
			   									referencedColumnName = "tid"))
	private List<Theme> themes;
	// @formatter:on
	
	@Setter
	@Column(name="bPublicity")
	@Basic(optional = false)
	@ColumnDefault("1")
	private boolean publicity;

	@Setter
	@Basic(optional = false)
	@ManyToOne(targetEntity = MemberInfo.class,
			fetch=FetchType.LAZY)
	@JoinColumn(name = "mid", updatable = false)
	private MemberInfo member;
	
}
