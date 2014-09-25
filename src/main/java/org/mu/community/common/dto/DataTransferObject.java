package org.mu.community.common.dto;

public interface DataTransferObject<T> {

	public T toObject();
	
	public DataTransferObject<T> toDTO(T object);
	
}