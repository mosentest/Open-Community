package org.mu.community.coop.service;

import java.util.Date;

import org.mu.community.common.dbutil.Page;
import org.mu.community.common.dto.MessageDTO;
import org.mu.community.common.entity.User;
import org.mu.community.common.exception.InfoException;
import org.mu.community.common.repository.UserRepository;
import org.mu.community.coop.entity.Message;
import org.mu.community.coop.entity.MessageConversation;
import org.mu.community.coop.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("messageService")
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public long createMessage(long sender, MessageDTO dto) throws InfoException {
		Message message = dto.toObject();
		MessageConversation conversation = messageRepository.getConversation(message.getConversationId(), sender);
		if (conversation == null) {
			conversation = new MessageConversation();
			conversation.setUpdate(new Date());
			conversation.setCreate(new Date());
			conversation.setUser(new User(sender));
			User receiver = userRepository.getUserById(dto.getReceiver());
			if (receiver == null) {
				throw new InfoException("Receiver doesn't exist.");
			}
			conversation.setTarget(receiver);
			messageRepository.createConversation(conversation);
			if (conversation.getId() == 0) {
				throw new InfoException("Could not send message");
			}
		}
		if (conversation.getUser().getId() == sender) {
			message.setOutward(true);
		} else {
			message.setOutward(false);
		}
		message.setConversationId(conversation.getId());
		message.setCreate(new Date());
		messageRepository.createMessage(message);
		return conversation.getId();
	}
	
	public Message getRecentMessage(long id, long user) {
		return messageRepository.getRecentMessage(id, user);
	}
	
	@Transactional
	public MessageConversation getConversation(long user, long id, int page, int size) throws InfoException {
		MessageConversation conversation = messageRepository.getConversation(id, user);
		if (conversation == null) {
			throw new InfoException("Conversation doesn't exist.");
		}
		conversation.setMessages(messageRepository.getMessages(id, user, page * size, size));
		if (!conversation.isRead() && 
				((!conversation.getRecentMessage().isOutward() && conversation.getUser().getId() == user) || 
				(conversation.getRecentMessage().isOutward() && conversation.getUser().getId() != user))) {
			messageRepository.read(id);
		}
		return conversation;
	}

	@Transactional(readOnly = true)
	public Page<MessageConversation> getAll(long user, int page, int size) {
		Page<MessageConversation> messageList = new Page<>();
		messageList.setTotalElement(messageRepository.countAll(user), size);
		if (messageList.getTotalElement() == 0) {
			return messageList;
		}
		messageList.setContent(messageRepository.getAll(user, page * size, size));
		messageList.setCurrentPage(page);
		return messageList;
	}

	@Transactional(readOnly = true)
	public Page<MessageConversation> getUnread(long user, int page, int size) {
		Page<MessageConversation> messageList = new Page<>();
		messageList.setTotalElement(messageRepository.countUnread(user), size);
		if (messageList.getTotalElement() == 0) {
			return messageList;
		}
		messageList.setContent(messageRepository.getUnread(user, page * size, size));
		messageList.setCurrentPage(page);
		return messageList;
	}

	public int countAll(long user) {
		return messageRepository.countAll(user);
	}

	public int countUnread(long user) {
		return messageRepository.countUnread(user);
	}

	public void setMessageRepository(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
}
