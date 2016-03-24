package edu.byu.cs.superasteroids.model.ship;

import edu.byu.cs.superasteroids.content.Image;
import edu.byu.cs.superasteroids.game.MainContainer;

/**
 * Responsible for assigning the attributes of the Extra Part
 * 
 * @author jakehasler
 * 
 *
 */
public class ExtraParts extends ShipPart {

	String atPoint;
	String image;
	int imgWidth;
	int imgHeight;

	/**
	 * Will Insert all the values from the Table. 
	 * 
	 * @param atPoint Attach Point Coordinates
	 * @param image Image URL
	 * @param imgWidth Width of Image
	 * @param imgHeight Height of Image 
	 */
	public ExtraParts(String atPoint, String image, int imgWidth, int imgHeight) {
		super(new Image(image, imgWidth, imgHeight), getDefaultPosition(), getPointFromString(atPoint), MainContainer.getModel().getScaleFactor());
		this.atPoint = atPoint;
		this.image = image;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
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
		return "ExtraParts{" +
				"atPoint='" + atPoint + '\'' +
				", image='" + image + '\'' +
				", imgWidth=" + imgWidth +
				", imgHeight=" + imgHeight +
				'}';
	}
}
