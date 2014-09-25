package org.mu.community.code.service;

import java.util.List;

import org.mu.community.code.entity.Tag;
import org.mu.community.code.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tagService")
public class TagService {

	@Autowired
	private TagRepository tagRepository;

	public List<Tag> getMostTagged(String type) {
		return tagRepository.findByType(type);
	}

	public void setTagRepostory(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}
	
}
