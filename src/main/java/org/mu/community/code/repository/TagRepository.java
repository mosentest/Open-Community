package org.mu.community.code.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.mu.jmdb.spring.repository.RepositoryTemplate;
import org.mu.community.code.entity.Tag;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;

@Repository("tagRepository")
public class TagRepository extends RepositoryTemplate<Tag, ObjectId> {
	
	public List<Tag> findByType(String type) {
		return this.find(
				new BasicDBObject("type", type), 
				mongoEntityManager.getQueryField(classType, "minimal"), 
				new BasicDBObject("nTags", -1), 0, 30);
	}

}
