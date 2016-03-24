package edu.byu.cs.superasteroids.database.dao;

import android.content.ContentValues;
import android.database.Cursor;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DBHelper;
import edu.byu.cs.superasteroids.model.ship.Engine;

public class DaoEngine {

	DBHelper dbHelper;

	public DaoEngine(DBHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	/**
	 * Will Insert all the values from the Table. 
	 * 
	 * @param engine Engine object to be inserted into the table
	 */
	public void insert(Engine engine) {

		ContentValues cv = new ContentValues();

		cv.put(Contract.Engine.COLUMN_NAME_BASESPEED, engine.getBaseSpeed());
		cv.put(Contract.Engine.COLUMN_NAME_BASETURNRATE, engine.getBaseTurnRate());
		cv.put(Contract.Engine.COLUMN_NAME_ATPOINT, engine.getAtPoint());
		cv.put(Contract.Engine.COLUMN_NAME_IMAGE, engine.getImage());
		cv.put(Contract.Engine.COLUMN_NAME_IMGWIDTH, engine.getImgWidth());
		cv.put(Contract.Engine.COLUMN_NAME_IMGHEIGHT, engine.getImgHeight());

		long newRowId;

		newRowId = dbHelper.getWritableDatabase().insert(
				Contract.Engine.TABLE_NAME,
				Contract.Engine.COLUMN_NAME_BASESPEED,
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
