package org.mu.cloudservice.security;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionDestroyedEvent;

public class SessionTimeOutListener implements ApplicationListener<SessionDestroyedEvent> {

	@Autowired
	private ServletContext servletContext;

	@Override
	public void onApplicationEvent(SessionDestroyedEvent event) {}

}