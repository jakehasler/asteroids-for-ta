package edu.byu.cs.superasteroids.database.dao;

import android.content.ContentValues;
import android.database.Cursor;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DBHelper;
import edu.byu.cs.superasteroids.model.world.Level;

public class DaoLevelObject {

	DBHelper dbHelper;

	public DaoLevelObject(DBHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	/**
	 * Will Insert all the values from the Table. 
	 * 
	 * @param levelObject Level Object object to be inserted into the table
	 */
	public void insert(Level.LevelObject levelObject) {
		ContentValues cv = new ContentValues();

		cv.put(Contract.LevelObject.COLUMN_NAME_LEVELNUM, levelObject.getLevelNum());
		cv.put(Contract.LevelObject.COLUMN_NAME_POS, levelObject.getPos());
		cv.put(Contract.LevelObject.COLUMN_NAME_OBJID, levelObject.getObjId());
		cv.put(Contract.LevelObject.COLUMN_NAME_SCALE, levelObject.getScale());

		long newRowId;

		newRowId = dbHelper.getWritableDatabase().insert(
				Contract.LevelObject.TABLE_NAME,
				Contract.LevelObject.COLUMN_NAME_POS,
				cv
		);
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
