package com.noteCup.www.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.type.BigDecimalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
@Entity
@ToString
@Table(name = "tbl_member")
public class MemberInput {
	
	@NotEmpty
	@NotNull
	@Id
	private String mid;
	
	@Column(name="mpwd")
	@NotEmpty
	@NotNull
	private String mpwd;
	
	@Column(name="memail")
	@NotEmpty
	@NotNull
	private String memail;
	
	@Column(name="mnickname")
	@NotEmpty
	@NotNull
	private String mnickname;

	@Column(name="mintro")
	private String mintro;
//
//	@Column(name="mpictureid")
//	private BigDecimalType mpictureid;
//	
	

	// standard getters and setters
}
