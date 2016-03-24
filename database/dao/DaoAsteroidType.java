package edu.byu.cs.superasteroids.database.dao;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DBHelper;
import edu.byu.cs.superasteroids.model.asteroid.AsteroidType;


public class DaoAsteroidType {

	private DBHelper sqldb;

	public DaoAsteroidType(DBHelper sqldb) {
		this.sqldb = sqldb;
	}
	
	/**
	 * Will Insert all the values from the Table. 
	 * 
	 * @param asteroidType Asteroid Type Variable
	 */
	//public static void insert(String name, String image, int imageWidth, int imageHeight, String type) {
	public void insert(AsteroidType asteroidType) {

		ContentValues cv = new ContentValues();

		cv.put(Contract.AsteroidType.COLUMN_NAME_NAME, asteroidType.getName());
		cv.put(Contract.AsteroidType.COLUMN_NAME_IMAGE, asteroidType.getRealImage().getPath());
		cv.put(Contract.AsteroidType.COLUMN_NAME_IMGHEIGHT, asteroidType.getRealImage().getHeight());
		cv.put(Contract.AsteroidType.COLUMN_NAME_IMGWIDTH, asteroidType.getRealImage().getWidth());
		cv.put(Contract.AsteroidType.COLUMN_NAME_TYPE, asteroidType.getType());

		long newRowId;

		newRowId = sqldb.getWritableDatabase().insert(
				Contract.AsteroidType.TABLE_NAME,
				Contract.AsteroidType.COLUMN_NAME_NAME,
				cv
		);
		//System.out.println(newRowId);
	}
	
	/**
	 * Will return every value from the Table as a Cursor. 
	 * 
	 * @return A Cursor
	 */
	public Cursor query() {
		return null;
	}
	

}
