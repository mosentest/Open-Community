package org.mu.community.coop.service;

import org.mu.community.common.dbutil.Page;
import org.mu.community.common.enumtype.NotificationClass;
import org.mu.community.coop.entity.Notification;
import org.mu.community.coop.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("notificationService")
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;
	
	@Transactional
	public Page<Notification> getNotifications(NotificationClass nClass, long user, int page, int size) {
		Page<Notification> notificationList = new Page<>();
		if (nClass == null) {
			notificationList.setTotalElement(notificationRepository.countAllUnread(user), size);
			if (notificationList.getTotalElement() == 0) {
				return notificationList;
			}
			notificationList.setContent(notificationRepository.getAll(user, page * size, size));
		} else {
			notificationList.setTotalElement(
					notificationRepository.countUnread(user, nClass.getIdent(), nClass == NotificationClass.SYSTEM), size);
			if (notificationList.getTotalElement() == 0) {
				return notificationList;
			}
			notificationList.setContent(notificationRepository.getNotifications(user, nClass.getIdent(), nClass == NotificationClass.SYSTEM, 
					page * size, size));
		}
		return notificationList;
	}
	
	@Transactional
	public Page<Notification> getUnread(long user, int page, int size) {
		Page<Notification> notificationList = new Page<>();
		notificationList.setTotalElement(notificationRepository.countAllUnread(user), size);
		if (notificationList.getTotalElement() == 0) {
			return notificationList;
		}
		notificationList.setContent(notificationRepository.getUnread(user, page * size, size));
		return notificationList;
	}
	
	public int countUnread(NotificationClass nClass, long user) {
		if (nClass == null) {
			return notificationRepository.countAllUnread(user);
		}
		return notificationRepository.countUnread(user, nClass.getIdent(), nClass == NotificationClass.SYSTEM);
	}

	public void setNotificationRepository(NotificationRepository notificationRepository) {
		this.notificationRepository = notificationRepository;
	}
	
}
