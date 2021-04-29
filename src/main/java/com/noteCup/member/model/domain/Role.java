package com.noteCup.member.model.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * Entity for Role
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.member.model.domain
 * @File		: Role.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 27.
 * @type		: Role
 * @version		: 
 * @formatter:on
 */
@Table(name = "tbl_role")
@Entity
@Setter
@Getter
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@ManyToMany
	@JoinTable(
			// 조인 테이블명
			name = "roles_privileges",
			// Foreign key references this entity (현재 엔티티에서 참고하는 키)
			joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
			// Foreign key references opposite entity (다른 엔티티에서 참고하는 키)
			inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
	private Collection<Privilege> privileges;

}