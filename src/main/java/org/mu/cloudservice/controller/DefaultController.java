package org.mu.cloudservice.controller;

import javax.servlet.http.HttpSession;

import org.mu.cloudservice.dto.UserDTO;
import org.mu.cloudservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class DefaultController {

	private UserService userService;
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public ModelAndView home(HttpSession session, ModelMap model) {
		return new ModelAndView("home", model);
	}

	@RequestMapping(value = "home.html", method = RequestMethod.GET)
	public ModelAndView home() {
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = "login.html", method = RequestMethod.GET)
	public String loginRequest() {
		return "login";
	}
	
	@RequestMapping(value = "signup.html", method = RequestMethod.GET)
	public ModelAndView signupRequest(ModelMap model) {
		model.put("user", new UserDTO());
		return new ModelAndView("signup", model);
	}

    @Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
