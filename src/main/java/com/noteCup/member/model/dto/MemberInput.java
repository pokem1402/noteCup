package com.noteCup.member.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.noteCup.member.model.domain.MemberInfo;
import com.noteCup.member.validation.PasswordMatches;
import com.noteCup.member.validation.ValidEmail;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@PasswordMatches
@Setter
@Getter
@ToString
@NoArgsConstructor
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

	@ValidEmail(message = "올바르지 않은 이메일입니다.")
	@NotNull(message = "NULL : 이메일을 입력해주세요.")
	@NotEmpty(message = "EMPTY : 이메일을 입력해주세요.")
	private String email;

	private String introduction;
	
	@Builder
	//@formatter:off
	public MemberInput(String password,
					   String matchingPassword,
					   String email,
					   String nickname,
					   String introduction) {
	//@formatter:on
		this.password = password;
		this.matchingPassword = matchingPassword;
		this.email = email;
		this.nickname = nickname;
		this.introduction = introduction;
	}
	
	public MemberInfo toEntity() {
		return MemberInfo.builder()
				.pwd(new BCryptPasswordEncoder().encode(password))
				.nickname(nickname)
				.email(email)
				.introduction(introduction)
				.auth("USER")
				.build();
	}
}