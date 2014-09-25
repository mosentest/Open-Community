package org.mu.community.coop.controller;

import java.util.List;

import org.mu.community.common.dto.PostDTO;
import org.mu.community.common.entity.JsonResponse;
import org.mu.community.common.entity.User;
import org.mu.community.common.security.Authentication;
import org.mu.community.common.service.UserService;
import org.mu.community.common.tag.PostContentParser;
import org.mu.community.coop.entity.Post;
import org.mu.community.coop.entity.PostComment;
import org.mu.community.coop.service.PostService;
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
public class PostController {

	private static final int POST_SIZE = 6;
	
	private static final int COMMENT_SIZE = 6;
	
	private static final int LIKE_SIZE = 6;
	
	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "u/{path}/o/{post}", method = RequestMethod.GET)
	public ModelAndView post(@AuthenticationPrincipal Authentication auth, 
			@PathVariable("path") String path, @PathVariable("post") long post,
			ModelMap model) {
		model.put("post", postService.getPost(post, 0, COMMENT_SIZE, 0, LIKE_SIZE));
		try {
			model.put("user", userService.getCompleteUser(0l, path, auth.getId()));
		} catch (Exception e) {}
		model.put("singlePost", true);
		return new ModelAndView("coop/userpost", model);
	}

	@RequestMapping(value = "post", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse post(@AuthenticationPrincipal Authentication auth, 
			@RequestParam("content") String content) {
		JsonResponse response = new JsonResponse();
		try {
			PostDTO dto = new PostDTO();
			dto.setContent(content);
			Post post = postService.createPost(dto, auth.getId());
			response.setSuccess(true);
			post.setContent(PostContentParser.parse(post.getContent()));
			response.setContent(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value = "post/{post}/comment", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse comment(@AuthenticationPrincipal Authentication auth,
			@PathVariable("post") long post, 
			@RequestParam("comment") String comment) {
		JsonResponse response = new JsonResponse();
		try {
			PostDTO dto = new PostDTO();
			dto.setContent(comment);
			dto.setPost(post);
			PostComment object = postService.createPostComment(dto, auth.getId());
			response.setSuccess(true);
			object.setContent(PostContentParser.parse(object.getContent()));
			response.setContent(object);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "post/{post}/like", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse like(@AuthenticationPrincipal Authentication auth, 
			@PathVariable("post") long post) {
		JsonResponse response = new JsonResponse();
		try {
			postService.like(auth.getId(), post);
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value = "post/{post}/dislike", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse dislike(@AuthenticationPrincipal Authentication auth, 
			@PathVariable("post") long post) {
		JsonResponse response = new JsonResponse();
		try {
			postService.dislike(auth.getId(), post);
			response.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value = "u/{path}/posts", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse posts(@AuthenticationPrincipal Authentication auth, 
			@RequestParam(value = "page", required = false) int page) {
		JsonResponse response = new JsonResponse();
		List<Post> posts = postService.getPosts(auth.getId(), auth.getId(), 
				page, POST_SIZE, 0, COMMENT_SIZE, 0, LIKE_SIZE);
		if (posts.size() > 0) {
			response.setSuccess(true);
			response.setContent(posts);
			for (Post post : posts) {
				post.setContent(PostContentParser.parse(post.getContent()));
				if (post.getnComment() > 0) {
					for (PostComment comment : post.getComments()) {
						comment.setContent(PostContentParser.parse(comment.getContent()));
					}
				}
			}
		} else {
			response.setMessage("No more posts to load.");
		}
		return response;
	}

	@RequestMapping(value = "post/{post}/comments", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse comments(@AuthenticationPrincipal Authentication auth, 
			@PathVariable("post") long post,
			@RequestParam(value = "page", required = false) int page) {
		JsonResponse response = new JsonResponse();
		List<PostComment> comments = postService.getComments(post, page, COMMENT_SIZE);
		if (comments.size() > 0) {
			response.setSuccess(true);
			response.setContent(comments);
		} else {
			response.setMessage("No more comments to load.");
		}
		return response;
	}

	@RequestMapping(value = "post/{post}/likes", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse likes(@AuthenticationPrincipal Authentication auth, 
			@PathVariable("post") long post,
			@RequestParam(value = "page", required = false) int page) {
		JsonResponse response = new JsonResponse();
		List<User> likes = postService.getLikes(post, page, LIKE_SIZE);
		if (likes.size() > 0) {
			response.setSuccess(true);
			response.setContent(likes);
		} else {
			response.setMessage("No more information to load.");
		}
		return response;
	}

	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
