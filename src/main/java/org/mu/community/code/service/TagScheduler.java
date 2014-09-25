package org.mu.community.code.service;

import org.mu.community.code.repository.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TagScheduler {

	private static final Logger logger = LoggerFactory.getLogger(TagScheduler.class);
	
	@Autowired
	private TagRepository tagRepository;
	
	public void log() {
		logger.debug("");
	}
	
	public TagRepository getTagRepository() {
		return tagRepository;
	}

	public void setTagRepository(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}
	
}
