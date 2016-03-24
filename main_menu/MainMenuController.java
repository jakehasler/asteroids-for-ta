package edu.byu.cs.superasteroids.main_menu;

import edu.byu.cs.superasteroids.base.IView;
import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.game.MainContainer;
import edu.byu.cs.superasteroids.model.MainModel;
import edu.byu.cs.superasteroids.model.ship.Ship;
import edu.byu.cs.superasteroids.ship_builder.ShipBuildingController;

/**
 * Created by jakehasler on 3/7/16.
 */
public class MainMenuController implements IMainMenuController {

    private MainActivity mainActivity;
    private MainModel model;
    private Ship ship;
    private ContentManager contentManager;

    public MainMenuController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        model = MainContainer.getModel();
//        this.contentManager = contentManager;
        ship = model.getShip();
    }

    public void loadContent(ContentManager contentManager) {

    }


    @Override
    public void onQuickPlayPressed() {
        loadContent(contentManager);
        makeShip();
        mainActivity.startGame();
    }

    private void makeShip() {
        ship.setMainBody(model.getMainBodies().get(0));
        ship.setCannon(model.getCannons().get(0));
        ship.setExtraParts(model.getExtraParts().get(0));
        ship.setEngine(model.getEngines().get(0));
        ship.setPowerCore(model.getPowerCores().get(0));
    }

    @Override
    public IView getView() {
        return null;
    }

    @Override
    public void setView(IView view) {

    }
}
