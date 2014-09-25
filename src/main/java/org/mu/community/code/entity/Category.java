package org.mu.community.code.entity;

import org.bson.types.ObjectId;
import org.mu.jmdb.annotations.Document;
import org.mu.jmdb.annotations.Field;
import org.mu.jmdb.annotations.Id;
import org.mu.jmdb.annotations.Indexed;
import org.mu.jmdb.annotations.Queries;
import org.mu.jmdb.annotations.Query;
import org.mu.jmdb.core.Identifiable;

@Document(collection = "category", autoIndexId = false)
@Queries({
		@Query(name = "increment", query = "{$inc:{nTag:#{increment}}}") })
public class Category implements Identifiable {

	@Id
	private ObjectId id;

	@Field
	@Indexed(unique = true)
	private String category;
	
	@Field
	private String description;

	@Field
	private long nCategory;

	@Field
	private String type;

	@Override
	public ObjectId getId() {
		return id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getnCategory() {
		return nCategory;
	}

	public void setnCategory(long nCategory) {
		this.nCategory = nCategory;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
