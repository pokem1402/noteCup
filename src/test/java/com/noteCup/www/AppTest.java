package com.noteCup.www;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Properties;


import org.junit.jupiter.api.Test;

import com.noteCup.member.model.domain.MemberInfo;
import com.noteCup.member.model.dto.MemberInput;


public class AppTest {

	@Test
	public void MemberInput_test() {
		MemberInfo member1 = MemberInfo.builder().build();
		MemberInput member2 = new MemberInput();
		member2.setPassword("아");

		System.out.println(member1.toString());
		System.out.println(member2.toEntity().toString());
	}

	// https://www.baeldung.com/java-properties


	@Test
	public void getPropertiesValue() {
		
		Properties prop = new Properties();
		InputStream in = getClass().getClassLoader().getResourceAsStream("messages\\messages_ko_KR.properties");
		try {
			prop.load(new InputStreamReader(in, "UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("아");
		System.out.println(prop.getProperty("page.home.title"));
	}
	
	@Test
	public void localeTest() {
		
		System.out.println(Locale.KOREAN.getDisplayLanguage());
		System.out.println(Locale.KOREAN.getLanguage());
		System.out.println(Locale.ENGLISH.getDisplayLanguage());
		System.out.println(Locale.ENGLISH.getLanguage());
		
	}
}
