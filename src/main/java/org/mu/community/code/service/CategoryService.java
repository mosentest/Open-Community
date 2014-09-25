package org.mu.community.code.service;

import java.util.List;

import org.mu.community.code.entity.Category;
import org.mu.community.code.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryService {

	private CategoryRepository categoryRepository;

	public List<Category> getMostTagged(String type) {
		return categoryRepository.findByType(type);
	}

    @Autowired
	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
}
