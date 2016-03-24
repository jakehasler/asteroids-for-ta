package edu.byu.cs.superasteroids.model.world;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.superasteroids.content.Image;
import edu.byu.cs.superasteroids.game.MainContainer;
import edu.byu.cs.superasteroids.model.Placed;
import edu.byu.cs.superasteroids.model.Visible;
import edu.byu.cs.superasteroids.model.asteroid.AsteroidType;

/**
 * Will set all variables of MainBody, call LevelAsteroid's Constructor, and LevelObject's Constructor
 * @author jakehasler
 */
public class Level extends Visible {

	int num;
	String title;
	String hint;
	int width;
	int height;
	String music;
	List<LevelObject> levelObjects;
	List<LevelAsteroid> levelAsteroids;
	List<AsteroidType> asteroids;

	/**
	 * Will Insert all the values from the Table. 
	 * 
	 * @param num Level Number
	 * @param title Title of the Level
	 * @param hint Hint of the Level
	 * @param width Width of Level
	 * @param height Height of Level 
	 */
	public Level(int num, String title, String hint, int width, int height, String music) {
		super(new Image("images/space.bmp", width, height));
		this.num = num;
		this.title = title;
		this.hint = hint;
		this.width = width;
		this.height = height;
		this.music = music;
		levelObjects = new ArrayList<>();
		levelAsteroids = new ArrayList<>();
		asteroids = new ArrayList<>();
	}

	public Level() {}

	public void initAst() {
		int newAstId = 0;
		for(LevelAsteroid levelAsteroid : levelAsteroids) {
			AsteroidType ast = MainContainer.getModel().getAsteroids().get(levelAsteroid.getId());

			for (int i = 0; i < levelAsteroid.getNum(); i++) {
				AsteroidType newAst = new AsteroidType(ast.getName(), ast.getRealImage(), ast.getType(), newAstId);

				newAstId++;
				this.asteroids.add(newAst);
			}
		}
	}

	public void removeAsteroid(int asteroidId) {

		for (int x=0; x<asteroids.size(); x++) {
			if (asteroids.get(x).getId() == asteroidId) {
				asteroids.remove(x);
			}
		}
	}




	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getMusic() {
		return music;
	}

	public List<LevelObject> getLevelObjects() {
		return levelObjects;
	}

	public void addLevelObject(LevelObject levelObject) {
		levelObjects.add(levelObject);
	}
	public void addLevelAsteroid(LevelAsteroid levelAsteroid) {
		levelAsteroids.add(levelAsteroid);
	}

	public void setLevelObjects(List<LevelObject> levelObjects) {
		this.levelObjects = levelObjects;
	}

	public List<LevelAsteroid> getLevelAsteroids() {
		return levelAsteroids;
	}

	public void setLevelAsteroids(List<LevelAsteroid> levelAsteroids) {
		this.levelAsteroids = levelAsteroids;
	}

	public List<AsteroidType> getAsteroids() {
		return asteroids;
	}

	public void setAsteroids(List<AsteroidType> asteroids) {
		this.asteroids = asteroids;
	}

	@Override
	public String toString() {
		return "Level{" +
				"num=" + num +
				", title='" + title + '\'' +
				", hint='" + hint + '\'' +
				", width=" + width +
				", height=" + height +
				", music='" + music + '\'' +
				", levelObjects=" + levelObjects +
				", levelAsteroids=" + levelAsteroids +
				'}';
	}

	public static class LevelAsteroid {

		int num;
		int id;
		int levelNum;

		/**
		 * Will Insert all the values from the Table.
		 *
		 * @param num Level Number
		 * @param id Asteroid ID
		 */
		public LevelAsteroid(int levelNum, int num, int id) {
			this.num = num;
			this.id = id;
			this.levelNum = levelNum;
		}

		public int getNum() {
			return num;
		}

		public int getId() {
			return id;
		}

		public int getLevelNum() {
			return levelNum;
		}

		public void setLevelNum(int levelNum) {
			this.levelNum = levelNum;
		}

		@Override
		public String toString() {
			return "LevelAsteroid{" +
					"num=" + num +
					", id=" + id +
					", levelNum=" + levelNum +
					'}';
		}
	}

	public static class LevelObject extends Placed {

		String pos;
		int objId;
		float scale;
		int levelNum;
		/**
		 * Will Insert all the values from the Table.
		 *
		 * @param pos Position Coordinates
		 * @param objId Hint of the Level
		 * @param scale Scale of the Level
		 */
		public LevelObject(int levelNum, String pos, int objId, float scale) {
			super(new Image("", 0,0), getPointFromString(pos));
			this.pos = pos;
			this.objId = objId;
			this.scale = scale;
			this.levelNum = levelNum;
		}


		public String getPos() {
			return pos;
		}

		public int getObjId() {
			return objId;
		}

		public float getScale() {
			return scale;
		}

		public int getLevelNum() {
			return levelNum;
		}

		public void setLevelNum(int levelNum) {
			this.levelNum = levelNum;
		}

		@Override
		public String toString() {
			return "LevelObject{" +
					"pos='" + pos + '\'' +
					", objId=" + objId +
					", scale=" + scale +
					'}';
		}
	}

}
