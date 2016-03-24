package edu.byu.cs.superasteroids.model;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import edu.byu.cs.superasteroids.content.Image;
import edu.byu.cs.superasteroids.game.MainContainer;

/**
 * Created by jakehasler on 3/4/16.
 */
public class Moves extends Placed {

    private float speed;
    private int rotation;
    private RectF rectangle;
    private RectF screenRectangle;

    /**
     *
     * @param image Image object
     * @param position Position Value
     * @param speed Speed of the moving object
     * @param rotation The direction of the moving object
     */
    public Moves(Image image, PointF position, RectF rectangle, int speed, int rotation) {
        super(image, position);
        this.speed = speed;
        this.rotation = rotation;
        this.rectangle = rectangle;
        initRect();
    }

    private void initRect() {
        rectangle = new RectF(
            getPosition().x - (getRealImage().getWidth() / 2),
            getPosition().y - (getRealImage().getHeight() / 2),
            getPosition().x + (getRealImage().getWidth() / 2),
            getPosition().y + (getRealImage().getWidth() / 2)
        );
    }

    public void generateRectangle() {
        int lHeight = this.getRealImage().getHeight();
        int lWidth = this.getRealImage().getWidth();
        int small = Math.min(lHeight, lWidth);

        this.rectangle = new RectF(
                getPosition().x - (small / 2),
                getPosition().y - (small / 2),
                getPosition().x + (small / 2),
                getPosition().y + (small / 2));

        this.generateScreenRectangle();
    }


    public void generateScreenRectangle() {

        if (MainContainer.getViewport() != null) {
            PointF viewPortPos = MainContainer.getViewport().getPosition();

            this.screenRectangle = new RectF(
                    this.rectangle.left - viewPortPos.x,
                    this.rectangle.top - viewPortPos.y,
                    this.rectangle.right - viewPortPos.x,
                    this.rectangle.bottom - viewPortPos.y);
        } else {
            this.screenRectangle = new RectF(0, 0, 0, 0);
        }
    }

    public RectF getScreenRectangle() {
        this.generateRectangle();
        return screenRectangle;
    }

    public void setScreenRectangle(RectF screenRectangle) {
        this.screenRectangle = screenRectangle;
    }

    public RectF getRectangle() {
        this.generateRectangle();
        return rectangle;

    }

    public void setRectangle(RectF rectangle) {
        this.rectangle = rectangle;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int direction) {
        this.rotation = rotation;
    }
}
