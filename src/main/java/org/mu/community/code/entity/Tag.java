package org.mu.community.code.entity;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mu.jmdb.annotations.Document;
import org.mu.jmdb.annotations.Field;
import org.mu.jmdb.annotations.Id;
import org.mu.jmdb.annotations.Indexed;
import org.mu.jmdb.annotations.Queries;
import org.mu.jmdb.annotations.Query;
import org.mu.jmdb.annotations.QueryField;
import org.mu.jmdb.annotations.QueryFields;
import org.mu.jmdb.core.Identifiable;

@Document(collection = "tag", autoIndexId = false)
@Queries({
		@Query(name = "dateCompare", query = "{create:{$gte:#{start},lt:#{end}}}"),
		@Query(name = "increment", query = "{$inc:{nTag:#{increment}}}") })
@QueryFields({ @QueryField(name = "minimal", fields = "{tag:1,nTag:1,description:1}") })
public class Tag implements Identifiable {

	@Id
	private ObjectId id;

	@Field
	@Indexed(unique = true)
	private String tag;

	@Field
	private long nTag;

	@Field
	private String description;

	@Field
	private Date date;

	@Field
	private String type;

	@Override
	public ObjectId getId() {
		return id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public long getnTag() {
		return nTag;
	}

	public void setnTag(long nTag) {
		this.nTag = nTag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
