package org.mu.community.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class SAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, org.springframework.security.core.Authentication auth)
			throws IOException, ServletException {
		response.sendRedirect(request.getContextPath() + "/source/index.html");
		
	}

}