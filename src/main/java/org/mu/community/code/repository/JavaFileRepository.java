package org.mu.community.code.repository;

import org.bson.types.ObjectId;
import org.mu.jmdb.spring.repository.RepositoryTemplate;
import org.mu.jmdb.utility.QueryParam;
import org.mu.community.code.entity.JavaFile;
import org.springframework.stereotype.Repository;

@Repository("javaFileRepository")
public class JavaFileRepository extends RepositoryTemplate<JavaFile, ObjectId> {
	
	public JavaFile getJavaFile(String library, String path, String name, String extension) {
		return findOne("getFile", null, 
				new QueryParam("name", name).put("library", library).put("path", path).put("extension", extension));
	}
	
}
