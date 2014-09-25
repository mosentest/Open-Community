package org.mu.community.common.entity;

public class JsonResponse {

	private boolean success;
	
	private String message;
	
	private Object content;
	
	public JsonResponse() {
	}
	
	public JsonResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	@Override
	public String toString() {
		if (content == null) {
			return String.format("{success:%b,message:'%s'}", success, message); 
		}
		return null;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
}
