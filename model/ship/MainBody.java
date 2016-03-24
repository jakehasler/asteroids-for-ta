package edu.byu.cs.superasteroids.model.ship;

import edu.byu.cs.superasteroids.content.Image;
import edu.byu.cs.superasteroids.model.Placed;

/**
 * Responsible for assigning the attributes of the Main Body
 * Updates and Queries the Database
 * 
 * @author jakehasler
 * 
 *
 */
public class MainBody extends Placed {

	String cannonAt;
	String engineAt;
	String extraAt;
	String image;
	int imgWidth;
	int imgHeight;

	/**
	 * Will Insert all the values from the Table.
	 *
	 * @param cannonAt Cannon Attach Point Coordinates
	 * @param engineAt Engine Attach Point Coordinates
	 * @param extraAt Extra Part Attach Point Coordinates
	 * @param image Image URL
	 * @param imgWidth Width of Image
	 * @param imgHeight Height of Image
	 */
	public MainBody(String cannonAt, String engineAt, String extraAt, String image, int imgWidth, int imgHeight) {
		super(new Image(image, 0, 0), getDefaultPosition());
		this.cannonAt = cannonAt;
		this.engineAt = engineAt;
		this.extraAt = extraAt;
		this.image = image;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
	}


	public String getCannonAt() {
		return cannonAt;
	}

	public void setCannonAt(String cannonAt) {
		this.cannonAt = cannonAt;
	}

	public String getEngineAt() {
		return engineAt;
	}

	public void setEngineAt(String engineAt) {
		this.engineAt = engineAt;
	}

	public String getExtraAt() {
		return extraAt;
	}

	public void setExtraAt(String extraAt) {
		this.extraAt = extraAt;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}

	public int getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}

	@Override
	public String toString() {
		return "MainBody{" +
				"cannonAt='" + cannonAt + '\'' +
				", engineAt='" + engineAt + '\'' +
				", extraAt='" + extraAt + '\'' +
				", image='" + image + '\'' +
				", imgWidth=" + imgWidth +
				", imgHeight=" + imgHeight +
				'}';
	}
}
