package edu.byu.cs.superasteroids.model;


import android.graphics.PointF;

import java.util.Arrays;
import java.util.List;

import edu.byu.cs.superasteroids.content.Image;
import edu.byu.cs.superasteroids.game.MainContainer;

/**
 * For any item that has specific coordinate values
 */
public class Placed extends Visible {

    private PointF position;
    private PointF screenPosition;

    public Placed() {}

    public Placed(Image image, PointF position) {
        super(image);
        this.position = position;
        this.screenPosition = this.getScreenPosition();
    }


    public PointF getScreenPosition() {
        if (MainContainer.getViewport() != null) {
            PointF viewPortPosition = MainContainer.getViewport().getPosition();
            return new PointF(
                    this.position.x - viewPortPosition.x,
                    this.position.y - viewPortPosition.y);
        }
        return new PointF(0,0);
    }


    public static PointF getPointFromString(String pointFString) {
        List<String> pointFStringList = Arrays.asList(pointFString.split(","));
        int x = Integer.parseInt(pointFStringList.get(0));
        int y = Integer.parseInt(pointFStringList.get(1));
        return new PointF(x, y);
    }

    public static PointF getDefaultPosition() {
        return new PointF(0, 0);
    }


    public PointF getPosition() {
        return position;
    }

    public void setPosition(PointF position) {

        this.position = position;
        this.screenPosition = this.getScreenPosition();
    }
}
