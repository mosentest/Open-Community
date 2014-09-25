package org.mu.community.coop.controller;

import org.mu.community.common.dbutil.Page;
import org.mu.community.common.enumtype.NotificationClass;
import org.mu.community.common.security.Authentication;
import org.mu.community.coop.entity.Notification;
import org.mu.community.coop.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/coop/notifications")
public class NotificationController {
	
	private static final int NOTIFICATION_SIZE = 10;

	@Autowired
	private NotificationService notificationService;

	@RequestMapping(value = "{type}", method = RequestMethod.GET)
	public ModelAndView notifications(@AuthenticationPrincipal Authentication auth, 
			@PathVariable("type") String type, ModelMap model) {
		Page<Notification> notificationList = null;
		setCount(auth.getId(), model);
		if ("unread".equals(type)) {
			notificationList = notificationService.getUnread(auth.getId(), 0, NOTIFICATION_SIZE);
		} else {
			notificationList = notificationService.getNotifications(NotificationClass.getClass(type), auth.getId(), 0, NOTIFICATION_SIZE);
		}
		model.put("notificationList", notificationList);
		model.put("type", type);
		return new ModelAndView("coop/notificationList", model);
	}
	
	@RequestMapping(value = "{type}/{page}", method = RequestMethod.GET)
	public ModelAndView notifications(@AuthenticationPrincipal Authentication auth, 
			@PathVariable("type") String type, 
			@PathVariable("page") int page, ModelMap model) {
		Page<Notification> notificationList = null;
		setCount(auth.getId(), model);
		if ("unread".equals(type)) {
			notificationList = notificationService.getUnread(auth.getId(), page, NOTIFICATION_SIZE);
		} else {
			notificationList = notificationService.getNotifications(NotificationClass.getClass(type), auth.getId(), page, NOTIFICATION_SIZE);
		}
		model.put("notificationList", notificationList);
		model.put("type", type);
		return new ModelAndView("coop/notificationList", model);
	}
	
	private void setCount(long auth, ModelMap model) {
		model.put("nAll", notificationService.countUnread(null, auth));
		model.put("nUnread", model.get("nAll"));
		model.put("nSystem", notificationService.countUnread(NotificationClass.SYSTEM, auth));
		model.put("nProject", notificationService.countUnread(NotificationClass.PROJECT, auth));
		model.put("nPost", notificationService.countUnread(NotificationClass.POST, auth));
		model.put("nAt", notificationService.countUnread(NotificationClass.AT, auth));
	}
	
	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}
	
}
