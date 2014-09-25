package org.mu.community.common.controller;

import java.util.List;

import org.mu.community.common.entity.JsonResponse;
import org.mu.community.common.entity.User;
import org.mu.community.common.security.Authentication;
import org.mu.community.common.service.UserService;
import org.mu.community.coop.service.PostService;
import org.mu.community.coop.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/coop/")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private PostService postService;

	@RequestMapping(value = "u/{path}", method = RequestMethod.GET)
	public ModelAndView home(@AuthenticationPrincipal Authentication auth, 
			@PathVariable("path") String path, ModelMap model) throws Exception {
		if (auth.getPath().equals(path)) {
			return new ModelAndView("redirect:/coop");
		}
		model.put("user", userService.getCompleteUser(0l, path, auth.getId()));
		return new ModelAndView("coop/userproject", model);
	}

	@RequestMapping(value = "u/{path}/project", method = RequestMethod.GET)
	public ModelAndView project(@AuthenticationPrincipal Authentication auth, 
			@PathVariable("path") String path, ModelMap model) throws Exception {
		model.put("user", userService.getCompleteUser(0l, path, auth.getId()));
		return new ModelAndView("coop/userproject", model);
	}

	@RequestMapping(value = "u/{path}/post", method = RequestMethod.GET)
	public ModelAndView post(@AuthenticationPrincipal Authentication auth, 
			@PathVariable("path") String path, ModelMap model) throws Exception {
		model.put("user", userService.getCompleteUser(0l, path, auth.getId()));
		return new ModelAndView("coop/userpost", model);
	}

	@RequestMapping(value = "u/{path}/activity", method = RequestMethod.GET)
	public ModelAndView activity(@AuthenticationPrincipal Authentication auth, 
			@PathVariable("path") String path, ModelMap model) throws Exception {
		model.put("user", userService.getCompleteUser(0l, path, auth.getId()));
		return new ModelAndView("coop/useractivity", model);
	}

	@RequestMapping(value = "coop/relationship/friends", method = RequestMethod.GET)
	public ModelAndView friends(@AuthenticationPrincipal Authentication auth, ModelMap model) {
		model.put("userList", userService.getFriends(auth.getId()));
		model.put("nFriends", userService.countFriends(auth.getId()));
		model.put("nFans", userService.countFans(auth.getId()));
		model.put("type", "friends");
		return new ModelAndView("coop/friendList", model);
	}

	@RequestMapping(value = "coop/relationship/fans", method = RequestMethod.GET)
	public ModelAndView fans(@AuthenticationPrincipal Authentication auth, ModelMap model) {
		model.put("userList", userService.getFans(auth.getId()));
		model.put("nFriends", userService.countFriends(auth.getId()));
		model.put("nFans", userService.countFans(auth.getId()));
		model.put("type", "fans");
		return new ModelAndView("coop/friendList", model);
	}

	@RequestMapping(value = "coop/relationship/add", method = RequestMethod.GET)
	public ModelAndView addRequest(@AuthenticationPrincipal Authentication auth, ModelMap model) {
		model.put("nFriends", userService.countFriends(auth.getId()));
		model.put("nFans", userService.countFans(auth.getId()));
		return new ModelAndView("coop/addFriend", model);
	}

	@RequestMapping(value = "coop/relationship/add", method = RequestMethod.POST)
	public @ResponseBody JsonResponse add(@AuthenticationPrincipal Authentication auth, 
			@RequestParam("path") String path, ModelMap model) {
		try {
			long user = userService.getId(path);
			userService.addFriend(auth.getId(), user);
			return new JsonResponse(true, "");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResponse(false, e.getMessage());
		}
	}
	
	@RequestMapping(value = "coop/relationship/remove", method = RequestMethod.POST)
	public @ResponseBody JsonResponse remove(@AuthenticationPrincipal Authentication auth, 
			@RequestParam("path") String path, @RequestParam("type") String type, ModelMap model) {
		try {
			long user = userService.getId(path);
			if ("friend".equals(type)) {
				userService.removeFriend(auth.getId(), user);
			} else {
				userService.removeFriend(user, auth.getId());
			}
			return new JsonResponse(true, "");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResponse(false, e.getMessage());
		}
	}
	
	@RequestMapping(value = "user/search", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody 
	public JsonResponse searchUser(@RequestParam("query") String query) {
		JsonResponse response = new JsonResponse();
		List<User> result = userService.search(query);
		if (result.size() > 0) {
			response.setSuccess(true);
			response.setContent(result);
		}
		try {
			Thread.sleep(2000);
		} catch (Exception e) {}
		return response;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	
}
