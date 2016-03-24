package edu.byu.cs.superasteroids.ship_builder;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.superasteroids.base.IView;
import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.game.MainContainer;
import edu.byu.cs.superasteroids.model.MainModel;
import edu.byu.cs.superasteroids.model.Placed;
import edu.byu.cs.superasteroids.model.ship.Cannon;
import edu.byu.cs.superasteroids.model.ship.Engine;
import edu.byu.cs.superasteroids.model.ship.ExtraParts;
import edu.byu.cs.superasteroids.model.ship.MainBody;
import edu.byu.cs.superasteroids.model.ship.PowerCore;
import edu.byu.cs.superasteroids.model.ship.Ship;
import edu.byu.cs.superasteroids.model.ship.ShipPart;

/**
 * Created by jakehasler on 3/4/16.
 */
public class ShipBuildingController implements IShipBuildingController {

    private IShipBuildingView shipBuildingView;
    private Context context;
    private PointF gameViewCenter;
    private IShipBuildingView.PartSelectionView currentView;
    private MainModel model;
    private Ship ship;
    private boolean first;
    private ContentManager loadedContent;
    private List<Integer> mainBodyImages;
    private List<Integer> cannonImages;
    private List<Integer> extraPartImages;
    private List<Integer> engineImages;
    private List<Integer> powerCoreImages;
    private int mainBodyIndex;
    private int engineIndex;
    private int cannonIndex;
    private int extraPartIndex;
    private int powerCoreIndex;
    private float sbScale;
    private PointF bodyCenter;

    public ShipBuildingController(IShipBuildingView shipBuildingView, Context context) {
        this.shipBuildingView = shipBuildingView;
        this.context = context;

        startGame = false;
        first = true;
        model = MainContainer.getModel();
        System.out.println("MODEL in BUILDER: " + model.toString());
        // TODO: GET CURRENT SHIP
        ship = MainContainer.getModel().getShip();

        mainBodyImages = new ArrayList<Integer>();
        cannonImages = new ArrayList<Integer>();
        extraPartImages = new ArrayList<Integer>();
        engineImages = new ArrayList<Integer>();
        powerCoreImages = new ArrayList<Integer>();

        mainBodyIndex = -1;
        engineIndex = -1;
        cannonIndex = -1;
        extraPartIndex = -1;
        powerCoreIndex = -1;

        sbScale = (float)0.5;
    }


    /** The variable that determines whether or not the game can be started. */
    private boolean startGame;

//    public ShipBuildingController(ShipBuildingActivity activity) {
//        this.activity = activity;
//    }


    // Prelim
    @Override
    public void loadContent(ContentManager content) {
        if(first) {
            loadedContent = content;
            //MainContainer.getViewport().setId(content.loadImage(MainContainer.getViewport().getRealImage().getPath()));
            for (MainBody body : model.getMainBodies()) {
                int imgId = content.loadImage(body.getImage());
                body.getRealImage().setId(imgId);
                mainBodyImages.add(imgId);
            }
            shipBuildingView.setPartViewImageList(IShipBuildingView.PartSelectionView.MAIN_BODY, mainBodyImages);
            for (Cannon cannon : model.getCannons()) {
                int imgId = content.loadImage(cannon.getImage());
                cannon.setAtImgId(content.loadImage(cannon.getAtImg()));
                cannon.getRealImage().setId(imgId);
                cannonImages.add(imgId);
            }
            shipBuildingView.setPartViewImageList(IShipBuildingView.PartSelectionView.CANNON, cannonImages);
            for (ExtraParts part : model.getExtraParts()) {
                int imgId = content.loadImage(part.getImage());
                part.getRealImage().setId(imgId);
                extraPartImages.add(imgId);
            }
            shipBuildingView.setPartViewImageList(IShipBuildingView.PartSelectionView.EXTRA_PART, extraPartImages);
            for (Engine eng : model.getEngines()) {
                int imgId = content.loadImage(eng.getImage());
                eng.getRealImage().setId(imgId);
                engineImages.add(imgId);
            }
            shipBuildingView.setPartViewImageList(IShipBuildingView.PartSelectionView.ENGINE, engineImages);
            for (PowerCore core : model.getPowerCores()) {
                int imgId = content.loadImage(core.getImage());
                core.getRealImage().setId(imgId);
                powerCoreImages.add(imgId);
            }
            shipBuildingView.setPartViewImageList(IShipBuildingView.PartSelectionView.POWER_CORE, powerCoreImages);
        }

        first = false;

    }

    public void isShipComplete() {
        if(mainBodyIndex >= 0
                && engineIndex >= 0
                && cannonIndex >= 0
                && extraPartIndex >= 0
                && powerCoreIndex >= 0) {
            startGame = true;
            Toast shipComplete = Toast.makeText(context, "Your Ship is now complete! You may start the game!", Toast.LENGTH_LONG);
            shipComplete.show();
            shipBuildingView.setStartGameButton(startGame);
//            System.out.println("Current Ship = " + ship);
            MainContainer.getModel().setShip(ship);
//            System.out.println("Current Ship In Model = " + MainContainer.getModel().getShip().toString());
        } else {
            startGame = false;
            Toast shipNotComplete = Toast.makeText(context, "Your Ship is not yet complete!", Toast.LENGTH_LONG);
            //shipNotComplete.show();
        }
    }


    // 1
    @Override
    public void onViewLoaded(IShipBuildingView.PartSelectionView partView) {
        currentView = partView;
        gameViewCenter = new PointF(DrawingHelper.getGameViewWidth() / 2, DrawingHelper.getGameViewHeight() / 2);
        shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.UP, false, partView.toString());
        shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.LEFT, true, getPrevView().toString());
        shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.RIGHT, true, getNextView().toString());
        shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.DOWN, false, null);

        shipBuildingView.setStartGameButton(startGame);
    }

    // 2
    @Override
    public void onSlideView(IShipBuildingView.ViewDirection direction) {
        if(direction == IShipBuildingView.ViewDirection.LEFT) {
            shipBuildingView.animateToView(getNextView(), IShipBuildingView.ViewDirection.RIGHT);
        }
        else if(direction == IShipBuildingView.ViewDirection.RIGHT) {
            shipBuildingView.animateToView(getPrevView(), IShipBuildingView.ViewDirection.LEFT);
        }
    }

    // 3
    @Override
    public void onPartSelected(int index) {
        switch(currentView) {
            case MAIN_BODY:
                mainBodyIndex = index;
                ship.setMainBody(model.getMainBodies().get(mainBodyIndex));
                break;
            case ENGINE:
                engineIndex = index;
                ship.setEngine(model.getEngines().get(engineIndex));
                break;
            case CANNON:
                cannonIndex = index;
                ship.setCannon(model.getCannons().get(cannonIndex));
                break;
            case EXTRA_PART:
                extraPartIndex = index;
                ship.setExtraParts(model.getExtraParts().get(extraPartIndex));
                break;
            case POWER_CORE:
                powerCoreIndex = index;
                ship.setPowerCore(model.getPowerCores().get(powerCoreIndex));
                break;
        }
        isShipComplete();
    }


    /////////////////////////////////////////////////////////


    private IShipBuildingView.PartSelectionView getNextView() {
        switch (currentView) {
            default: return IShipBuildingView.PartSelectionView.MAIN_BODY;
            case MAIN_BODY: return IShipBuildingView.PartSelectionView.ENGINE;
            case ENGINE: return IShipBuildingView.PartSelectionView.CANNON;
            case CANNON: return IShipBuildingView.PartSelectionView.EXTRA_PART;
            case EXTRA_PART: return IShipBuildingView.PartSelectionView.POWER_CORE;
            case POWER_CORE: return IShipBuildingView.PartSelectionView.MAIN_BODY;
        }
    }

    private IShipBuildingView.PartSelectionView getPrevView() {
        switch (currentView) {
            default: return IShipBuildingView.PartSelectionView.MAIN_BODY;
            case MAIN_BODY: return IShipBuildingView.PartSelectionView.POWER_CORE;
            case ENGINE: return IShipBuildingView.PartSelectionView.MAIN_BODY;
            case CANNON: return IShipBuildingView.PartSelectionView.ENGINE;
            case EXTRA_PART: return IShipBuildingView.PartSelectionView.CANNON;
            case POWER_CORE: return IShipBuildingView.PartSelectionView.EXTRA_PART;
        }
    }

    @Override
    public IView getView() {
        return null;
    }

    @Override
    public void setView(IView view) {

    }

    @Override
    public void update(double elapsedTime) {

    }

    @Override
    public void unloadContent(ContentManager content) {

    }

    @Override
    public void draw() {
        if(mainBodyIndex >= 0) {
            gameViewCenter = new PointF(DrawingHelper.getGameViewWidth() / 2, DrawingHelper.getGameViewHeight() / 2);
            bodyCenter = new PointF(gameViewCenter.x - ((ship.getMainBody().getImgWidth() * sbScale)/2), gameViewCenter.y - ((ship.getMainBody().getImgHeight() * sbScale)/2));
            DrawingHelper.drawImage(mainBodyImages.get(mainBodyIndex), gameViewCenter.x, gameViewCenter.y, 0, sbScale, sbScale, 255);
        }
        if(engineIndex >= 0) {
            Engine engine = ship.getEngine();
            drawShipPart(engineImages.get(engineIndex), engine.getImgWidth(), engine.getImgHeight(), engine.getAttachPoint(), Placed.getPointFromString(ship.getMainBody().getEngineAt()));
        }
        if(cannonIndex >= 0) {
            Cannon cannon = ship.getCannon();
            drawShipPart(cannonImages.get(cannonIndex), cannon.getImgWidth(), cannon.getImgHeight(), cannon.getAttachPoint(), Placed.getPointFromString(ship.getMainBody().getCannonAt()));
        }
        if(extraPartIndex >= 0) {
            ExtraParts part = ship.getExtraParts();
            drawShipPart(extraPartImages.get(extraPartIndex), part.getImgWidth(), part.getImgHeight(), part.getAttachPoint(), Placed.getPointFromString(ship.getMainBody().getExtraAt()));
        }
    }



    @Override
    public void onResume() {

    }

    @Override
    public void onStartGamePressed() {
        shipBuildingView.startGame();
    }


    private void drawShipPart(int id, int partWidth, int partHeight, PointF partAt, PointF bodyAt) {
        float bodyAtX = (bodyAt.x * sbScale) + bodyCenter.x;
        float bodyAtY = (bodyAt.y * sbScale) + bodyCenter.y;
        float partAtX = partAt.x * sbScale;
        float partAtY = partAt.y * sbScale;

        float partOrigX = bodyAtX - partAtX;
        float partOrigY = bodyAtY - partAtY;

        float scaledWidth = partWidth * sbScale;
        float scaledHeight = partHeight * sbScale;

        float partCenterX = partOrigX + scaledWidth/2;
        float partCenterY = partOrigY + scaledHeight/2;

        DrawingHelper.drawImage(id, partCenterX, partCenterY, 0, sbScale, sbScale, 255);

    }



}
