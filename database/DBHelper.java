package edu.byu.cs.superasteroids.database;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

/** Updates and Queries the Database
 *
 * 
 * @author jakehasler
 */
public class DBHelper extends SQLiteOpenHelper {

	public static DBHelper dbHelper;

	public static void init(Context context) {
		System.out.println("Initializing DBHelper");
		dbHelper = new DBHelper(context);
//		dbHelper.onUpgrade(getWriteable(), 1,1);
	}

	public static SQLiteDatabase getReadable() {
		return dbHelper.getReadableDatabase();
	}

	public static SQLiteDatabase getWriteable() {
		return dbHelper.getWritableDatabase();
	}


	// for main
	//DBHelper.init(getBaseContext());

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "database.sqlite";
	public static Context context;

	public DBHelper(Context c) {
		super(c, DATABASE_NAME, null, DATABASE_VERSION);
		context = c;
	 }


	public static DBHelper getThis() {
		return new DBHelper(context);
	}
	
	/**
	 * Will create all tables in the DB
	 * @param db
	 */
	public void onCreate(SQLiteDatabase db) {
		System.out.println("••• INSIDE ONCREATE •••");
		db.execSQL(Contract.CREATE_AT);
		db.execSQL(Contract.CREATE_CAN);
		db.execSQL(Contract.CREATE_ENG);
		db.execSQL(Contract.CREATE_EXT);
		db.execSQL(Contract.CREATE_LEV);
		db.execSQL(Contract.CREATE_LEVAST);
		db.execSQL(Contract.CREATE_LEVOBJ);
		db.execSQL(Contract.CREATE_MB);
		db.execSQL(Contract.CREATE_PC);
		db.execSQL(Contract.CREATE_BO);
	}
	
	/**
	 * If tables exist, this will delete them
	 * @param db
	 */
	public void onUpgrade(SQLiteDatabase db, int one, int two) {
		System.out.println("Onupgrade");
		db.execSQL(Contract.DROP_AT);
		db.execSQL(Contract.DROP_CAN);
		db.execSQL(Contract.DROP_ENG);
		db.execSQL(Contract.DROP_EXT);
		db.execSQL(Contract.DROP_LEV);
		db.execSQL(Contract.DROP_LEVAST);
		db.execSQL(Contract.DROP_LEVOBJ);
		db.execSQL(Contract.DROP_MB);
		db.execSQL(Contract.DROP_PC);
		db.execSQL(Contract.DROP_BO);

		onCreate(db);
	}
	
	
}