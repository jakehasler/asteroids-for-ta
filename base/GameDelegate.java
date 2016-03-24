package edu.byu.cs.superasteroids.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.content.Image;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.game.MainContainer;
import edu.byu.cs.superasteroids.model.MainModel;
import edu.byu.cs.superasteroids.model.asteroid.AsteroidType;
import edu.byu.cs.superasteroids.model.ship.Cannon;
import edu.byu.cs.superasteroids.model.ship.Ship;
import edu.byu.cs.superasteroids.model.world.BackgroundObject;
import edu.byu.cs.superasteroids.model.world.Level;
import edu.byu.cs.superasteroids.model.world.Space;
import edu.byu.cs.superasteroids.model.world.Viewport;
import edu.byu.cs.superasteroids.ship_builder.ShipBuildingController;

/**
 * Created by jakehasler on 3/7/16.
 */
public class GameDelegate implements IGameDelegate {

    private Context context;
    private Space space;
    private MainModel model;
    private Ship ship;
    private Viewport viewport;
    private Level currentLevel;
    private List<Integer> levelImages;
    private List<Integer> bgObjects;
    private List<Integer> asteroids;
    private boolean in;
    int mapX = 50;
    int mapY = 350;
    int mapWidth = 300;
    int mapHeight = 200;


    public GameDelegate(Context context) {
        this.context = context;
        model = MainContainer.getModel();
        viewport = MainContainer.getViewport();
        ship = model.getShip();
        space = new Space(viewport);
        levelImages = new ArrayList<>();
        bgObjects = new ArrayList<>();
        asteroids = new ArrayList<>();
        this.currentLevel = model.getLevels().get(0);
        in = false;

        this.ship.setPosition(new PointF(this.currentLevel.getWidth() / 2f,
                this.currentLevel.getHeight() / 2f));
    }


    @Override
    public void loadContent(ContentManager content) {
        Toast inLoadContent = Toast.makeText(context, "Loading Content in GameDelegate!!", Toast.LENGTH_LONG);
        inLoadContent.show();
//        ShipBuildingController.loadContent(content);
        MainContainer.getViewport().setId(content.loadImage(MainContainer.getViewport().getRealImage().getPath()));
        // System.out.println("model.getShip().getMainBody().getId() = " + model.getShip().getMainBody().getId());
        int imgIds = content.loadImage(space.getRealImage().getPath());
        //System.out.println("imgId = " + imgId);
        MainContainer.spaceImage = imgIds;
        space.setImageIndex(imgIds);

        if(!in) {
            // Load backgrounds
            for(BackgroundObject object : model.getObjects()) {

                bgObjects.add(content.loadImage(object.getRealImage().getPath()));
            }
            for(AsteroidType asteroid : model.getAsteroids()) {
                int imgId = content.loadImage(asteroid.getRealImage().getPath());
                asteroids.add(imgId);
                asteroid.setId(imgId);
            }
            for(Level level : model.getLevels()) {
                levelImages.add(content.loadImage(level.getRealImage().getPath()));
                for(Level.LevelObject levelObject : level.getLevelObjects()) {

                    int id = levelObject.getObjId();
                    int back = bgObjects.get(id);
                    levelObject.setId(back);
                }
            }

        }
        in = true;
       // space.setImageIndex(content.loadImage(space.getRealImage().getPath()));
        ship.setPosition(new PointF(DrawingHelper.getGameViewWidth()/2, DrawingHelper.getGameViewHeight()/2));
    }


    @Override
    public void unloadContent(ContentManager content) {

    }

    @Override
    public void draw() {
        viewport.draw();
        ship.draw(this.viewport.getPosition());

        this.drawMiniMap();
    }

    @Override
    public void update(double elapsedTime) {
        viewport.update(elapsedTime);
        ship.update(elapsedTime);
        this.processCollisions();
    }

    public void processCollisions() {
         // Check ship with asteroids
        this.ship.processCollisions(this.currentLevel.getAsteroids());

        for (AsteroidType a1 : this.currentLevel.getAsteroids()) {
            for (AsteroidType a2 : this.currentLevel.getAsteroids()) {
                if (a1.getId() != a2.getId())
                    a1.processCollision(a2);
            }
        }
    }



    private void drawMiniMap() {
        // Draw box
        DrawingHelper.drawRectangle(
                new RectF(mapX, mapY, mapX + mapWidth, mapY + mapHeight),
                Color.RED,
                100
        );

        // Draw Ship
        DrawingHelper.drawPoint(
                convertToMini(ship.getPosition()),
                2,
                Color.GREEN,
                200
        );

        // Draw Asteroids
//        for (AsteroidType a : MainContainer.getModel().getCurrentLevel().getAsteroids()) {
        for (AsteroidType a : MainContainer.getModel().getAsteroids()) {
            DrawingHelper.drawPoint(
                    convertToMini(a.getPosition()),
                    2,
                    Color.RED,
                    200
            );
        }
    }

    private PointF convertToMini(PointF p) {

        float percX = p.x / 3000;
        float percY = p.y / 3000;

        float miniX = percX * mapWidth;
        float miniY = percY * mapHeight;

        miniX += mapX;
        miniY += mapY;

        return new PointF(miniX, miniY);
    }


}
