package com.noteCup.member.registration;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.noteCup.member.model.domain.MemberInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    public OnRegistrationCompleteEvent(MemberInfo user, Locale locale, String appUrl) {

    	super(user);
		
    	this.locale = locale;
		this.appUrl = appUrl;
	
    }
	
    private String appUrl;
    private Locale locale;
    private MemberInfo user;

}
