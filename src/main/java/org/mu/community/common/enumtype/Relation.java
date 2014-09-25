package org.mu.community.common.enumtype;

public enum Relation {

	FRIEND("F"), FOLLOWING("O"), FAN("A");
	
	private final String text;
	
	private Relation(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
}
