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

import static com.noteCup.util.Util.getPropertiesValue;

@Component
public class RegistrationListner implements ApplicationListener<OnRegistrationCompleteEvent> {

	
	@Autowired
	private MemberMM memberService;
	
	@Autowired
	private MessageSource messages;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		this.confirmRegistration(event);
	}
	
	private void confirmRegistration(OnRegistrationCompleteEvent event) {
		MemberInfo user = event.getUser();
		String token = UUID.randomUUID().toString();
		memberService.createVerificationToken(user, token);
		
		String recipientAddress = user.getEmail();
		String subject;
		switch(user.getLocale().getLanguage()) {
			case "ko":
				subject = getPropertiesValue("messages//messages_ko_KR.properties", "registration.confirm.subject");
			default:
				subject = getPropertiesValue("messages//messages_en.properties", "registration.confirm.subject");
		}
		String confirmUrl = event.getAppUrl() + "/registrationConfirm.html?token=" + token;
		String message = messages.getMessage("message.regSucc", null, event.getLocale());
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject(subject);
		email.setText(message + "\r\n" + "http://localhost" + confirmUrl);
		mailSender.send(email);
		
	}

}
