package com.noteCup.theme.model.domain;

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
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.noteCup.member.model.domain.MemberInfo;

import lombok.Getter;
import lombok.Setter;

/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.theme.model.domain
 * @File		: Theme.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 29.
 * @type		: Theme
 * @version		: 
 * @formatter:on
 */
@Entity
@Getter
@Table(name = "tbl_theme")
public class Theme {

	@Id @Column(name="tid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Setter
	@Basic(optional = false)
	@Column(name = "tName")
	private String name;
	
	@Setter
	@Column(name = "tDesc")
	private String description;
	
	@Setter
	@Basic(optional = false)
	@ManyToOne(targetEntity = MemberInfo.class,
			fetch=FetchType.LAZY)
	@JoinColumn(name = "mid")
	private MemberInfo member;
	
	@Basic(optional = false)
	@Column(name = "tLike", insertable = false)
	@ColumnDefault("0")
	private long like;
	
	@Basic(optional = false)
	@Column(name = "tCreated", insertable = false, updatable = false)
	@CreationTimestamp
	private Date created;
	
}
