package edu.byu.cs.superasteroids.database.dao;

import android.content.ContentValues;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DBHelper;
import edu.byu.cs.superasteroids.model.ship.PowerCore;
import edu.byu.cs.superasteroids.model.world.BackgroundObject;

/**
 * Created by jakehasler on 3/11/16.
 */
public class DaoBGObject {

    DBHelper dbHelper;

    public DaoBGObject(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    /**
     * Will Insert all the values from the Table.
     *
     * @param object PowerCore object to be inserted into the table
     */
    public void insert(BackgroundObject object) {

        ContentValues cv = new ContentValues();

        cv.put(Contract.BackgroundObjects.COLUMN_NAME_IMG, object.getRealImage().getPath());

        long newRowId;

        newRowId = dbHelper.getWritableDatabase().insert(
                Contract.BackgroundObjects.TABLE_NAME,
                Contract.BackgroundObjects.COLUMN_NAME_IMG,
                cv
        );

    }
}
