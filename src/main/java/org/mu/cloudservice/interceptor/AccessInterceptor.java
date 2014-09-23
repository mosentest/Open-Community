package org.mu.cloudservice.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mu.cloudservice.repository.NCSALogRepository;
import org.mu.cloudservice.utility.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AccessInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private NCSALogRepository ncsaLogRepository;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		ncsaLogRepository.save(LogUtil.generateLog(request, response));
		return true;
	}

	public NCSALogRepository getNcsaLogRepository() {
		return ncsaLogRepository;
	}

	public void setNcsaLogRepository(NCSALogRepository ncsaLogRepository) {
		this.ncsaLogRepository = ncsaLogRepository;
	}

}
