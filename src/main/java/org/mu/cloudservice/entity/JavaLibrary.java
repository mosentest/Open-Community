package org.mu.cloudservice.entity;

import java.util.Date;
import java.util.List;

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
import org.mu.opencomm.common.util.StringUtil;
import org.mu.opencomm.coop.entity.Project;
import org.mu.pom.entity.Pom;

@Document(collection = "java_library", compoundIndexes = {
		@CompoundIndex(def = "{name:1,project:1,version:1}", unique = true)
})
@Queries({
	@Query(name = "getAll", query = "{_id:{$in:#{idList}}}")
})
@QueryFields({
	@QueryField(name = "minimal", fields = "{name:1,description:1,size:1,create:1,project:1,version:1,tag:1,category:1}")
})
public class JavaLibrary implements Identifiable {
	
	public static final String LIB = "lib";

	public static final String DOC = "doc";
	
	public static final String SRC = "src";
	
	public static final String language = "Java";
	
	@Id
	protected ObjectId id;

	@Field
	private Project project;

	@Field
	private String name;

	@Field
	private String description;

	@Field
	private String extension;

	@Field
	private boolean verified;

	@Field
	private long size;

	@Field
	private String md5;

	@Field
	private String version;

	@Field
	private String source;

	@Field
	private String javadoc;

	@Field
	private long nDownload;

	@Field
	private long nFile;

	private Pom pomInfo;

	@Field
	private Date create;

	@Field
	private String[] categories;
	
	@Field
	@Indexed
	private String[] tags;

	@Field
	private List<FileInfo> structure;
	
	@Field
	private List<JarDependency> dependencies;
	
	private List<JavaFile> javaFiles;
	
	public JavaLibrary() {}

	public boolean hasSource() {
		return !StringUtil.isEmpty(source);	
	}
	
	@Override
	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getJavadoc() {
		return javadoc;
	}

	public void setJavadoc(String javadoc) {
		this.javadoc = javadoc;
	}

	public long getnDownload() {
		return nDownload;
	}

	public void setnDownload(long nDownload) {
		this.nDownload = nDownload;
	}

	public long getnFile() {
		return nFile;
	}

	public void setnFile(long nFile) {
		this.nFile = nFile;
	}

	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public List<FileInfo> getStructure() {
		return structure;
	}

	public void setStructure(List<FileInfo> structure) {
		this.structure = structure;
	}

	public List<JarDependency> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<JarDependency> dependencies) {
		this.dependencies = dependencies;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public List<JavaFile> getJavaFiles() {
		return javaFiles;
	}

	public void setJavaFiles(List<JavaFile> javaFiles) {
		this.javaFiles = javaFiles;
	}

	public Pom getPomInfo() {
		return pomInfo;
	}

	public void setPomInfo(Pom pomInfo) {
		this.pomInfo = pomInfo;
	}
}
