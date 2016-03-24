package edu.byu.cs.superasteroids.model;

import android.graphics.PointF;

import org.json.JSONObject;

import java.util.List;

import edu.byu.cs.superasteroids.content.Image;
import edu.byu.cs.superasteroids.database.DBHelper;
import edu.byu.cs.superasteroids.database.dao.DaoHelper;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.model.asteroid.AsteroidType;
import edu.byu.cs.superasteroids.model.ship.Cannon;
import edu.byu.cs.superasteroids.model.ship.Engine;
import edu.byu.cs.superasteroids.model.ship.ExtraParts;
import edu.byu.cs.superasteroids.model.ship.MainBody;
import edu.byu.cs.superasteroids.model.ship.PowerCore;
import edu.byu.cs.superasteroids.model.ship.Ship;
import edu.byu.cs.superasteroids.model.ship.Shots;
import edu.byu.cs.superasteroids.model.world.BackgroundObject;
import edu.byu.cs.superasteroids.model.world.Level;
import edu.byu.cs.superasteroids.model.world.Viewport;

/**
 * The Class to hold all of the objects once everything has been queried from the DB
 */
public class MainModel {

    private DBHelper dbHelper;
    private DaoHelper daoHelper;
    private Ship ship;
    private float scaleFactor;
    private boolean initialized;
    private Level currentLevel;
    private Viewport viewport;

    private List<MainBody> mainBodies;
    private List<Cannon> cannons;
    private List<Shots> shots;
    private List<ExtraParts> extraParts;
    private List<Engine> engines;
    private List<PowerCore> powerCores;
    private List<Level> levels;
    private List<Level.LevelObject> levelObjects;
    private List<Level.LevelAsteroid> levelAsteroids;
    private List<AsteroidType> asteroids;
    private List<BackgroundObject> objects;
    public static final float AST_SCALE = 3f;

    /**
     * Uses the Main Singleton DB to get items from it.
     * @param dbHelper
     */
    public MainModel(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
        //dbHelper.onUpgrade(dbHelper.getWriteable(), 1,1);
        //ship = new Ship();
        ship = new Ship(new Image("",150,150), new PointF(0,0), 0, 0);
        this.scaleFactor = .4f;
        daoHelper = new DaoHelper(dbHelper);
    }

    /**
     * Runs through all of the DAO Funcions to build the data structure for the ship parts.
     * @param initialized Determines if it has been initialized with or without data.
     */
    public void initModel(boolean initialized) {
        asteroids = daoHelper.getAsteroids();
        mainBodies = daoHelper.getBodies();
        cannons = daoHelper.getCannons();
        extraParts = daoHelper.getExtraParts();
        engines = daoHelper.getEngines();
        powerCores = daoHelper.getPowerCores();
        levels = daoHelper.getLevels();
        objects = daoHelper.getObjects();

        levelObjects = daoHelper.getLevelObjects();
        levelAsteroids = daoHelper.getLevelAsteroids();
       // viewport = new Viewport(new Image("images/space.bmp",3000,3000), new PointF(0,0));
        viewport = new Viewport(
                new Image("", DrawingHelper.getGameViewWidth(),DrawingHelper.getGameViewHeight()),
                new PointF(DrawingHelper.getGameViewWidth() / 2,DrawingHelper.getGameViewHeight() / 2)
        );
        this.initialized = initialized;
        levels = addLevelObjectsAndAsteroidsToLevels(levels, levelObjects, levelAsteroids);

        if(levels.size() != 0){
            currentLevel = levels.get(0);
            currentLevel.initAst();
        }
        System.out.println("Levels: " + levels.toString());
        System.out.println("\n\nMODEL INITIALIZED!!!!!!!!!!");
    }

    // TODO: getCurrentShip


    private static List<Level> addLevelObjectsAndAsteroidsToLevels(List<Level> levels,
                                                                   List<Level.LevelObject> levelObjects,
                                                                   List<Level.LevelAsteroid> levelAsteroids) {
        // Add all these in if their level numbers match up
        for(Level level : levels) {
            for(Level.LevelObject levelObject : levelObjects) {
                if (level.getNum() == levelObject.getLevelNum()) {
                    level.addLevelObject(levelObject);
                }
            }

            for(Level.LevelAsteroid levelAsteroid : levelAsteroids) {
                if (level.getNum() == levelAsteroid.getLevelNum()) {
                    level.addLevelAsteroid(levelAsteroid);
                }
            }
        }

        return levels;
    }


    public DBHelper getDbHelper() {
        return dbHelper;
    }

    public void setDbHelper(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public DaoHelper getDaoHelper() {
        return daoHelper;
    }

    public void setDaoHelper(DaoHelper daoHelper) {
        this.daoHelper = daoHelper;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public float getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(float scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public List<MainBody> getMainBodies() {
        return mainBodies;
    }

    public void setMainBodies(List<MainBody> mainBodies) {
        this.mainBodies = mainBodies;
    }

    public List<Cannon> getCannons() {
        return cannons;
    }

    public void setCannons(List<Cannon> cannons) {
        this.cannons = cannons;
    }

    public List<ExtraParts> getExtraParts() {
        return extraParts;
    }

    public void setExtraParts(List<ExtraParts> extraParts) {
        this.extraParts = extraParts;
    }

    public List<Engine> getEngines() {
        return engines;
    }

    public void setEngines(List<Engine> engines) {
        this.engines = engines;
    }

    public List<PowerCore> getPowerCores() {
        return powerCores;
    }

    public void setPowerCores(List<PowerCore> powerCores) {
        this.powerCores = powerCores;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public List<AsteroidType> getAsteroids() {
        return asteroids;
    }

    public void setAsteroids(List<AsteroidType> asteroids) {
        this.asteroids = asteroids;
    }

    public List<Level.LevelObject> getLevelsObjects() {
        return levelObjects;
    }

    public void setLevelsObjects(List<Level.LevelObject> levelsObjects) {
        this.levelObjects = levelsObjects;
    }

    public List<BackgroundObject> getObjects() {
        return objects;
    }

    public void setObjects(List<BackgroundObject> objects) {
        this.objects = objects;
    }

    @Override
    public String toString() {
        return "MainModel{" +
                "mainBodies=" + mainBodies +
                ", cannons=" + cannons +
                ", extraParts=" + extraParts +
                ", engines=" + engines +
                ", powerCores=" + powerCores +
                ", levels=" + levels +
                '}';
    }
}
