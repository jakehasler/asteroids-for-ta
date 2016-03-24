package edu.byu.cs.superasteroids.database.dao;

import android.content.ContentValues;
import android.database.Cursor;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DBHelper;
import edu.byu.cs.superasteroids.model.ship.Cannon;

public class DaoCannon {

	DBHelper dbHelper;

	public DaoCannon(DBHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	/**
	 * Will Insert all the values from the Table. 
	 * 
	 * @param cannon Cannon object to be inserted into the table
	 */
	public void insert(Cannon cannon) {

		ContentValues cv = new ContentValues();

		cv.put(Contract.Cannon.COLUMN_NAME_ATPOINT, cannon.getAp());
		cv.put(Contract.Cannon.COLUMN_NAME_EMPOINT, cannon.getEp());
		cv.put(Contract.Cannon.COLUMN_NAME_IMAGE, cannon.getImage());
		cv.put(Contract.Cannon.COLUMN_NAME_IMGWIDTH, cannon.getImgWidth());
		cv.put(Contract.Cannon.COLUMN_NAME_IMGHEIGHT, cannon.getImgHeight());
		cv.put(Contract.Cannon.COLUMN_NAME_ATIMG, cannon.getAtImg());
		cv.put(Contract.Cannon.COLUMN_NAME_ATIMGWIDTH, cannon.getAtImgWidth());
		cv.put(Contract.Cannon.COLUMN_NAME_ATIMGHEIGHT, cannon.getAtImgHeight());
		cv.put(Contract.Cannon.COLUMN_NAME_ATSOUND, cannon.getAtSound());
		cv.put(Contract.Cannon.COLUMN_NAME_DAMAGE, cannon.getDamage());

		long newRowId;

		newRowId = dbHelper.getWritableDatabase().insert(
				Contract.Cannon.TABLE_NAME,
				Contract.Cannon.COLUMN_NAME_ATPOINT,
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
