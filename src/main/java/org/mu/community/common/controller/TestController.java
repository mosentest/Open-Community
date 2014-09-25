package org.mu.community.common.controller;

import org.mu.community.common.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private TestService testService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView home() throws Exception {
        testService.newBlogs();
        return new ModelAndView("coop/userproject");
	}

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}
}
