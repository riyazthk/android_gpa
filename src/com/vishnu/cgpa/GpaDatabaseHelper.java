package com.vishnu.cgpa;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GpaDatabaseHelper  extends SQLiteOpenHelper {

	private static final String DATABASE_NAME="GpaDatabase";
	private static final String DATABASE_TABLE = "StoreGpa";
	private static final int DATABASE_VERSION = 1;
	
	public GpaDatabaseHelper(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DATABASE_NAME, null, DATABASE_VERSION); 
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE "+ DATABASE_TABLE+" (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
		"name TEXT,dept TEXT,reg TEXT, sub1 INTEGER, sub2 INTEGER, sub3 INTEGER, sub4 INTEGER" +
		", sub5 INTEGER, sub6 INTEGER, lab1 INTEGER, lab2 INTEGER, lab3 INTEGER, lab4 INTEGER,gpa REAL);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
		onCreate(db);
	}

	public long addCgpa(String cgpaEntryName, String department,
			String regulation, int g1, int g2, int g3, int g4, int g5, int g6,
			int glab1, int glab2, int glab3, int glab4, float mygpa) {
		// TODO Auto-generated method stub
		ContentValues values=new ContentValues(15);
		values.put("name", cgpaEntryName);
		values.put("dept", department);
		values.put("reg", regulation);
		values.put("sub1", g1);
		values.put("sub2", g2);
		values.put("sub3", g3);
		values.put("sub4", g4);
		values.put("sub5", g5);
		values.put("sub6", g6);
		values.put("lab1", glab1);
		values.put("lab2", glab2);
		values.put("lab3", glab3);
		values.put("lab4", glab4);
		values.put("gpa", mygpa);
		return getWritableDatabase().insert(DATABASE_TABLE, "name", values);
	}

	public ArrayList<String> getSavedCgpaNames() {
		// TODO Auto-generated method stub
		ArrayList<String> ID = new ArrayList<String>();
		String temp;
		String[] columns = new String[] { "id", "name"};
		Cursor c = getReadableDatabase().query(DATABASE_TABLE, columns,
				null, null, null, null, null);
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			temp=c.getString(0)+"-"+c.getString(1);
			//System.out.println(temp);
			ID.add(temp);
		}
		c.close();
		return ID;
	}

	public ArrayList<String> getFullSavedGpa(int myid) {
		// TODO Auto-generated method stub
ArrayList<String> ID = new ArrayList<String>();
		
		
		Cursor c = getReadableDatabase().rawQuery("SELECT * FROM "+DATABASE_TABLE+" where id=" +
				myid, null);
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			ID.add(c.getString(1));
			ID.add(c.getString(2));
			ID.add(c.getString(3));
			ID.add(c.getString(4));
			ID.add(c.getString(5));
			ID.add(c.getString(6));
			ID.add(c.getString(7));
			ID.add(c.getString(8));
			ID.add(c.getString(9));
			ID.add(c.getString(10));
			ID.add(c.getString(11));
			ID.add(c.getString(12));
			ID.add(c.getString(13));
			ID.add(c.getString(14));
			
		}
		c.close();
		return ID;
	}

	public int deleteEntry(int myid) {
		// TODO Auto-generated method stub
		String id = String.valueOf(myid);
		return getWritableDatabase().delete(DATABASE_TABLE, "id=?",new String[]{id});
	}
}
