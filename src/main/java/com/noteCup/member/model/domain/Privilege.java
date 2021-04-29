package com.noteCup.member.model.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


/** @formatter:off
 * ------------------------------------------------------
 * Entity for privilege
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.member.model.domain
 * @File		: Privilege.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 27.
 * @type		: Privilege
 * @version		: 1.
 * @formatter:on
 */
@Setter
@Getter
@Entity
public class Privilege {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Size(max = 40)
	private String name;
	
	@ManyToMany(mappedBy = "privileges")
	private Collection<Role> roles;
}
