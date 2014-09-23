package org.mu.cloudservice.entity;

import org.mu.jmdb.annotations.EmbeddedDocument;
import org.mu.jmdb.annotations.Field;

@EmbeddedDocument
public class JarDependency {

	@Field
	private String dependedJar;
	
	@Field
	private String versionFrom;
	
	@Field
	private String versionTo;

	public String getDependedJar() {
		return dependedJar;
	}

	public void setDependedJar(String dependedJar) {
		this.dependedJar = dependedJar;
	}

	public String getVersionFrom() {
		return versionFrom;
	}

	public void setVersionFrom(String versionFrom) {
		this.versionFrom = versionFrom;
	}

	public String getVersionTo() {
		return versionTo;
	}

	public void setVersionTo(String versionTo) {
		this.versionTo = versionTo;
	}
	

	
}
