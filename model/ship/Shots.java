package edu.byu.cs.superasteroids.model.ship;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;

import edu.byu.cs.superasteroids.content.Image;
import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.game.MainContainer;
import edu.byu.cs.superasteroids.model.Moves;
import edu.byu.cs.superasteroids.model.asteroid.AsteroidType;

/**
 * Created by jakehasler on 3/8/16.
 */
public class Shots extends Moves {

    private int power;
    private boolean dead;
    private int imgId;

    public Shots(Image image, PointF position, int speed, int direction, int power, int imgId) {
        super(image, position, new RectF(), speed, direction);
        this.power = power;
        this.dead = false;
        this.imgId = imgId;
        generateRectangle();
    }

    public void hit(AsteroidType asteroid) {
        asteroid.getHit(1);
        this.dead = true;
    }

    public void draw() {
        DrawingHelper.drawImage(
                imgId,
                getPosition().x,
                getPosition().y,
                (float) getRotation(),
                MainContainer.getModel().getScaleFactor(),
                MainContainer.getModel().getScaleFactor(),
                255
        );

        DrawingHelper.drawRectangle(
                getRectangle(),
                Color.MAGENTA,
                255
        );
    }

    public void update(double time) {
        GraphicsUtils.MoveObjectResult result =
                GraphicsUtils.moveObject(
                        getPosition(),
                        getRectangle(),
                        getSpeed(),
                        GraphicsUtils.degreesToRadians(
                                getRotation() - 90
                        ),
                        time
                );

        if(!MainContainer.getViewport().getSpace().fits(getPosition())) {
            dead = true;
        }
        else {
            setPosition(result.getNewObjPosition());
            setRectangle(result.getNewObjBounds());
        }
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
