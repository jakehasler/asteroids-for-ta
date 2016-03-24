package edu.byu.cs.superasteroids.model.world;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.content.Image;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.game.MainContainer;
import edu.byu.cs.superasteroids.model.Moves;
import edu.byu.cs.superasteroids.model.asteroid.AsteroidType;
import edu.byu.cs.superasteroids.model.world.Level;
import edu.byu.cs.superasteroids.model.Visible;

/**
 * Created by jakehasler on 3/5/16.
 */
public class Space extends Moves {

    private int imageIndex;
    private Image image;
    private Viewport viewport;
    private Level currentLevel;

    public Space(Viewport viewport) {
        super(new Image("images/space.bmp", 3000,3000), new PointF(0,0), new RectF(), 0, 0);
        // image = new Image("images/space.bmp", 3000, 3000);
        this.viewport = viewport;
        this.imageIndex = -1;
    }

    public Space() {
        super(new Image("", 0, 0), new PointF(0, 0), new RectF(), 0, 0);
        this.imageIndex = -1;
    }

    public void draw() {
        drawSpace();
        drawBackgroundImages();
        drawAsteroids();
    }

    public void drawSpace() {
        //System.out.println("Space Image Getting Drawn!:  " + getRealImage().getPath());
        PointF scale = new PointF(2048f/getRealImage().getWidth(), 2048f/getRealImage().getHeight());
        if(viewport != null) {
            Rect src = new Rect(
                    (int) (viewport.getPosition().x * scale.x),
                    (int) (viewport.getPosition().y * scale.y),
                    (int) ((viewport.getPosition().x + DrawingHelper.getGameViewWidth()) * scale.x),
                    (int) ((viewport.getPosition().y + DrawingHelper.getGameViewHeight()) * scale.y)
            );

            // TODO: Fix hard coded space image!
            DrawingHelper.drawImage(MainContainer.spaceImage, src, null);
        }
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }


    public void updateAsteroids(double elapsedTime) {
        if (this.currentLevel != null) {
            for (AsteroidType asteroid : this.currentLevel.getAsteroids()) {
                asteroid.update(elapsedTime);
            }
        }
    }

    private void drawBackgroundImages() {
        if(this.currentLevel != null && this.currentLevel.getLevelObjects() != null) {
            List<Level.LevelObject> levelObjects = currentLevel.getLevelObjects();

            // TODO: Not sure if the id is correct
            for (Level.LevelObject levelObject : levelObjects) {
                DrawingHelper.drawImage(
                        (int)levelObject.getId(),
                        levelObject.getPosition().x - viewport.getPosition().x,
                        levelObject.getPosition().y - viewport.getPosition().y,
                        90,
                        levelObject.getScale(),
                        levelObject.getScale(),
                        200
                );
            }
        }
    }

    // Draw asteroids
    public void drawAsteroids() {
        if (this.currentLevel != null) {

            ArrayList<Integer> idsToRemove = new ArrayList<>();
            for(AsteroidType asteroid : this.currentLevel.getAsteroids()) {
                PointF viewportPosition = this.viewport.getPosition();
                asteroid.draw(viewportPosition);
                if (!asteroid.isAlive()) {
                    idsToRemove.add(asteroid.getAstId());
                }
            }

            for (Integer i : idsToRemove) {
                this.currentLevel.removeAsteroid(i);
            }
        }
    }


    public void update(double time) {
        if(MainContainer.getModel().getCurrentLevel() != null) {
            currentLevel = MainContainer.getModel().getCurrentLevel();
            getRealImage().setWidth(
                    currentLevel.getRealImage().getWidth()
            );
            getRealImage().setHeight(
                    currentLevel.getRealImage().getHeight()
            );
            this.updateAsteroids(time);
        }
        updateRectangle();
    }

    private void updateRectangle() {
        setRectangle(
                new RectF(
                        0,
                        0,
                        getRealImage().getWidth(),
                        getRealImage().getHeight()
                )
        );

    }

    public boolean fits(RectF rect) {
        if(getRectangle().contains(rect)) {
            return true;
        }
        else return false;
    }

    public boolean fits(PointF pointF) {
        if(getRectangle().contains(pointF.x, pointF.y)) {
            return true;
        }
        else {
            return false;
        }
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }
}
