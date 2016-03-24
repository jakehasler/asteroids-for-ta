package edu.byu.cs.superasteroids.database.dao;

import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.superasteroids.content.Image;
import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DBHelper;
import edu.byu.cs.superasteroids.model.asteroid.AsteroidType;
import edu.byu.cs.superasteroids.model.ship.Cannon;
import edu.byu.cs.superasteroids.model.ship.Engine;
import edu.byu.cs.superasteroids.model.ship.ExtraParts;
import edu.byu.cs.superasteroids.model.ship.PowerCore;
import edu.byu.cs.superasteroids.model.world.BackgroundObject;
import edu.byu.cs.superasteroids.model.world.Level;
import edu.byu.cs.superasteroids.model.ship.MainBody;

public class DaoHelper {

	DBHelper dbHelper;

	DaoAsteroidType dat;
	DaoLevel dl;
	DaoLevelAsteroid dla;
	DaoLevelObject dlo;
	DaoMainBody dmb;
	DaoCannon dc;
	DaoExtraParts dep;
	DaoEngine de;
	DaoPowerCore dpc;
	DaoBGObject dbo;
	JSONArray objects = new JSONArray();

	public DaoHelper(DBHelper dbHelper) {
		this.dbHelper = dbHelper;
	}


	/**
	 * Use the JSON Object and Uses child functions to insert everything into the DB.
	 * 
	 * @param obj The JSON Object to be inserted into the DB
	 */
	public void insertAll(JSONObject obj) {
		System.out.println("DAOHELPER INSERT ALL");
		// System.out.println(obj);

		try {
			JSONObject name = obj.getJSONObject("asteroidsGame");

			JSONArray asteroids = name.getJSONArray("asteroids");
			JSONArray cannons = name.getJSONArray("cannons");
			JSONArray engines = name.getJSONArray("engines");
			JSONArray extraParts = name.getJSONArray("extraParts");
			JSONArray levels = name.getJSONArray("levels");
			JSONArray mainBodies = name.getJSONArray("mainBodies");
			JSONArray objects = name.getJSONArray("objects");
			JSONArray powerCores = name.getJSONArray("powerCores");

			this.doObjects(objects);
			// System.out.println("Inside Insert All try catch block");
			this.doAsteroid(asteroids);
			this.doCannons(cannons);
			this.doExtraParts(extraParts);
			this.doEngine(engines);
			this.doMainBodies(mainBodies);
			// System.out.println("About to call doLevels");
			this.doLevels(levels);
			this.doPowerCores(powerCores);

		}catch (JSONException e) {

		}

	}

	public List<BackgroundObject> getObjects() {
		List<BackgroundObject> objs = new ArrayList<>();

		Cursor c = dbHelper.getReadableDatabase().query(Contract.BackgroundObjects.TABLE_NAME, null, null, null, null, null, null);

		//System.out.println(android.database.DatabaseUtils.dumpCursorToString(c));

		if (c.moveToFirst()){
			while(!c.isAfterLast()){
				String name = c.getString(c.getColumnIndex("image"));

				BackgroundObject object= new BackgroundObject(new Image(name, 0,0));
				objs.add(object);
				// do what ever you want here
				c.moveToNext();
			}
		}
		c.close();

		return objs;
	}

	public List<AsteroidType> getAsteroids() {
		List<AsteroidType> asteroids = new ArrayList<>();

		Cursor c = dbHelper.getReadableDatabase().query(Contract.AsteroidType.TABLE_NAME, null, null, null, null, null, null);

		//System.out.println(android.database.DatabaseUtils.dumpCursorToString(c));
		int counter = 0;
		if (c.moveToFirst()){
			while(!c.isAfterLast()){
				String name = c.getString(c.getColumnIndex("name"));
				String image = c.getString(c.getColumnIndex("image"));
				int width = c.getInt(c.getColumnIndex("imgWidth"));
				int height = c.getInt(c.getColumnIndex("imgHeight"));
				String type= c.getString(c.getColumnIndex("type"));

				Image img = new Image(image, width, height);
				AsteroidType asteroid = new AsteroidType(name, img, type, counter);
				asteroids.add(asteroid);
				// do what ever you want here
				counter++;
				c.moveToNext();
			}
		}
		c.close();

		return asteroids;
	}


	public List<Level> getLevels() {
		List<Level> levels = new ArrayList<>();

		Cursor c = dbHelper.getReadableDatabase().query(Contract.Level.TABLE_NAME, null, null, null, null, null, null);

		//System.out.println(android.database.DatabaseUtils.dumpCursorToString(c));

		if (c.moveToFirst()){
			while(!c.isAfterLast()){
				int num = c.getInt(c.getColumnIndex("number"));
				String title = c.getString(c.getColumnIndex("title"));
				String hint = c.getString(c.getColumnIndex("hint"));
				int width = c.getInt(c.getColumnIndex("width"));
				int height = c.getInt(c.getColumnIndex("height"));
				String music = c.getString(c.getColumnIndex("music"));

				// TODO: Add levelObjs and Stroids to the corresponding levels

				Level newLevel = new Level(num, title, hint, width, height, music);
				levels.add(newLevel);
				// do what ever you want here
				c.moveToNext();
			}
		}
		c.close();

		int counter  = 0;




		return levels;
	}

	public List<Level.LevelObject> getLevelObjects() {
		List <Level.LevelObject> levelObjects = new ArrayList<>();
		Cursor clo = dbHelper.getReadableDatabase().query(Contract.LevelObject.TABLE_NAME, null, null, null, null, null, null);
		if (clo.moveToFirst()){
			while(!clo.isAfterLast()){
				int levelNum = clo.getInt(clo.getColumnIndex("levelNum"));
				String pos = clo.getString(clo.getColumnIndex("position"));
				int num = clo.getInt(clo.getColumnIndex("objectId"));
				Float scale = clo.getFloat(clo.getColumnIndex("scale"));

				Level.LevelObject newLevelObj = new Level.LevelObject(levelNum, pos, num, scale);
				levelObjects.add(newLevelObj);
				// do what ever you want here
				clo.moveToNext();
			}
		}
		clo.close();
		return levelObjects;
	}

	public List<Level.LevelAsteroid> getLevelAsteroids() {
		List <Level.LevelAsteroid> levelAsteroids = new ArrayList<>();
		Cursor cla = dbHelper.getReadableDatabase().query(Contract.LevelAsteroid.TABLE_NAME, null, null, null, null, null, null);
		System.out.println("Getting Level Asteroids");
		if (cla.moveToFirst()){
			while(!cla.isAfterLast()){
				//System.out.println("About to break");
				int levelNum = cla.getInt(cla.getColumnIndex("levelNum"));
				//System.out.println("About to break2");
				int num = cla.getInt(cla.getColumnIndex("number"));
				//System.out.println("About to break3");
				int asteroidId = cla.getInt(cla.getColumnIndex("id"));
				//System.out.println("About to break4");

				Level.LevelAsteroid newLevelObj = new Level.LevelAsteroid(1, num, asteroidId);
				levelAsteroids.add(newLevelObj);
				// do what ever you want here
				cla.moveToNext();
			}
		}
		cla.close();
		return levelAsteroids;
	}

	public List<MainBody> getBodies() {
		List<MainBody> bodies = new ArrayList<>();
		Cursor c = dbHelper.getReadableDatabase().query(Contract.MainBody.TABLE_NAME, null, null, null, null, null, null);
		// System.out.println(android.database.DatabaseUtils.dumpCursorToString(c));

		if (c.moveToFirst()){
			while(!c.isAfterLast()){
				String cannon = c.getString(c.getColumnIndex("cannonAttach"));
				String engine = c.getString(c.getColumnIndex("engineAttach"));
				String extra = c.getString(c.getColumnIndex("extraAttach"));
				String image = c.getString(c.getColumnIndex("image"));
				int imgWidth = c.getInt(c.getColumnIndex("imgWidth"));
				int imgHeight = c.getInt(c.getColumnIndex("imgHeight"));

				MainBody mainBody = new MainBody(cannon, engine, extra, image, imgWidth, imgHeight);
				bodies.add(mainBody);
				// do what ever you want here
				c.moveToNext();
			}
		}
		c.close();

		return bodies;
	}

	public List<Cannon> getCannons() {
		List<Cannon> cannons = new ArrayList<>();
		Cursor c = dbHelper.getReadableDatabase().query(Contract.Cannon.TABLE_NAME, null, null, null, null, null, null);
		//System.out.println(android.database.DatabaseUtils.dumpCursorToString(c));

		if (c.moveToFirst()){
			while(!c.isAfterLast()){
				String ap = c.getString(c.getColumnIndex("attachPoint"));
				String ep = c.getString(c.getColumnIndex("emitPoint"));
				String image = c.getString(c.getColumnIndex("image"));
				int imgWidth = c.getInt(c.getColumnIndex("imgWidth"));
				int imgHeight = c.getInt(c.getColumnIndex("imgHeight"));
				String atImg = c.getString(c.getColumnIndex("attackImage"));
				int atImgWidth = c.getInt(c.getColumnIndex("attackImgWidth"));
				int atImgHeight = c.getInt(c.getColumnIndex("attackImgHeight"));
				String atSound = c.getString(c.getColumnIndex("attackSound"));
				int damage = c.getInt(c.getColumnIndex("damage"));

				Cannon can = new Cannon(ap, ep, image, imgWidth, imgHeight, atImg, atImgWidth, atImgHeight, atSound, damage);
				cannons.add(can);
				// do what ever you want here
				c.moveToNext();
			}
		}
		c.close();

		return cannons;
	}

	public List<ExtraParts> getExtraParts() {
		List<ExtraParts> parts = new ArrayList<>();
		Cursor c = dbHelper.getReadableDatabase().query(Contract.ExtraParts.TABLE_NAME, null, null, null, null, null, null);
		//System.out.println(android.database.DatabaseUtils.dumpCursorToString(c));

		if (c.moveToFirst()){
			while(!c.isAfterLast()){
				String atPoint = c.getString(c.getColumnIndex("attachPoint"));
				String img = c.getString(c.getColumnIndex("image"));
				int imgWidth = c.getInt(c.getColumnIndex("imgWidth"));
				int imgHeight = c.getInt(c.getColumnIndex("imgHeight"));

				edu.byu.cs.superasteroids.model.ship.ExtraParts part = new edu.byu.cs.superasteroids.model.ship.ExtraParts(atPoint, img, imgWidth, imgHeight);
				parts.add(part);
				// System.out.println(atPoint);
				// do what ever you want here
				c.moveToNext();
			}
		}
		c.close();

		return parts;
	}

	public List<Engine> getEngines() {
		List<Engine> engines = new ArrayList<>();
		Cursor c = dbHelper.getReadableDatabase().query(Contract.Engine.TABLE_NAME, null, null, null, null, null, null);
		//System.out.println(android.database.DatabaseUtils.dumpCursorToString(c));

		if (c.moveToFirst()){
			while(!c.isAfterLast()){
				int baseSpeed = c.getInt(c.getColumnIndex("baseSpeed"));
				int baseTurnRate = c.getInt(c.getColumnIndex("baseTurnRate"));
				String atPoint = c.getString(c.getColumnIndex("attachPoint"));
				String image = c.getString(c.getColumnIndex("image"));
				int imgWidth = c.getInt(c.getColumnIndex("imgWidth"));
				int imgHeight = c.getInt(c.getColumnIndex("imgHeight"));

				edu.byu.cs.superasteroids.model.ship.Engine eng = new edu.byu.cs.superasteroids.model.ship.Engine(baseSpeed, baseTurnRate, atPoint, image, imgWidth, imgHeight);
				engines.add(eng);
				//System.out.println(atPoint);
				// do what ever you want here
				c.moveToNext();
			}
		}
		c.close();

		return engines;
	}

	public List<PowerCore> getPowerCores() {
		List<PowerCore> cores = new ArrayList<>();
		Cursor c = dbHelper.getReadableDatabase().query(Contract.PowerCore.TABLE_NAME, null, null, null, null, null, null);

		if (c.moveToFirst()){
			while(!c.isAfterLast()){
				int cBoost = c.getInt(c.getColumnIndex("cannonBoost"));
				int engBoost = c.getInt(c.getColumnIndex("engineBoost"));
				String img = c.getString(c.getColumnIndex("image"));

				edu.byu.cs.superasteroids.model.ship.PowerCore core = new edu.byu.cs.superasteroids.model.ship.PowerCore(cBoost, engBoost, img);
				cores.add(core);
				// do what ever you want here
				c.moveToNext();
			}
		}
		c.close();

		return cores;
	}

	public boolean doObjects(JSONArray array) {
		dbo = new DaoBGObject(dbHelper);
		try {
			for (int i = 0; i < array.length(); i++) {
				String obj = array.getString(i);
				//System.out.println("obj = " + obj);
				BackgroundObject newObj = new BackgroundObject(new Image(obj.toString(),0,0));
				dbo.insert(newObj);
				//objs.add(newObj);
			}
			//System.out.println("array = " + array);
			//System.out.println("objs = " + objs);
		} catch(JSONException e) {
			System.out.println(e.toString());
		}
		return true;
	}


	public boolean doAsteroid(JSONArray array) {
		// System.out.println("In Do Asteroid");
		// System.out.println(array);
		dat = new DaoAsteroidType(dbHelper);
		try {

			for (int i = 0; i < array.length(); i++) {
				JSONObject astName = array.getJSONObject(i);
				String asteroidName = astName.getString("name");
				String image = astName.getString("image");
				int imgWidth = astName.getInt("imageWidth");
				int imgHeight = astName.getInt("imageHeight");
				String type = astName.getString("type");

				Image img = new Image(image, imgWidth, imgHeight);

				AsteroidType stroid = new AsteroidType(asteroidName, img, type, i);

				dat.insert(stroid);
			}


		} catch(JSONException e) {

			System.out.println(e.toString());
			return false;
		}

		return true;
	}


	public boolean doEngine(JSONArray array) {
		// System.out.println("In Do Engine");
		// System.out.println(array);
		de = new DaoEngine(dbHelper);
		try {

			for (int i = 0; i < array.length(); i++) {
				JSONObject engName = array.getJSONObject(i);
				int baseSpeed = engName.getInt("baseSpeed");
				int baseTurnRate = engName.getInt("baseTurnRate");
				String atPoint = engName.getString("attachPoint");
				String image = engName.getString("image");
				int imgWidth = engName.getInt("imageWidth");
				int imgHeight = engName.getInt("imageHeight");

				Engine eng = new Engine(baseSpeed, baseTurnRate, atPoint, image, imgWidth, imgHeight);

				de.insert(eng);
			}


		} catch(JSONException e) {

			System.out.println(e.toString());
			return false;
		}

		return true;
	}

	public boolean doLevels(JSONArray array) {
		// System.out.println("In Levels");
		// TODO: Pass in DB to DaoLevel constructor
		dl = new DaoLevel(dbHelper);
		dlo = new DaoLevelObject(dbHelper);
		dla = new DaoLevelAsteroid(dbHelper);
		try {

			for (int i = 0; i < array.length(); i++) {
				JSONObject level = array.getJSONObject(i);
				int num = level.getInt("number");
				String title = level.getString("title");
				String hint = level.getString("hint");
				// System.out.println("HINT: " + hint);
				int width = level.getInt("width");
				int height = level.getInt("height");
				String music = level.getString("music");
				//System.out.println(level.toString());

				JSONArray levelObjArr = level.getJSONArray("levelObjects");
				// System.out.println("Level Object " + levelObjArr);
				for(int j = 0; j < levelObjArr.length(); j++) {
					JSONObject levelObj = levelObjArr.getJSONObject(j);
					String position = levelObj.getString("position");
					int objId = levelObj.getInt("objectId");
					float scale = Float.parseFloat(levelObj.getString("objectId"));

					Level.LevelObject levObj = new Level.LevelObject(num, position, objId, scale);

					dlo.insert(levObj);
				}

				JSONArray levelAstArr = level.getJSONArray("levelAsteroids");
				// System.out.println("Level Asteroids: " + levelAstArr);
				for(int j = 0; j < levelAstArr.length(); j++) {
					JSONObject levelAst = levelAstArr.getJSONObject(j);
					int num1 = levelAst.getInt("number");
					int astId = levelAst.getInt("asteroidId");

					Level.LevelAsteroid levAsteroid = new Level.LevelAsteroid(num, num1, astId);

					dla.insert(levAsteroid);
				}


				Level newLevel = new Level(num, title, hint, width, height, music);

				dl.insert(newLevel);
			}


		} catch(JSONException e) {

			return false;
		}

		return true;
	}

	public boolean doMainBodies(JSONArray array) {
		// System.out.println("In Main Bodies");
		// TODO: Pass in DB to DaoLevel constructor
		dmb = new DaoMainBody(dbHelper);
		try {

			for (int i = 0; i < array.length(); i++) {
				JSONObject body = array.getJSONObject(i);
				String cannon = body.getString("cannonAttach");
				String engine = body.getString("engineAttach");
				String extra = body.getString("extraAttach");
				String image = body.getString("image");
				int imgWidth = body.getInt("imageWidth");
				int imgHeight = body.getInt("imageHeight");

				MainBody mainBody = new MainBody(cannon, engine, extra, image, imgWidth, imgHeight);

				dmb.insert(mainBody);
			}


		} catch(JSONException e) {

			return false;
		}

		return true;
	}



	public boolean doCannons(JSONArray array) {
		// System.out.println("In Do Cannons");
		// System.out.println(array);
		dc = new DaoCannon(dbHelper);
		try {

			for (int i = 0; i < array.length(); i++) {
				JSONObject cannon = array.getJSONObject(i);
				String ap = cannon.getString("attachPoint");
				String ep = cannon.getString("emitPoint");
				String image = cannon.getString("image");
				String atImg = cannon.getString("attackImage");
				int imgWidth = cannon.getInt("imageWidth");
				int imgHeight = cannon.getInt("imageHeight");
				int atImgWidth = cannon.getInt("attackImageWidth");
				int atImgHeight = cannon.getInt("attackImageHeight");
				String atSound = cannon.getString("attackSound");
				int damage = cannon.getInt("damage");

				Cannon can = new Cannon(ap, ep, image, imgWidth, imgHeight, atImg, atImgWidth, atImgHeight, atSound, damage);

				dc.insert(can);
			}


		} catch(JSONException e) {

			System.out.println(e.toString());
			return false;
		}

		return true;
	}


	public boolean doExtraParts(JSONArray array) {
		// System.out.println("In Do Cannons");
		// System.out.println(array);
		dep = new DaoExtraParts(dbHelper);
		try {

			for (int i = 0; i < array.length(); i++) {
				JSONObject extraPart = array.getJSONObject(i);
				String ap = extraPart.getString("attachPoint");
				String image = extraPart.getString("image");
				int imgWidth = extraPart.getInt("imageWidth");
				int imgHeight = extraPart.getInt("imageHeight");

				ExtraParts extP = new ExtraParts(ap, image, imgWidth, imgHeight);

				dep.insert(extP);
			}


		} catch(JSONException e) {

			System.out.println(e.toString());
			return false;
		}

		return true;
	}

	public boolean doPowerCores(JSONArray array) {
		// System.out.println("In Do Power Cores");
		// System.out.println(array);
		dpc = new DaoPowerCore(dbHelper);
		try {

			for (int i = 0; i < array.length(); i++) {
				JSONObject powerCore = array.getJSONObject(i);
				int cannonBoost = powerCore.getInt("cannonBoost");
				int engineBoost = powerCore.getInt("engineBoost");
				String image = powerCore.getString("image");

				PowerCore powerC = new PowerCore(cannonBoost, engineBoost, image);

				dpc.insert(powerC);
			}


		} catch(JSONException e) {

			System.out.println(e.toString());
			return false;
		}

		return true;
	}
}
