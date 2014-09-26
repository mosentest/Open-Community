package org.mu.community.common.controller;

import org.mu.community.common.exception.NoUserException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ErrorController implements GenericController {

    @ExceptionHandler(NoUserException.class)
    public ModelAndView nouser(Exception exception) {
        return new ModelAndView("error/nouser", "error", exception.getMessage());
    }

	@RequestMapping(value = "notfound.html", method = RequestMethod.GET)
	public String notFound() {
		return "error/404";
	}
	
	@RequestMapping(value = "badrequest.html", method = RequestMethod.GET)
	public String badRequest() {
		return "error/400";
	}
	
	@RequestMapping(value = "accessdenied.html", method = RequestMethod.GET)
	public String accessdenied(ModelMap model) {
		model.addAttribute("error", true);
		return "error/403";
	}
	
	@RequestMapping(value = "timeout.html", method = RequestMethod.GET)
	public String timeout(ModelMap model) {
		model.addAttribute("error", true);
		return "error/timeout";
	}
}
