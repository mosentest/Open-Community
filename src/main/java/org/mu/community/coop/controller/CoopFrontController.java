package org.mu.community.coop.controller;

import org.mu.community.common.security.Authentication;
import org.mu.community.common.service.UserService;
import org.mu.community.coop.service.PostService;
import org.mu.community.coop.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/coop")
public class CoopFrontController {

	private static final int POST_SIZE = 6;
	
	private static final int COMMENT_SIZE = 6;
	
	private static final int LIKE_SIZE = 6;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private PostService postService;
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public ModelAndView home(@AuthenticationPrincipal Authentication auth, ModelMap model) {
		model.addAttribute("projectList", projectService.getUserProject(auth.getId(), false, 0, 10));
		model.addAttribute("nProject", projectService.countUserProject(auth.getId(), false));
		model.put("postList", postService.getPosts(auth.getId(), auth.getId(), 
				0, POST_SIZE, 0, COMMENT_SIZE, 0, LIKE_SIZE));
		model.put("user", auth.getUser());
		return new ModelAndView("coop/index", model);
	}

	@RequestMapping(value = "u/{user}/projects", method = RequestMethod.GET)
	public ModelAndView projects(ModelMap model) {
		return new ModelAndView("coop/projects", model);
	}

	@RequestMapping(value = "u/{user}/tasks", method = RequestMethod.GET)
	public ModelAndView tasks(ModelMap model) {
		return new ModelAndView("coop/tasks", model);
	}

	@RequestMapping(value = "u/{user}/activities", method = RequestMethod.GET)
	public ModelAndView activities(ModelMap model) {
		return new ModelAndView("coop/activities", model);
	}

	@RequestMapping(value = "messages", method = RequestMethod.GET)
	public ModelAndView messages(ModelMap model) {
		return new ModelAndView("coop/messages", model);
	}

	@RequestMapping(value = "settings", method = RequestMethod.GET)
	public ModelAndView settings(ModelMap model) {
		return new ModelAndView("coop/settings", model);
	}
	
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	
}
