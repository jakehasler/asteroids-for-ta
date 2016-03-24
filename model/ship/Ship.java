package edu.byu.cs.superasteroids.model.ship;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.content.Image;
import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.game.InputManager;
import edu.byu.cs.superasteroids.game.MainContainer;
import edu.byu.cs.superasteroids.model.MainModel;
import edu.byu.cs.superasteroids.model.Moves;
import edu.byu.cs.superasteroids.model.Placed;
import edu.byu.cs.superasteroids.model.asteroid.AsteroidType;
import edu.byu.cs.superasteroids.model.world.Viewport;

/**
 * Holds all of the elements of the ship.
 * @author jakehasler
 *
 */
public class Ship extends Moves {

    private MainModel model;
    private MainBody mainBody;
    private Cannon cannon;
    private ExtraParts extraParts;
    private Engine engine;
    private PowerCore powerCore;
    private float speed;
    private float sbScale = (float) .4;
    ContentManager manager;
    private int direction = 0;
    private float rotationDegrees;

    private PointF shipCenter;
    private float shipWidth = 0;
    private float shipHeight = 0;

    private PointF cannonOffset;
    private boolean safeMode;
    private List<Shots> shots;

    private boolean isHit;
    private int hitCounter;
    private int HITTIME = 80;

    /**
     *
     * @param image Calls parent constructor
     * @param position
     * @param speed
     * @param direction
     */
    public Ship(Image image, PointF position, int speed, int direction) {
        super(image, position, new RectF(), speed, direction);
        model = MainContainer.getModel();
        mainBody = null;
        cannon = null;
        extraParts = null;
        engine = null;
        powerCore = null;
        shots = new ArrayList<>();
//        position = new PointF(DrawingHelper.getGameViewWidth(), DrawingHelper.getGameViewHeight());

        this.isHit = false;
        this.hitCounter = this.HITTIME;
    }

//    /**
//     * Empty contructor for the ship. Used more often than standard constructor.
//     */
//    public Ship() {
//        super(new Image("", 0, 0), new PointF(0, 0), new RectF(),  0, 0);
//        shots = new ArrayList<>();
//        rotationDegrees = 0;
//        speed = (float)300;
////        position = new PointF(DrawingHelper.getGameViewWidth(), DrawingHelper.getGameViewHeight());
//    }


    public void drawMainBody() {
        DrawingHelper.drawImage(mainBody.getRealImage().getId(), getViewPosition().x, getViewPosition().y, rotationDegrees, sbScale, sbScale, 255);
    }

    public void drawEngine() {
        PointF partCenterPoint = getCenterPoint(engine);
        DrawingHelper.drawImage(engine.getRealImage().getId(), partCenterPoint.x, partCenterPoint.y, rotationDegrees, sbScale, sbScale, 255);
    }

    public void drawCannon() {
        PointF partCenterPoint = getCenterPoint(cannon);
        DrawingHelper.drawImage(cannon.getRealImage().getId(), partCenterPoint.x, partCenterPoint.y, rotationDegrees, sbScale, sbScale, 255);
    }

    public void drawExtraPart() {
        PointF partCenterPoint = getCenterPoint(extraParts);
        DrawingHelper.drawImage(extraParts.getRealImage().getId(), partCenterPoint.x, partCenterPoint.y, rotationDegrees, sbScale, sbScale, 255);
    }

    private void drawShots() {
        List<Shots> deadList = new ArrayList<>();
        for(Shots shot : shots) {
            if(shot.isDead()) {
                deadList.add(shot);
            }
            shot.draw();
        }
        shots.removeAll(deadList);
    }


    //@Override
    public void draw(PointF viewPortPosition) {
        drawMainBody();
        drawEngine();
        drawCannon();
        drawExtraPart();

        drawShots();

        if(getRectangle() != null) {
            DrawingHelper.drawRectangle(getRectangle(), Color.RED, 255);
        }

        if (this.isHit) {
            this.hitCounter--; // remove countdown
            DrawingHelper.drawFilledCircle(this.getScreenPosition(), 120, Color.RED, 100);

            if (this.hitCounter < 0) {
                this.isHit = false;
            }
        }
    }


    public void update(double time) {
        generateRectangle();

        if(InputManager.movePoint != null) {
            updateRotation();
        }
        updatePosition(time);
        calculateSpeed();

        if(InputManager.firePressed) {
            shoot();
        }

        updateShots(time);
    }

    public void takeDamage() {
        this.isHit = true;
        this.hitCounter = this.HITTIME;
    }

    public void processCollisions(List<AsteroidType> asteroids) {

        if (this.getScreenRectangle() != null) {

            // Astroid with ship
            for (AsteroidType a : asteroids) {
                if (GraphicsUtils.isCollision(a.getScreenRectangle(), this.getScreenRectangle())) {
                    this.takeDamage();
                }

                // Astroid with projectile
                for (Shots p : shots) {
                    if (GraphicsUtils.isCollision(a.getScreenRectangle(), p.getRectangle())) {
                        p.hit(a);
                    }
                }
            }
        }
    }

    private void shoot() {

        try {
            int shotSound = ContentManager.getInstance().loadSound(cannon.getAtSound());
            ContentManager.getInstance().playSound(shotSound, .2f, 1);
        } catch(Exception e) {
            System.out.println("Error Reading sound = " + e);
        }

        Shots shot = new Shots(
                new Image(cannon.getAtImg(), cannon.getAtImgWidth(), cannon.getAtImgHeight()),
                getEmitPoint(),
                1000,
                (int)rotationDegrees,
                cannon.getDamage(),
                cannon.atImgId
        );
        shots.add(shot);
    }

    private void updateShots(double time) {
        for(Shots shot : shots) {
            shot.update(time);
        }
    }

    private boolean isInBounds(RectF bounds) {
        if(MainContainer.getViewport().getSpace().getRectangle().contains(bounds)) {
            return true;
        }
        else {
            return false;
        }
    }

    private void updatePosition(double time) {
        GraphicsUtils.MoveObjectResult result =
                GraphicsUtils.moveObject(
                        getPosition(),
                        getRectangle(),
                        getSpeed(),
                        GraphicsUtils.degreesToRadians(
                                rotationDegrees - 90
                        ),
                        time
                );

        if(isInBounds(result.getNewObjBounds())) {

            this.setPosition(result.getNewObjPosition());
            this.setRectangle(result.getNewObjBounds());
        }
    }

    private void calculateSpeed() {
        if(InputManager.movePoint != null && getSpeed() < 500) {
            setSpeed(getSpeed() + 15f);
        }
        else if(InputManager.movePoint == null && getSpeed() > 0) {
            setSpeed(getSpeed() - 15f);
        }
    }


    private void updateRotation() {
        PointF difference = new PointF(
                InputManager.movePoint.x - getViewPosition().x,
                InputManager.movePoint.y - getViewPosition().y
        );

        double rotation = 90;

        if(difference.x != 0 && difference.y != 0) {
            rotation = Math.atan2(difference.y, difference.x);
        }
        else if(difference.x != 0) {
            rotation = ((difference.x > 0) ? 0 : Math.PI);
        }
        else if(difference.y != 0) {
            rotation = ((difference.y > 0) ? GraphicsUtils.HALF_PI : GraphicsUtils.THREE_HALF_PI);
        }

        rotationDegrees = (float) GraphicsUtils.radiansToDegrees(rotation) + 90;
    }


    public MainBody getMainBody() {
        return mainBody;
    }

    public void setMainBody(MainBody mainBody) {
        this.mainBody = mainBody;
    }

    public Cannon getCannon() {
        return cannon;
    }

    public void setCannon(Cannon cannon) {
        this.cannon = cannon;
    }

    public ExtraParts getExtraParts() {
        return extraParts;
    }

    public void setExtraParts(ExtraParts extraParts) {
        this.extraParts = extraParts;
    }

    public Engine getEngine() {
        return engine;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public PowerCore getPowerCore() {
        return powerCore;
    }

    public void setPowerCore(PowerCore powerCore) {
        this.powerCore = powerCore;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }


    private PointF getCenterPoint(ShipPart part) {
        if(mainBody.getId() < 0) {
            return getPosition();
        }

        PointF mainBodyAttachPoint = getMainBodyAttachPoint(part);
        //System.out.println("mainBodyAttachPoint = " + mainBodyAttachPoint);

        PointF mainBodyCenterPoint = new PointF(
                mainBody.getImgWidth() / 2,
                mainBody.getImgHeight() / 2
        );
        PointF partCenterPoint = new PointF(
                part.getRealImage().getWidth() / 2,
                part.getRealImage().getHeight() / 2
        );

        PointF centerPoint = new PointF(
                getViewPosition().x + sbScale * ((mainBodyAttachPoint.x - mainBodyCenterPoint.x) + (partCenterPoint.x - part.getAttachPoint().x)),
                getViewPosition().y + sbScale * ((mainBodyAttachPoint.y - mainBodyCenterPoint.y) + (partCenterPoint.y - part.getAttachPoint().y))
        );

        centerPoint = GraphicsUtils.rotate(
                GraphicsUtils.subtract(centerPoint, getViewPosition()),
                GraphicsUtils.degreesToRadians(rotationDegrees)
        );
        centerPoint = GraphicsUtils.add(centerPoint, getViewPosition());

        return centerPoint;
    }

    private PointF getMainBodyAttachPoint(ShipPart part) {
        PointF mainBodyAttachPoint;
        if(part.getClass() == Cannon.class) {
            mainBodyAttachPoint = getPointFromString(mainBody.getCannonAt());
        }
        else if(part.getClass() == ExtraParts.class) {
            mainBodyAttachPoint = getPointFromString(mainBody.getExtraAt());
        }
        else if(part.getClass() == Engine.class) {
            mainBodyAttachPoint = getPointFromString(mainBody.getEngineAt());
        }
        else {
            mainBodyAttachPoint = getPosition();
        }
        return mainBodyAttachPoint;
    }

    private PointF getEmitPoint() {
        PointF emitPoint = new PointF(
                getViewPosition().x +
                        sbScale *
                                getPointFromString(cannon.getEp()).x,
                getViewPosition().y +
                        sbScale *
                                getPointFromString(cannon.getEp()).y
        );

        emitPoint = GraphicsUtils.rotate(
                GraphicsUtils.subtract(emitPoint, getViewPosition()),
                GraphicsUtils.degreesToRadians(rotationDegrees)
        );
        emitPoint = GraphicsUtils.add(emitPoint, getViewPosition());

        return emitPoint;
    }

    public PointF getViewPosition() {
        return GraphicsUtils.subtract(
                getPosition(),
                MainContainer.getViewport().getPosition()
        );
    }

    public void generateRectangle() {
        if(MainContainer.getModel() != null) {
            setRectangle(new RectF(
                    getViewPosition().x -
                            MainContainer.getModel().getScaleFactor() *
                                    ((mainBody.getImgWidth() / 2 +
                                            getPointFromString(mainBody.getExtraAt()).x) +
                                            extraParts.getAttachPoint().x),
                    getViewPosition().y -
                            MainContainer.getModel().getScaleFactor() *
                                    (mainBody.getImgHeight() / 2),
                    getViewPosition().x +
                            MainContainer.getModel().getScaleFactor() *
                                    (getPointFromString(mainBody.getCannonAt()).x -
                                            (mainBody.getRealImage().getWidth() / 2) +
                                            cannon.getImgWidth() -
                                            cannon.getAttachPoint().x),
                    getViewPosition().y +
                           MainContainer.getModel().getScaleFactor() *
                                    getPointFromString(mainBody.getEngineAt()).y - (mainBody.getImgHeight() / 2) +
                                            engine.getRealImage().getHeight() - engine.getAttachPoint().y)
            );
        }
    }





    @Override
    public String toString() {
        return "Ship{" +
                "mainBody=" + mainBody +
                ", cannon=" + cannon +
                ", extraParts=" + extraParts +
                ", engine=" + engine +
                ", powerCore=" + powerCore +
                '}';
    }
}
