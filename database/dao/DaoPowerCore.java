package edu.byu.cs.superasteroids.database.dao;

import android.content.ContentValues;
import android.database.Cursor;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DBHelper;
import edu.byu.cs.superasteroids.model.ship.PowerCore;

public class DaoPowerCore {

	DBHelper dbHelper;

	public DaoPowerCore(DBHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	/**
	 * Will Insert all the values from the Table. 
	 * 
	 * @param powerCore PowerCore object to be inserted into the table
	 */
	public void insert(PowerCore powerCore) {

		ContentValues cv = new ContentValues();

		cv.put(Contract.PowerCore.COLUMN_NAME_CANNONBOOST, powerCore.getCannonBoost());
		cv.put(Contract.PowerCore.COLUMN_NAME_ENGINEBOOST, powerCore.getEngineBoost());
		cv.put(Contract.PowerCore.COLUMN_NAME_IMAGE, powerCore.getImage());

		long newRowId;

		newRowId = dbHelper.getWritableDatabase().insert(
				Contract.PowerCore.TABLE_NAME,
				Contract.PowerCore.COLUMN_NAME_CANNONBOOST,
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
