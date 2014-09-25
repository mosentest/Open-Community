package org.mu.community.code.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.mu.jmdb.spring.repository.RepositoryTemplate;
import org.mu.community.code.entity.Category;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;

@Repository("categoryRepository")
public class CategoryRepository extends RepositoryTemplate<Category, ObjectId> {
	
	public List<Category> findByType(String type) {
		return find(new BasicDBObject("type", type), new BasicDBObject("nCategory", -1), 0, 0);
	}

}
