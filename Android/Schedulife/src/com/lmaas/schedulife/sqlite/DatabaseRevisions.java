package com.lmaas.schedulife.sqlite;

import java.util.ArrayList;
import java.util.List;

public class DatabaseRevisions {
	
	private static DatabaseRevisions _instance;
	
	public static DatabaseRevisions getInstance() {
		if (_instance == null) {
			_instance = new DatabaseRevisions();
		}
		return _instance;
	}
	
	private DatabaseRevisions() {}
	
	public List<String> getDatabaseRevisions() {
		
		List<String> revisions = new ArrayList<String>();
		
		// Revision 1
		revisions.add("CREATE TABLE schedulife_task " +
				"(" +
				")");
		revisions.add("CREATE TABLE schedulife_task_category");
		
		return revisions;
	}

}
