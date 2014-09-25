package org.mu.community.coop.controller;

import org.mu.community.common.dto.MessageDTO;
import org.mu.community.common.entity.JsonResponse;
import org.mu.community.common.entity.User;
import org.mu.community.common.exception.InfoException;
import org.mu.community.common.security.Authentication;
import org.mu.community.common.service.UserService;
import org.mu.community.coop.entity.Message;
import org.mu.community.coop.entity.MessageConversation;
import org.mu.community.coop.service.MessageService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/coop/")
public class MessageController {
	
	private static final int LIST_SIZE = 20;

	private static final String ALL = "all";

	private static final String UNREAD = "unread";

	@Autowired
	private MessageService messageService;

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "message/writeMessage", method = RequestMethod.GET)
	public ModelAndView createRequest(@AuthenticationPrincipal Authentication auth, 
			@RequestParam(value = "receiver", required = false, defaultValue = "0") Long receiver, ModelMap model) {
		model.put("nAll", messageService.countAll(auth.getId()));
		model.put("nUnread", messageService.countUnread(auth.getId()));
		if (receiver != 0) {
			User target = userService.getUser(receiver);
			if (target != null) {
				model.put("receiver", target.getAccount());
			}
		}
		return new ModelAndView("coop/writeMessage", model);
	}
	
	@RequestMapping(value = "message/new", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse newMessage(@AuthenticationPrincipal Authentication auth,
			@RequestParam("content") String content, 
			@RequestParam("receiver") String receiver) {
		JsonResponse response = new JsonResponse();
		User target = userService.getUser(receiver);
		if (target == null) {
			response.setMessage("User doesn't exist.");
			return response;
		} else if (target.getId() == auth.getId()) {
			response.setMessage("Can not send message to yourself.");
			return response;
		}
		try {
			long conversation = messageService.createMessage(auth.getId(), new MessageDTO(0l, target.getId(), content));
			response.setContent(conversation);
			response.setSuccess(true);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@RequestMapping(value = "message/write", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse writeMessage(@AuthenticationPrincipal Authentication auth,
			@RequestParam("content") String content, 
			@RequestParam("conversation") long conversation, 
			@RequestParam("receiver") long receiver) {
		JsonResponse response = new JsonResponse();
		MessageDTO dto = new MessageDTO(conversation, receiver, content);
		try {
			conversation = messageService.createMessage(auth.getId(), dto);
			Message message = messageService.getRecentMessage(conversation, auth.getId());
			if (message == null) {
				throw new Exception("Could not send nessage.");
			}
			response.setContent(message);
			response.setSuccess(true);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
		}
		return response;
	} 
	
	@RequestMapping(value = "message/{id}", method = RequestMethod.GET)
	public ModelAndView get(@AuthenticationPrincipal Authentication auth,
			@PathVariable("id") long id, RedirectAttributes redirectAttributes, ModelMap model) {
		MessageConversation conversation;
		try {
			conversation = messageService.getConversation(auth.getId(), id, 0, LIST_SIZE);
			model.put("conversation", conversation);
			model.put("nAll", messageService.countAll(auth.getId()));
			model.put("nUnread", messageService.countUnread(auth.getId()));
		} catch (InfoException e) {
			e.printStackTrace();
		}
		return new ModelAndView("coop/conversation", model);
	}
	
	@RequestMapping(value = "messages/{type}", method = RequestMethod.GET)
	public ModelAndView all(@AuthenticationPrincipal Authentication auth,
			@PathVariable("type") String type, ModelMap model) {
		long user = auth.getId();
		switch (type) {
			case UNREAD:
				model.put("messageList", messageService.getUnread(user, 0, LIST_SIZE));
				break;
			case ALL: default:
				model.put("messageList", messageService.getAll(user, 0, LIST_SIZE));
		}
		model.put("nAll", messageService.countAll(user));
		model.put("nUnread", messageService.countUnread(user));
		model.put("type", type);
		return new ModelAndView("coop/messageList", model);
	}
	
	@RequestMapping(value = "messages/{type}/{page}", method = RequestMethod.GET)
	public ModelAndView all(@AuthenticationPrincipal Authentication auth,
			@PathVariable("type") String type, @PathVariable("page") int page,
			ModelMap model) {
		long user = auth.getId();
		page = page <= 0 ? 0 : page;
		switch (type) {
			case UNREAD:
				model.put("messageList", messageService.getUnread(user, page, LIST_SIZE));
				break;
			case ALL: default:
				model.put("messageList", messageService.getAll(user, page, LIST_SIZE));
		}
		model.put("nAll", messageService.countAll(user));
		model.put("nUnread", messageService.countUnread(user));
		model.put("type", type);
		model.put("page", page);
		return new ModelAndView("coop/messageList", model);
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
