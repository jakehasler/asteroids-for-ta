package edu.byu.cs.superasteroids.database.dao;

import android.content.ContentValues;
import android.database.Cursor;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DBHelper;
import edu.byu.cs.superasteroids.model.ship.MainBody;

public class DaoMainBody {

	DBHelper dbHelper;

	DaoMainBody(DBHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	/**
	 * Will Insert all the values from the Table. 
	 * 
	 * @param mainBody Main Body object to push into the table
	 */
	public void insert(MainBody mainBody) {
		ContentValues cv = new ContentValues();

		cv.put(Contract.MainBody.COLUMN_NAME_CANNONAT, mainBody.getCannonAt());
		cv.put(Contract.MainBody.COLUMN_NAME_ENGINEAT, mainBody.getEngineAt());
		cv.put(Contract.MainBody.COLUMN_NAME_EXTRAAT, mainBody.getExtraAt());
		cv.put(Contract.MainBody.COLUMN_NAME_IMAGE, mainBody.getImage());
		cv.put(Contract.MainBody.COLUMN_NAME_IMGWIDTH, mainBody.getImgWidth());
		cv.put(Contract.MainBody.COLUMN_NAME_IMGHEIGHT, mainBody.getImgHeight());

		long newRowId;

		newRowId = dbHelper.getWritableDatabase().insert(
				Contract.MainBody.TABLE_NAME,
				Contract.MainBody.COLUMN_NAME_CANNONAT,
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
