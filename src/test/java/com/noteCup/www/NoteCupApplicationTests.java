package com.noteCup.www;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.noteCup.member.model.domain.MemberInfo;
import com.noteCup.member.model.dto.MemberInput;

@SpringBootTest
class NoteCupApplicationTests {

	@Test
	public void EntityTest() {
		
		MemberInfo member1 = MemberInfo.builder().build();
		MemberInput member2 = new MemberInput();
		member2.setPassword("a");
		
		
		System.out.println(member1.toString());
		System.out.println(member2.toEntity().toString());
	}
	
}
