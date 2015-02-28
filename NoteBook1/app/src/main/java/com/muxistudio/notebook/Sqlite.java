package com.zhu.notebook;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlite extends SQLiteOpenHelper{
		public Sqlite(Context context, String name, CursorFactory factory,
		int version) {
		super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table if not exists notes("
		+ "id integer primary key,"
		+ "content varchar)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
		
		public List<String> getNotes()
		{
			List<String> list = new ArrayList<String>();
			SQLiteDatabase db = getReadableDatabase();
			Cursor cursor = db.query("notes", null, null, null, null, null, "id asc");
			int contentIndex = cursor.getColumnIndex("content");
			for (cursor.moveToFirst();!(cursor.isAfterLast());cursor.moveToNext()) {
				list.add(cursor.getString(contentIndex));
			}
			cursor.close();
			db.close();
			return list;
		}
		
		public void insertNote(String note)
		{
			SQLiteDatabase db = getWritableDatabase();
			db.execSQL("insert into notes(content) values('" + note + "')");
			db.close();
		}
}
