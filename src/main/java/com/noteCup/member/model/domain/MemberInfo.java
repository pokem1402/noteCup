package com.noteCup.member.model.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * Entity for member
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.member.model.domain
 * @File		: MemberInfo.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 27.
 * @type		: MemberInfo
 * @version		: 1.
 * @formatter:on
 */
@Entity
@Table(name = "tbl_member")
@Data
@Accessors(chain=true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberInfo implements UserDetails {

	/**
	 * @field long serialVersionUID
	 *
	 * @author 김원빈 
	 */
	private static final long serialVersionUID = -4582951860454296082L;

	@Id
	@Column(name = "mid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mid;

	@Column(name = "mpwd")
	private String pwd;

	@Column(name = "memail", unique = true)
	private String email;

	@Column(name = "mnickname")
	@Size(max = 20)
	private String nickname;

	@Column(name = "auth")
	private String auth;

	@Column(name = "mintro")
	private String introduction;
	
	@Transient // Skip persistence
	private boolean accountNonExpired;

	@Transient
	private boolean accountNonLocked;
	
	@Transient
	private boolean credentialsNonExpired;

	@Transient
	private boolean enabled;
	
	@Builder
	//@formatter:off
	public MemberInfo(Long mid,
			 		  String pwd,
			 		  String email,
					  String nickname,
					  String auth,
					  String introduction,
					  boolean accountNonExpired,
					  boolean accountNonLocked,
					  boolean credentialsNonExpired,
					  boolean enabled) {
	//@formatter:on
		this.mid = mid;
		this.pwd = pwd;
		this.email = email;
		this.nickname = nickname;
		this.auth = auth;
		this.introduction = introduction;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> roles = new HashSet<>();
		for (String role : auth.split(",")) {
			roles.add(new SimpleGrantedAuthority(role));
		}
		return roles;
	}

	
	@Override
	public String getUsername() { // id
		return this.email;
	}

	@Override
	public String getPassword() { // password
		return this.pwd;
	}


}