package org.mu.community.code.entity;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mu.jmdb.annotations.CompoundIndex;
import org.mu.jmdb.annotations.Document;
import org.mu.jmdb.annotations.Field;
import org.mu.jmdb.annotations.Id;
import org.mu.jmdb.annotations.Indexed;
import org.mu.jmdb.annotations.Queries;
import org.mu.jmdb.annotations.Query;
import org.mu.jmdb.annotations.QueryField;
import org.mu.jmdb.annotations.QueryFields;
import org.mu.jmdb.core.Identifiable;

@Document(collection = "java_file", compoundIndexes = {
		@CompoundIndex(def = "{name:1,library:1}"),
})
@Queries({
	@Query(name = "getFile", query = "{name:#{name},library:#{library},path:#{path},extension:#{extension}}"),
	@Query(name = "getDirectory", query = "{ library : ${library}, path : ${path}}")
})
@QueryFields({
	@QueryField(name = "minimal", fields = "{name:1,extention:1,size:1,create:1}"),
	@QueryField(name = "complete", fields = "{name:1,extention:1,size:-1,create:1,tags:1,content:1,path:1}")
})
public class JavaFile extends CodeFile implements Identifiable {

	@Id
	protected ObjectId id;

	@Field
	private String library;

	@Field
	@Indexed(type = Indexed.IndexType.ASCEND)
	private String path;

	@Field
	private String name;

	@Field
	private String extension;

	@Field
	private String content;

	@Field
	private int nLine;

	@Field
	private long size;

	@Field
	private Date create;

	@Field
	private String[] tags;
	
	@Override
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getLibrary() {
		return library;
	}

	public void setLibrary(String library) {
		this.library = library;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public int getnLine() {
		return nLine;
	}

	public void setnLine(int nLine) {
		this.nLine = nLine;
	}
	
}
