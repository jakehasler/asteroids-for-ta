package edu.byu.cs.superasteroids.model.ship;

import edu.byu.cs.superasteroids.content.Image;
import edu.byu.cs.superasteroids.game.MainContainer;
import edu.byu.cs.superasteroids.model.Placed;

/**
 * 
 * Responsible for assigning the attributes of the Power Core
 * Updates and Queries the Database
 *
 * @author jakehasler
 * 
 */
public class PowerCore extends Placed {

	int cannonBoost;
	int engineBoost;
	String image;
	/**;
	 * Will Insert all the values from the Table. 
	 * 
	 * @param cannonBoost Cannon Boost Value
	 * @param engineBoost Hint of the Level
	 * @param image Image URL
	 */
	public PowerCore(int cannonBoost, int engineBoost, String image) {
		super(new Image(image, 0, 0), getDefaultPosition());
		this.cannonBoost = cannonBoost;
		this.engineBoost = engineBoost;
		this.image = image;
	}

	public int getCannonBoost() {
		return cannonBoost;
	}

	public int getEngineBoost() {
		return engineBoost;
	}

	public String getImage() {
		return image;
	}

	@Override
	public String toString() {
		return "PowerCore{" +
				"cannonBoost=" + cannonBoost +
				", engineBoost=" + engineBoost +
				", image='" + image + '\'' +
				'}';
	}
}
