package com.noteCup.member.registration;

import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.noteCup.member.model.domain.MemberInfo;
import com.noteCup.member.service.MemberMM;

import lombok.extern.log4j.Log4j2;

import static com.noteCup.util.Util.getPropertiesValue;

/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.member.registration
 * @File		: RegistrationListner.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 29.
 * @type		: RegistrationListner
 * @version		: 
 * @formatter:on
 */
@Log4j2
@Component
public class RegistrationListner implements ApplicationListener<OnRegistrationCompleteEvent> {

	
	@Autowired
	private MemberMM memberService;
	
	@Autowired
	private MessageSource messages;
	
//	@Autowired
//	private JavaMailSender mailSender;
	
	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		this.confirmRegistration(event);
	}
	
	private void confirmRegistration(OnRegistrationCompleteEvent event) {
		MemberInfo user = event.getUser();
		
		log.warn(user.getUsername());
		
		String token = UUID.randomUUID().toString();
		memberService.createVerificationToken(user, token);
		
		String recipientAddress = user.getEmail();
		String subject = messages.getMessage("registration.confirm.subject", null, event.getLocale());

		String confirmUrl = event.getAppUrl() + "/registration/Confirm?token=" + token;
		
		String message = messages.getMessage("auth.message.regSucc", null, event.getLocale());
		
		System.out.println(subject);
		System.out.println(message);
		System.out.println(confirmUrl);
//		SimpleMailMessage email = new SimpleMailMessage();
//		email.setTo(recipientAddress);
//		email.setSubject(subject);
//		email.setText(message + "\r\n" + "http://localhost" + confirmUrl);
//		mailSender.send(email);
		
	}

}
