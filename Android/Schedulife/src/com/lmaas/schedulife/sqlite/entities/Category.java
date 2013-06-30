package com.lmaas.schedulife.sqlite.entities;

import android.content.Context;

import com.orm.SugarRecord;

public class Category extends SugarRecord<Category> {

	private int categoryId;
	private String name;
	private int priority;
	
	public Category(Context context, int id, String name, int priority) {
		super(context);
		this.setName(name);
		this.setPriority(priority);
	}
	
	public Category(Context context){
		super(context);		
	}
	
	public int getCategoryId() {
		return this.categoryId;
	}
	
	public void setCategoryId(int newId) {
		this.categoryId = newId;
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
	
	@Override
	public String toString() {
		return this.name;
	}

}
