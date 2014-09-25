package org.mu.community.common.controller;

import javax.servlet.http.HttpSession;

import org.mu.community.common.dto.UserDTO;
import org.mu.community.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class DefaultController implements GenericController {

	@Autowired
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
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public ModelAndView signup(@ModelAttribute("user") UserDTO user, HttpSession session,
			RedirectAttributes redirectAttributes, ModelMap model) {
		String kaptcha = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (!kaptcha.equals(user.getCaptcha())) {
			redirectAttributes.addFlashAttribute(ERROR, "Captcha is invalid.");
			return new ModelAndView("redirect:/signup.html");
		}
		if (!user.getPassword().equals(user.getConfirm())) {
			redirectAttributes.addFlashAttribute(ERROR, "Password doesn't match.");
			return new ModelAndView("redirect:/signup.html");
		}
		try {
			userService.register(user);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute(ERROR, e.getMessage());
			return new ModelAndView("redirect:/signup.html");
		}
		redirectAttributes.addFlashAttribute(TIP, "Registration successful.");
		return new ModelAndView("redirect:/login.html");
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
