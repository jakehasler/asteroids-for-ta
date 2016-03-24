package edu.byu.cs.superasteroids.database.dao;

import android.database.Cursor;
import android.content.ContentValues;

import edu.byu.cs.superasteroids.database.DBHelper;
import edu.byu.cs.superasteroids.model.world.Level;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DBHelper;
import edu.byu.cs.superasteroids.model.world.Level;

public class DaoLevel {

	private DBHelper dbHelper;

	public DaoLevel(DBHelper dbHelper) {
		this.dbHelper = dbHelper;
	}
	
	/**
	 * Will Insert all the values from the Table. 
	 * 
	 * @param level Level object to be inserted into the table
	 */
	public void insert(Level level) {

		// DaoEngine.insert(baseSpeed,baseTurnRate, atPoint, image, imgWidth, imgHeight);
		ContentValues cv = new ContentValues();

		cv.put(Contract.Level.COLUMN_NAME_NUM, level.getNum());
		cv.put(Contract.Level.COLUMN_NAME_TITLE, level.getTitle());
		cv.put(Contract.Level.COLUMN_NAME_HINT, level.getHint());
		cv.put(Contract.Level.COLUMN_NAME_WIDTH, level.getWidth());
		cv.put(Contract.Level.COLUMN_NAME_HEIGHT, level.getHeight());
		cv.put(Contract.Level.COLUMN_NAME_MUSIC, level.getMusic());

		long newRowId;

		newRowId = dbHelper.getWritableDatabase().insert(
				Contract.Level.TABLE_NAME,
				Contract.Level.COLUMN_NAME_NUM,
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
