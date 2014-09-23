package org.mu.cloudservice.entity;

import org.mu.jmdb.annotations.EmbeddedDocument;
import org.mu.jmdb.annotations.Field;

@EmbeddedDocument
public class RequestData {

	@Field
	private String method;

	@Field
	private String resource;

	@Field
	private String protocol;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
}
