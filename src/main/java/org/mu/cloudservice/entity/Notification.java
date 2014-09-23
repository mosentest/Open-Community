package org.mu.cloudservice.entity;

import java.util.Date;

import org.mu.opencomm.common.entity.User;
import org.mu.opencomm.common.enumtype.NotificationClass;
import org.mu.opencomm.common.enumtype.NotificationType;

public class Notification {

	private long id;
	
	private User user;
	
	private Date create;
	
	private boolean read;	
	
	private boolean system;
	
	private NotificationClass classType;
	
	private NotificationType type;
	
	private User linkUser;

	private long linkId1;

	private long linkId2;

	private long linkId3;

	private long linkId4;

	private long linkId5;
	
	private String content;

	public Notification() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public boolean isSystem() {
		return system;
	}

	public void setSystem(boolean system) {
		this.system = system;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public User getLinkUser() {
		return linkUser;
	}

	public void setLinkUser(User linkUser) {
		this.linkUser = linkUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public NotificationClass getClassType() {
		return classType;
	}

	public void setClassType(NotificationClass classType) {
		this.classType = classType;
	}

	public long getLinkId1() {
		return linkId1;
	}

	public void setLinkId1(long linkId1) {
		this.linkId1 = linkId1;
	}

	public long getLinkId2() {
		return linkId2;
	}

	public void setLinkId2(long linkId2) {
		this.linkId2 = linkId2;
	}

	public long getLinkId3() {
		return linkId3;
	}

	public void setLinkId3(long linkId3) {
		this.linkId3 = linkId3;
	}

	public long getLinkId4() {
		return linkId4;
	}

	public void setLinkId4(long linkId4) {
		this.linkId4 = linkId4;
	}

	public long getLinkId5() {
		return linkId5;
	}

	public void setLinkId5(long linkId5) {
		this.linkId5 = linkId5;
	}
}
