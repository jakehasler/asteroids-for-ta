package edu.byu.cs.superasteroids.model.world;


import android.graphics.Point;
import android.graphics.PointF;

import java.util.List;

import edu.byu.cs.superasteroids.content.Image;
import edu.byu.cs.superasteroids.model.Placed;

/**
 * Holds all the objects of the background.
 * @author jakehasler
 *
 */
public class Background extends Placed {

    private Space space;
    private List<Level.LevelObject> levelObjects;

    public Background(Image image, PointF position, Space space, List<Level.LevelObject> levelObjects) {
        super(image, position);
        this.space = space;
        this.levelObjects = levelObjects;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public List<Level.LevelObject> getLevelObjects() {
        return levelObjects;
    }

    public void setLevelObjects(List<Level.LevelObject> levelObjects) {
        this.levelObjects = levelObjects;
    }
}
