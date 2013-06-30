package com.lmaas.schedulife.sqlite;

import java.util.List;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	public static final String DATABASE_NAME = "schedulife-data.db";

	public MySQLiteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		this.applyDatabaseRevisions(db, 0, Integer.MAX_VALUE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		this.applyDatabaseRevisions(db, oldVersion, newVersion);
	}
	
	private void applyDatabaseRevisions(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		List<String> revisions = DatabaseRevisions.getInstance().getDatabaseRevisions();
		List<String> newRevisions = revisions.subList(oldVersion, newVersion);
		
		for (String revision : newRevisions) {
			db.execSQL(revision);
		}
	}

}
