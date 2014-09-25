package org.mu.community.code.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("javaLibraryRDRepository")
public interface JavaLibraryRDRepository {

	public int associateClass(@Param("id") String id, @Param("classes") String classes);
	
	public List<String> searchLibrary(@Param("name") String name, @Param("offset") int offset, @Param("size") int size);
	
	public long countLibrary(@Param("name") String name);
	
}
