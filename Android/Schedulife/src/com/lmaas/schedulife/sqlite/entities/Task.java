package com.lmaas.schedulife.sqlite.entities;

import java.util.Date;
import android.content.Context;
import com.orm.SugarRecord;

public class Task extends SugarRecord<Task> {
	
	private int noteId;
	private String name;
	
	private int timeToFinishSeconds;
	private int unitOfTimeSeconds;
	
	private Date dueDate;
	private Category category;
	
	public Task(Context context) {
		super(context);
	}
	
	public Task(Context context, int noteId, String name,
			int timeToFinishSeconds, int unitOfTimeSeconds, Date dueDate,
			Category category) {
		super(context);
		this.noteId = noteId;
		this.name = name;
		this.timeToFinishSeconds = timeToFinishSeconds;
		this.unitOfTimeSeconds = unitOfTimeSeconds;
		this.dueDate = dueDate;
		this.category = category;
	}
	
	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTimeToFinishSeconds() {
		return timeToFinishSeconds;
	}

	public void setTimeToFinishSeconds(int timeToFinishSeconds) {
		this.timeToFinishSeconds = timeToFinishSeconds;
	}

	public int getUnitOfTimeSeconds() {
		return unitOfTimeSeconds;
	}

	public void setUnitOfTimeSeconds(int unitOfTimeSeconds) {
		this.unitOfTimeSeconds = unitOfTimeSeconds;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
