package edu.byu.cs.superasteroids.model.singleton;

/**
 * A class to be extended to all items with an image.
 * @author jakehasler
 *
 */

public class Image {
	
	int imgHeight;
	int imgWidth;
	String coords;
	
	public Image(int imgHeight, int imgWidth, String coords) {
		this.imgHeight = imgHeight;
		this.imgWidth = imgWidth;
		this.coords = coords;
	}

}
