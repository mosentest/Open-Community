package org.mu.cloudservice.service;

import java.util.List;

import org.mu.opencomm.code.entity.Category;
import org.mu.opencomm.code.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> getMostTagged(String type) {
		return categoryRepository.findByType(type);
	}

	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
}
