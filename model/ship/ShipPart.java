package edu.byu.cs.superasteroids.model.ship;

import android.graphics.Point;
import android.graphics.PointF;

import edu.byu.cs.superasteroids.content.Image;
import edu.byu.cs.superasteroids.model.Placed;

/**
 * Parent class for the extrapart, Cannon, and engine
 */
public class ShipPart extends Placed {

    private PointF attachPoint;
    private float scale;


    /**
     *
     * @param image Image object, calling parent constructor
     * @param position Position, also parent constructor
     * @param attachPoint Point value of attach point
     * @param scale the scale of the actual object, passed in globally
     */
    public ShipPart(Image image, PointF position, PointF attachPoint, float scale) {
        super(image, position);
        this.attachPoint = attachPoint;
        this.scale = scale;
    }

    public PointF getAttachPoint() {
        return attachPoint;
    }

    public void setAttachPoint(PointF attachPoint) {
        this.attachPoint = attachPoint;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

}
