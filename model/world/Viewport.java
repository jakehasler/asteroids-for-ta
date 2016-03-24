package edu.byu.cs.superasteroids.model.world;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import edu.byu.cs.superasteroids.content.Image;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.game.MainContainer;
import edu.byu.cs.superasteroids.model.Placed;
import edu.byu.cs.superasteroids.model.ship.Ship;

/**
 * Determines the size and location of what is actually seen on the screen. 
 * 
 * @author jakehasler
 *
 */
public class Viewport extends Placed{

    private PointF dimensions;
    private Ship ship;
    private Space space;

    public Viewport(Image image, PointF position) {
        super(image, position);
        ship = MainContainer.getModel().getShip();
        dimensions = new PointF(DrawingHelper.getGameViewWidth(), DrawingHelper.getGameViewHeight());
        space = new Space(this);
    }

    public void draw() {
        space.draw();
    }

    public void update(double time) {
        space.update(time);
        updatePosition();
        updateSize();
    }

    private void updatePosition() {
        if(this.ship.getPosition() != null) {

            // Move our viewport position
            float moveX = this.getPosition().x;
            float moveY = this.getPosition().y;

            float newX = this.ship.getPosition().x - (DrawingHelper.getGameViewWidth() / 2);
            float newY = this.ship.getPosition().y - (DrawingHelper.getGameViewHeight() / 2);

            if (isInBounds(new PointF(newX, moveY))) {
                moveX = newX;
            }

            if (isInBounds(new PointF(moveX, newY))) {
                moveY = newY;
            }

            PointF newPosition = new PointF(moveX, moveY);

            // Move if in bounds
            if(isInBounds(newPosition)) {
                setPosition(newPosition);
            }
            else {
                System.out.println("uh, broken man");
            }
        }
    }

    private boolean isInBounds(PointF newPosition) {
        RectF bounds = new RectF(
                newPosition.x,
                newPosition.y,
                newPosition.x + DrawingHelper.getGameViewWidth(),
                newPosition.y + DrawingHelper.getGameViewHeight()
        );

        if (this.space.getRectangle().contains(bounds)) {
            return true;
        }
        else {
            return false;
        }
    }

    private void updateSize() {
        getRealImage().setWidth(DrawingHelper.getGameViewWidth());
        getRealImage().setHeight(DrawingHelper.getGameViewHeight());
        Rect view = new Rect(
                (int) getPosition().x,
                (int) getPosition().y,
                (int) getPosition().x +
                        getRealImage().getWidth(),
                (int) getPosition().y +
                        getRealImage().getHeight()
        );
    }


    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public PointF getDimensions() {
        return dimensions;
    }

    public void setDimensions(PointF dimensions) {
        this.dimensions = dimensions;
    }
}
