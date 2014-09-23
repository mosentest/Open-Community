package org.mu.cloudservice.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.mu.jmdb.spring.repository.RepositoryTemplate;
import org.mu.jmdb.utility.QueryParam;
import org.mu.opencomm.code.entity.JavaLibrary;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;

@Repository("javaLibraryRepository")
public class JavaLibraryRepository extends RepositoryTemplate<JavaLibrary, ObjectId> {
	
	public List<JavaLibrary> getLibraries(List<String> idList, int offset, int size) {
		ObjectId[] idArray  = new ObjectId[idList.size()];
		for (int i = 0; i < idList.size(); i++) {
			idArray[i] = new ObjectId(idList.get(i));
		}
		return find("getAll", "minimal", new QueryParam("idList", idArray), 
				new BasicDBObject("nDownload", -1), offset, size);
	}
	
	public JavaLibrary getLibrary(String id) {
		return findOne(new BasicDBObject("_id", new ObjectId(id)), new BasicDBObject("name", 1));
	}
	
	public List<JavaLibrary> search(String name, String project, String version,
			String[] category, String[] tag, boolean verified, int offset, int limit) {
		return find(buildQueryObject(name, project, version, category, tag, verified), 
				mongoEntityManager.getQueryField(classType, "minimal"), new BasicDBObject("downloads", 1), offset, limit);
	}
	
	public long search(String name, String project, String version,
			String[] category, String[] tag, boolean verified) {
		return count(buildQueryObject(name, project, version, category, tag, verified));
	}
	
	public List<JavaLibrary> getJarFiles(int offset, int limit) {
		return find(null, null, new BasicDBObject("create", 1), offset, limit);
	}
	
	public JavaLibrary getJarFile(String name) {
		return findOne(new BasicDBObject("name", name), null);
	}
	
	private BasicDBObject buildQueryObject(String name, String project, String version,
			String[] category, String[] tag, boolean verified) {
		BasicDBObject query = new BasicDBObject();
		if (name != null) {
			query.put("name", new BasicDBObject("$regex", name));
		}
		if (project != null) {
			query.put("project", new BasicDBObject("$regex", project));
		}
		if (version != null) {
			query.put("version", new BasicDBObject("$regex", version));
		}
		if (category != null) {
			query.put("categories", new BasicDBObject("$all", category));
		}
		if (tag != null) {
			query.put("tags", new BasicDBObject("$all", tag));
		}
		if (verified) {
			query.put("verified", true);
		}
		return query;
	}

}
