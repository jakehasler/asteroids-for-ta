package edu.byu.cs.superasteroids.database;

import android.provider.BaseColumns;

import edu.byu.cs.superasteroids.model.world.BackgroundObject;

/**
 * Will make string representations of all the classes so that they can be executed as create and drop statements in the DBHelper
 *
 *
 */
public class Contract {
	
	
	public static abstract class AsteroidType implements BaseColumns{

		public static final String TABLE_NAME = "AsteroidType";

		public static final String COLUMN_NAME_NAME = "name";
		public static final String COLUMN_NAME_IMAGE = "image";
		public static final String COLUMN_NAME_IMGWIDTH = "imgWidth";
		public static final String COLUMN_NAME_IMGHEIGHT = "imgHeight";
		public static final String COLUMN_NAME_TYPE = "type";

	}
	
	public abstract class Cannon implements BaseColumns{

		public static final String TABLE_NAME = "Cannon";

		public static final String COLUMN_NAME_ATPOINT = "attachPoint";
		public static final String COLUMN_NAME_EMPOINT = "emitPoint";
		public static final String COLUMN_NAME_IMAGE = "image";
		public static final String COLUMN_NAME_IMGWIDTH = "imgWidth";
		public static final String COLUMN_NAME_IMGHEIGHT = "imgHeight";
		public static final String COLUMN_NAME_ATIMG = "attackImage";
		public static final String COLUMN_NAME_ATIMGHEIGHT = "attackImgHeight";
		public static final String COLUMN_NAME_ATIMGWIDTH = "attackImgWidth";
		public static final String COLUMN_NAME_ATSOUND = "attackSound";
		public static final String COLUMN_NAME_DAMAGE = "damage";

	}
	
	public abstract class Engine implements BaseColumns{

		public static final String TABLE_NAME = "Engine";

		public static final String COLUMN_NAME_BASESPEED = "baseSpeed";
		public static final String COLUMN_NAME_BASETURNRATE = "baseTurnRate";
		public static final String COLUMN_NAME_ATPOINT = "attachPoint";
		public static final String COLUMN_NAME_IMAGE = "image";
		public static final String COLUMN_NAME_IMGWIDTH = "imgWidth";
		public static final String COLUMN_NAME_IMGHEIGHT = "imgHeight";
		
	}
	
	public abstract class ExtraParts implements BaseColumns{

		public static final String TABLE_NAME = "ExtraParts";

		public static final String COLUMN_NAME_ATPOINT = "attachPoint";
		public static final String COLUMN_NAME_IMAGE = "image";
		public static final String COLUMN_NAME_IMGWIDTH = "imgWidth";
		public static final String COLUMN_NAME_IMGHEIGHT = "imgHeight";
		
	}

	public abstract class Level implements BaseColumns{

		public static final String TABLE_NAME = "Level";

		public static final String COLUMN_NAME_NUM = "number";
		public static final String COLUMN_NAME_TITLE = "title";
		public static final String COLUMN_NAME_HINT = "hint";
		public static final String COLUMN_NAME_HEIGHT = "height";
		public static final String COLUMN_NAME_WIDTH = "width";
		public static final String COLUMN_NAME_MUSIC = "music";
	}
	
	public abstract class LevelAsteroid implements BaseColumns{

		public static final String TABLE_NAME = "LevelAsteroid";

		public static final String COLUMN_NAME_LEVELNUM = "levelNum";
		public static final String COLUMN_NAME_NUM = "number";
		public static final String COLUMN_NAME_ID = "id";
	}
	
	public abstract class LevelObject implements BaseColumns{

		public static final String TABLE_NAME = "LevelObject";

		public static final String COLUMN_NAME_LEVELNUM = "levelNum";
		public static final String COLUMN_NAME_POS = "position";
		public static final String COLUMN_NAME_OBJID = "objectId";
		public static final String COLUMN_NAME_SCALE = "scale";
	}
	public abstract class MainBody implements BaseColumns{

		public static final String TABLE_NAME = "MainBody";

		public static final String COLUMN_NAME_CANNONAT = "cannonAttach";
		public static final String COLUMN_NAME_ENGINEAT = "engineAttach";
		public static final String COLUMN_NAME_EXTRAAT = "extraAttach";
		public static final String COLUMN_NAME_IMAGE = "image";
		public static final String COLUMN_NAME_IMGWIDTH = "imgWidth";
		public static final String COLUMN_NAME_IMGHEIGHT = "imgHeight";
	}
	public abstract class PowerCore implements BaseColumns{

		public static final String TABLE_NAME = "PowerCore";

		public static final String COLUMN_NAME_CANNONBOOST = "cannonBoost";
		public static final String COLUMN_NAME_ENGINEBOOST = "engineBoost";
		public static final String COLUMN_NAME_IMAGE = "image";

	}

	public abstract class BackgroundObjects implements BaseColumns{

		public static final String TABLE_NAME = "BGObjects";

		public static final String COLUMN_NAME_IMG = "image";

	}

	public static final String TEXT = " TEXT";
	public static final String INT = " INTEGER";
	public static final String FLOAT = " FLOAT";
	public static final String COMMA = ",";
	public static final String CREATE = "CREATE TABLE ";
	public static final String DROP = "DROP TABLE IF EXISTS ";


	public static final String CREATE_AT = CREATE + AsteroidType.TABLE_NAME + " ("
			+ AsteroidType.COLUMN_NAME_NAME + TEXT + COMMA
			+ AsteroidType.COLUMN_NAME_IMAGE + TEXT + COMMA
			+ AsteroidType.COLUMN_NAME_IMGWIDTH + INT + COMMA
			+ AsteroidType.COLUMN_NAME_IMGHEIGHT + INT + COMMA
			+ AsteroidType.COLUMN_NAME_TYPE + TEXT
			+ " )";

	public static final String CREATE_CAN = CREATE + Cannon.TABLE_NAME + " ("
			+ Cannon.COLUMN_NAME_ATPOINT + TEXT + COMMA
			+ Cannon.COLUMN_NAME_EMPOINT + TEXT + COMMA
			+ Cannon.COLUMN_NAME_IMAGE + TEXT + COMMA
			+ Cannon.COLUMN_NAME_IMGWIDTH + INT + COMMA
			+ Cannon.COLUMN_NAME_IMGHEIGHT + INT + COMMA
			+ Cannon.COLUMN_NAME_ATIMG + TEXT + COMMA
			+ Cannon.COLUMN_NAME_ATIMGWIDTH + INT + COMMA
			+ Cannon.COLUMN_NAME_ATIMGHEIGHT + INT + COMMA
			+ Cannon.COLUMN_NAME_ATSOUND + TEXT + COMMA
			+ Cannon.COLUMN_NAME_DAMAGE + TEXT
			+ " )";

	public static final String CREATE_ENG = CREATE + Engine.TABLE_NAME + " ("
			+ Engine.COLUMN_NAME_BASESPEED + INT + COMMA
			+ Engine.COLUMN_NAME_BASETURNRATE + INT + COMMA
			+ Engine.COLUMN_NAME_ATPOINT + TEXT + COMMA
			+ Engine.COLUMN_NAME_IMAGE + TEXT + COMMA
			+ Engine.COLUMN_NAME_IMGWIDTH + INT + COMMA
			+ Engine.COLUMN_NAME_IMGHEIGHT + INT
			+ " )";

	public static final String CREATE_EXT = CREATE + ExtraParts.TABLE_NAME + " ("
			+ ExtraParts.COLUMN_NAME_ATPOINT + TEXT + COMMA
			+ ExtraParts.COLUMN_NAME_IMAGE + TEXT + COMMA
			+ ExtraParts.COLUMN_NAME_IMGWIDTH + INT + COMMA
			+ ExtraParts.COLUMN_NAME_IMGHEIGHT + INT
			+ " )";

	public static final String CREATE_LEV = CREATE + Level.TABLE_NAME + " ("
			+ Level.COLUMN_NAME_NUM + INT + COMMA
			+ Level.COLUMN_NAME_TITLE + TEXT + COMMA
			+ Level.COLUMN_NAME_HINT + TEXT + COMMA
			+ Level.COLUMN_NAME_WIDTH + INT + COMMA
			+ Level.COLUMN_NAME_HEIGHT + INT + COMMA
			+ Level.COLUMN_NAME_MUSIC + TEXT
			+ " )";

	public static final String CREATE_LEVAST = CREATE + LevelAsteroid.TABLE_NAME + " ("
			+ LevelAsteroid.COLUMN_NAME_LEVELNUM + INT + COMMA
			+ LevelAsteroid.COLUMN_NAME_NUM + INT + COMMA
			+ LevelAsteroid.COLUMN_NAME_ID + INT
			+ " )";

	public static final String CREATE_LEVOBJ = CREATE + LevelObject.TABLE_NAME + " ("
			+ LevelAsteroid.COLUMN_NAME_LEVELNUM + INT + COMMA
			+ LevelObject.COLUMN_NAME_POS + TEXT + COMMA
			+ LevelObject.COLUMN_NAME_OBJID + INT + COMMA
			+ LevelObject.COLUMN_NAME_SCALE + FLOAT
			+ " )";

	public static final String CREATE_MB = CREATE + MainBody.TABLE_NAME + " ("
			+ MainBody.COLUMN_NAME_CANNONAT + TEXT + COMMA
			+ MainBody.COLUMN_NAME_ENGINEAT + TEXT + COMMA
			+ MainBody.COLUMN_NAME_EXTRAAT + TEXT + COMMA
			+ MainBody.COLUMN_NAME_IMAGE + TEXT + COMMA
			+ MainBody.COLUMN_NAME_IMGWIDTH + INT + COMMA
			+ MainBody.COLUMN_NAME_IMGHEIGHT + INT
			+ " )";

	public static final String CREATE_PC = CREATE + PowerCore.TABLE_NAME + " ("
			+ PowerCore.COLUMN_NAME_CANNONBOOST + INT + COMMA
			+ PowerCore.COLUMN_NAME_ENGINEBOOST + INT + COMMA
			+ PowerCore.COLUMN_NAME_IMAGE + TEXT
			+ " )";

	public static final String CREATE_BO = CREATE + BackgroundObjects.TABLE_NAME + " ("
			+ BackgroundObjects.COLUMN_NAME_IMG + TEXT
			+ " )";

	public static final String DROP_AT = DROP + AsteroidType.TABLE_NAME;
	public static final String DROP_CAN = DROP + Cannon.TABLE_NAME;
	public static final String DROP_ENG = DROP + Engine.TABLE_NAME;
	public static final String DROP_EXT = DROP + ExtraParts.TABLE_NAME;
	public static final String DROP_LEV = DROP + Level.TABLE_NAME;
	public static final String DROP_LEVAST = DROP + LevelAsteroid.TABLE_NAME;
	public static final String DROP_LEVOBJ = DROP + LevelObject.TABLE_NAME;
	public static final String DROP_MB = DROP + MainBody.TABLE_NAME;
	public static final String DROP_PC = DROP + PowerCore.TABLE_NAME;
	public static final String DROP_BO = DROP + BackgroundObjects.TABLE_NAME;
}
