package edu.byu.cs.superasteroids.game;

import android.content.Context;
import android.graphics.PointF;

import edu.byu.cs.superasteroids.content.Image;
import edu.byu.cs.superasteroids.database.DBHelper;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.model.MainModel;
import edu.byu.cs.superasteroids.model.asteroid.AsteroidType;
import edu.byu.cs.superasteroids.model.world.Viewport;

/**
 * Holds the DB and the main model throughout the course of the game.
 */
public class MainContainer {

//    private static DBHelper dbHelper;
    private static MainModel model;
    private static DBHelper dbHelper;
    public static Viewport viewport;
    public static int spaceImage;

    /**
     * Stores the DB Helper, and then initializes the Main Model with it.
     * @param dbHelp
     */
    public static void init(DBHelper dbHelp) {
        dbHelper = dbHelp;
        model = new MainModel(dbHelper);
        viewport = new Viewport(new Image("", DrawingHelper.getGameViewWidth(), DrawingHelper.getGameViewWidth()), new PointF(DrawingHelper.getGameViewWidth()/2, DrawingHelper.getGameViewHeight()/2));
    }
//
    public static DBHelper getDbHelper() {
        return dbHelper;
    }

    public static void setDbHelper(DBHelper dbHelper) {
        MainContainer.dbHelper = dbHelper;
    }

    public static MainModel getModel() {
        return model;
    }

    public static void setModel(MainModel model) {
        MainContainer.model = model;
    }

    public static Viewport getViewport() {
        return viewport;
    }
}
