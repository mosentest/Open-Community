package org.mu.community.coop.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mu.community.coop.entity.Message;
import org.mu.community.coop.entity.MessageConversation;
import org.springframework.stereotype.Repository;

@Repository("messageRepository")
public interface MessageRepository {
	
	public void createConversation(MessageConversation conversation);
	
	public void createMessage(Message message);
	
	public void read(long id);
	
	public MessageConversation getConversation(@Param("id") long id, @Param("user") long user);
	
	public List<Message> getMessages(@Param("id") long id, @Param("user") long user,
			@Param("offset") int offset, @Param("size") int size);
	
	public Message getRecentMessage(@Param("id") long id, @Param("user") long user);
	
	public int countUnread(long user);
	
	public int countAll(long user);
	
	public List<MessageConversation> getUnread(@Param("user") long user, @Param("offset") int offset, @Param("size") int size);
	
	public List<MessageConversation> getAll(@Param("user") long user, @Param("offset") int offset, @Param("size") int size);
}
