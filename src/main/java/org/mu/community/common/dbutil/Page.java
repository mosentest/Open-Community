package org.mu.community.common.dbutil;

import java.util.Iterator;
import java.util.List;

public class Page<T> implements Iterable<T> {
	
	private long totalElement;
	
	private int currentPage;
	
	private int totalPage;
	
	private List<T> content;
	
	public Page() {}
	
	public boolean isEmpty() {
		return totalElement == 0;
	}

	public void setTotalElement(long totalElement, int size) {
		this.totalElement = totalElement;
		if (totalElement % size == 0) {
			totalPage = (int) totalElement / size;
		} else {
			totalPage = (int) totalElement / size + 1;
		}
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public long getTotalElement() {
		return totalElement;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public List<T> getContent() {
		return content;
	}

	@Override
	public Iterator<T> iterator() {
		return content.iterator();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
