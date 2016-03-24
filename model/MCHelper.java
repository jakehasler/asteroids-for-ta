package edu.byu.cs.superasteroids.model;

import android.database.Cursor;
import android.database.DatabaseUtils;

import edu.byu.cs.superasteroids.content.Image;
import edu.byu.cs.superasteroids.database.Contract.Cannon;
import edu.byu.cs.superasteroids.database.Contract.AsteroidType;
import edu.byu.cs.superasteroids.database.DBHelper;
import edu.byu.cs.superasteroids.model.ship.Ship;

/**
 * 
 * Helps Populate the Models
 * Updates and Queries the Database
 *
 * @author jakehasler
 * 
 */
public class MCHelper {

	DBHelper dbHelper;
	Ship theShip;
//	public SQLiteDatabase db = dbHelper.getReadableDatabase();

	public MCHelper(DBHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	/**
	 * Will call each Dao's query and split the cursor into it's respective objects.
	 * The constructor will populate the model class with Data.
	 */
	public void addAll() {
		
	}

	public void queryAll() {
		System.out.println("Inside MCHELPER Query All!");

		getAsteroids();
		getEngines();
		getExtraParts();
		getLevels();
		getLevelObjects();
		getLevelAsteroids();
		getMainBody();
		getPowerCore();
		getShip();

	}

	public void getAsteroids() {
		Cursor c = dbHelper.getReadableDatabase().query(AsteroidType.TABLE_NAME, null, null, null, null, null, null);
		//System.out.println(android.database.DatabaseUtils.dumpCursorToString(c));
		int counter = 0;
		if (c.moveToFirst()){
			while(!c.isAfterLast()){
				String name = c.getString(c.getColumnIndex("name"));
				String img = c.getString(c.getColumnIndex("image"));
				int imgWidth = c.getInt(c.getColumnIndex("imgWidth"));
				int imgHeight = c.getInt(c.getColumnIndex("imgHeight"));
				String type = c.getString(c.getColumnIndex("type"));

				Image image = new Image(img, imgWidth, imgHeight);

				edu.byu.cs.superasteroids.model.asteroid.AsteroidType asteroidType = new edu.byu.cs.superasteroids.model.asteroid.AsteroidType(name, image, type, counter);

				System.out.println(name);
				counter++;
				// do what ever you want here
				c.moveToNext();
			}
		}
		c.close();
	}


	public void getEngines() {

	}

	public void getExtraParts() {

	}

	public void getLevels() {

	}

	public void getLevelObjects() {

	}

	public void getLevelAsteroids() {

	}

	public void getMainBody() {

	}

	public void getPowerCore() {

	}

	public void getShip() {

	}


}
