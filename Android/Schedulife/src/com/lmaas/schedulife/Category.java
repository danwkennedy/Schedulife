package com.lmaas.schedulife;

public class Category {

	private int id;
	private String name;
	private int priority;
	
	public Category(int id, String name, int priority) {
		this.setName(name);
		this.setPriority(priority);
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int newId) {
		this.id = newId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public String toString() {
		return this.name;
	}
	
	
	
	
	
}

