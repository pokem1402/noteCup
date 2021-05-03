package com.noteCup.member.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.noteCup.member.model.domain.MemberInfo;
import com.noteCup.member.validation.PasswordMatches;
import com.noteCup.member.validation.ValidEmail;

import lombok.AllArgsConstructor;
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
 * @Package		: com.noteCup.member.model.dto
 * @File		: MemberInput.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 29.
 * @type		: MemberInput
 * @version		: 
 * @formatter:on
 */
@PasswordMatches
@Setter
@Getter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MemberInput {
	@NotNull(message = "NULL : 비밀번호를 입력해주세요.")
	@NotEmpty(message = "EMPTY : 비밀번호를 입력해주세요.")
	private String password;
	
	@NotNull(message = "NULL : 비밀번호 확인값을 입력해주세요.")
	@NotEmpty(message = "EMPTY : 비밀번호 확인값을 입력해주세요.")
	private String matchingPassword;

	@NotNull(message = "NULL : 닉네임을 입력해주세요.")
	@NotEmpty(message = "EMPTY : 닉네임을 입력해주세요.")
	private String nickname;

	@NotNull(message = "NULL : URL을 입력해주세요.")
	@NotEmpty(message = "EMPTY : URL을 입력해주세요.")
	private String url;
	
	@ValidEmail(message = "올바르지 않은 이메일입니다.")
	@NotNull(message = "NULL : 이메일을 입력해주세요.")
	@NotEmpty(message = "EMPTY : 이메일을 입력해주세요.")
	private String email;

	private String introduction;

	
	public MemberInfo toEntity() {
		return MemberInfo.builder()
				.pwd(new BCryptPasswordEncoder().encode(password))
				.nickname(nickname)
				.email(email)
				.introduction(introduction)
				.auth("GUEST")
				.url(url)
				.build();
	}
}