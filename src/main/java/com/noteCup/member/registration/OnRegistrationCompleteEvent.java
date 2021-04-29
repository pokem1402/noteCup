package com.noteCup.member.registration;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import com.noteCup.member.model.domain.MemberInfo;

import lombok.Getter;
import lombok.Setter;

/** @formatter:off
 * ------------------------------------------------------
 * <Description>
 * ------------------------------------------------------
 * @Project		: noteCup
 * @Package		: com.noteCup.member.registration
 * @File		: OnRegistrationCompleteEvent.java
 * ------------------------------------------------------
 * @author		: 김원빈
 * @created		: 2021. 4. 29.
 * @type		: OnRegistrationCompleteEvent
 * @version		: 
 * @formatter:on
 */
@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    public OnRegistrationCompleteEvent(MemberInfo user, Locale locale, String appUrl) {

    	super(user);
		this.user = user;
    	this.locale = locale;
		this.appUrl = appUrl;
	
    }
	
    private String appUrl;
    private Locale locale;
    private MemberInfo user;

}
