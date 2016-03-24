package edu.byu.cs.superasteroids.model.ship;

import edu.byu.cs.superasteroids.content.Image;
import edu.byu.cs.superasteroids.game.MainContainer;

/**
 * Responsible for assigning the attributes of the Engine
 * 
 * @author jakehasler
 * 
 *
 */
public class Engine extends ShipPart {

	int baseSpeed;
	int baseTurnRate;
	String atPoint;
	String image;
	int imgWidth;
	int imgHeight;
	/**
	 * Will Insert all the values from the Table. 
	 * 
	 * @param baseSpeed Base Speed Value
	 * @param baseTurnRate Base Turn Rate Value
	 * @param atPoint Attach Point Coordinates
	 * @param image Image URL
	 * @param imgWidth Width of Image
	 * @param imgHeight Height of Image 
	 */
	public Engine(int baseSpeed, int baseTurnRate, String atPoint, String image, int imgWidth, int imgHeight) {
		super(new Image(image, imgWidth, imgHeight), getDefaultPosition(), getPointFromString(atPoint), MainContainer.getModel().getScaleFactor());
		this.baseSpeed = baseSpeed;
		this.baseTurnRate = baseTurnRate;
		this.atPoint = atPoint;
		this.image = image;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
	}

	public int getBaseSpeed() {
		return baseSpeed;
	}

	public int getBaseTurnRate() {
		return baseTurnRate;
	}

	public String getAtPoint() {
		return atPoint;
	}

//	@Override
	public String getImage() {
		return image;
	}

	public int getImgWidth() {
		return imgWidth;
	}

	public int getImgHeight() {
		return imgHeight;
	}

	@Override
	public String toString() {
		return "Engine{" +
				"baseSpeed=" + baseSpeed +
				", baseTurnRate=" + baseTurnRate +
				", atPoint='" + atPoint + '\'' +
				", image='" + image + '\'' +
				", imgWidth=" + imgWidth +
				", imgHeight=" + imgHeight +
				'}';
	}
}
