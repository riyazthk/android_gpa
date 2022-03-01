package com.vishnu.cgpa;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CgpaDatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME="MyFriendsDatabase";
	private static final String DATABASE_TABLE = "StoreCgpa";
	private static final int DATABASE_VERSION = 1;
	
	public CgpaDatabaseHelper(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		 
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE "+ DATABASE_TABLE+" (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
		"name TEXT,dept TEXT,reg TEXT, sem1 REAL, sem2 REAL, sem3 REAL, sem4 REAL" +
		", sem5 REAL, sem6 REAL, sem7 REAL, sem8 REAL,cgpa REAL);");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
		onCreate(db);
	}
	
	public long addCgpa(String name,String dept,String reg,float sem1,float sem2,float sem3,float sem4,float sem5,
			float sem6,float sem7,float sem8,float cgpa) throws SQLException
	 
	{
	 
	ContentValues values=new ContentValues(12);
	values.put("name", name);
	values.put("dept", dept);
	values.put("reg", reg);
	values.put("sem1", sem1);
	values.put("sem2", sem2);
	values.put("sem3", sem3);
	values.put("sem4", sem4);
	values.put("sem5", sem5);
	values.put("sem6", sem6);
	values.put("sem7", sem7);
	values.put("sem8", sem8);
	values.put("cgpa", cgpa);
	return getWritableDatabase().insert(DATABASE_TABLE, "name", values);
	 
	}
	
	public ArrayList<String> getSavedCgpaNames()
	 
	{
	 
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
	
	public ArrayList<String> getFullSavedCgpa(int myid){
		
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
