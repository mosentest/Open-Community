package org.mu.community.code.entity;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mu.jmdb.annotations.Document;
import org.mu.jmdb.annotations.Field;
import org.mu.jmdb.core.Identifiable;

@Document(collection = "ncsa_log", autoIndexId = false)
public class NCSALog implements Identifiable {

	@Field
	private String remotehost;

	@Field
	private String ident;

	@Field
	private String authuser;

	@Field
	private Date date;

	@Field
	private RequestData request;

	@Field
	private int status;

	@Field
	private long bytes;

	@Field
	private String referrer;

	@Field
	private String agent;

	@Override
	public ObjectId getId() {
		return null;
	}

	public String getRemotehost() {
		return remotehost;
	}

	public void setRemotehost(String remotehost) {
		this.remotehost = remotehost;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public String getAuthuser() {
		return authuser;
	}

	public void setAuthuser(String authuser) {
		this.authuser = authuser;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public RequestData getRequest() {
		return request;
	}

	public void setRequest(RequestData request) {
		this.request = request;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getBytes() {
		return bytes;
	}

	public void setBytes(long bytes) {
		this.bytes = bytes;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}
	
}
