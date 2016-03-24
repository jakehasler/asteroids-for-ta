package edu.byu.cs.superasteroids.database.dao;

import android.content.ContentValues;
import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DBHelper;
import edu.byu.cs.superasteroids.model.ship.ExtraParts;

public class DaoExtraParts {

	DBHelper dbHelper;

	public DaoExtraParts(DBHelper dbHelper) {
		this.dbHelper = dbHelper;
	}
	
	/**
	 * Will Insert all the values from the Table. 
	 * 
	 * @param extraParts ExtraParts object to be inserted into the table
	 */
	public void insert(ExtraParts extraParts) {

		ContentValues cv = new ContentValues();

		cv.put(Contract.ExtraParts.COLUMN_NAME_ATPOINT, extraParts.getAtPoint());
		cv.put(Contract.ExtraParts.COLUMN_NAME_IMAGE, extraParts.getImage());
		cv.put(Contract.ExtraParts.COLUMN_NAME_IMGWIDTH, extraParts.getImgWidth());
		cv.put(Contract.ExtraParts.COLUMN_NAME_IMGHEIGHT, extraParts.getImgHeight());

		long newRowId;

		newRowId = dbHelper.getWritableDatabase().insert(
				Contract.ExtraParts.TABLE_NAME,
				Contract.ExtraParts.COLUMN_NAME_ATPOINT,
				cv
		);


	}
	
	/*
	 * Will return every value from the Table as a Cursor. 
	 * 
	 * @return A Cursor
	 */
	public Cursor query() {
		return null;
	}
	

}
