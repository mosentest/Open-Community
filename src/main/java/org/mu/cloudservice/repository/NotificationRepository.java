package org.mu.cloudservice.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mu.opencomm.coop.entity.Notification;
import org.springframework.stereotype.Repository;

@Repository("notificationRepository")
public interface NotificationRepository {

	public long create(Notification notification);
	
	public List<Notification> getAll(@Param("user") long user, @Param("offset") int offset, @Param("size") int size);
	
	public List<Notification> getNotifications(@Param("user") long user, @Param("classType") String classType, @Param("system") boolean system,
                                               @Param("offset") int offset, @Param("size") int size);
	
	public List<Notification> getUnread(@Param("user") long user, @Param("offset") int offset, @Param("size") int size);

	public int countAllUnread(@Param("user") long user);
	
	public int countUnread(@Param("user") long user, @Param("classType") String classType, @Param("system") boolean system);
	
	public int read(@Param("notification") long notification, @Param("system") boolean system);
	
}
