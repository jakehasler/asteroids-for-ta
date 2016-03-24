package edu.byu.cs.superasteroids.database.dao;

import android.content.ContentValues;
import android.database.Cursor;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DBHelper;
import edu.byu.cs.superasteroids.model.world.Level;

public class DaoLevelAsteroid {

	DBHelper dbHelper;

	public DaoLevelAsteroid(DBHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	/**
	 * Will Insert all the values from the Table. 
	 * 
	 * @param levelAst Level Asteroid Object that gets added to the table
	 */
	public void insert(Level.LevelAsteroid levelAst) {
		ContentValues cv = new ContentValues();

		cv.put(Contract.LevelAsteroid.COLUMN_NAME_LEVELNUM, levelAst.getLevelNum());
		cv.put(Contract.LevelAsteroid.COLUMN_NAME_ID, levelAst.getId());
		cv.put(Contract.LevelAsteroid.COLUMN_NAME_NUM, levelAst.getNum());

		long newRowId;

		newRowId = dbHelper.getWritableDatabase().insert(
				Contract.LevelAsteroid.TABLE_NAME,
				Contract.LevelAsteroid.COLUMN_NAME_ID,
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
