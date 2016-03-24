package edu.byu.cs.superasteroids.model.ship;

import android.graphics.Point;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.content.Image;
import edu.byu.cs.superasteroids.game.MainContainer;
import edu.byu.cs.superasteroids.model.Placed;

/**
 * 
 * Responsible for assigning the attributes of the Cannon
 * @author jakehasler
 * 
 *
 */
public class Cannon extends ShipPart {


	String ap;
	String ep;
	String image;
	int imgWidth;
	int imgHeight;
	String atImg;
	int atImgId;
	int atImgWidth;
	int atImgHeight;
	String atSound;
	int damage;

	/**
	 * Will Insert all the values from the Table.
	 *
	 * @param ap Attach Point in coordinates
	 * @param ep Emit Point
	 * @param image Image URL
	 * @param imgWidth Width of Image
	 * @param imgHeight Height of Image
	 * @param atImg Attack Image URL
	 * @param atImgWidth Attack Image Width
	 * @param atImgHeight Attack Image Height
	 * @param atSound Attack Sound URL
	 * @param damage Damage Value
	 */
	public Cannon(String ap, String ep, String image, int imgWidth, int imgHeight, String atImg, int atImgWidth, int atImgHeight, String atSound, int damage) {
		super(new Image(image, imgWidth, imgHeight), getDefaultPosition(), getPointFromString(ap), MainContainer.getModel().getScaleFactor());
		this.ap = ap;
		this.ep = ep;
		this.image = image;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
		this.atImg = atImg;
		this.atImgWidth = atImgWidth;
		this.atImgHeight = atImgHeight;
		this.atSound = atSound;
		this.damage = damage;
		//atImgId = ContentManager.getInstance().loadImage(atImg);
	}

	public String getAp() {
		return ap;
	}

	public String getEp() {
		return ep;
	}


	public String getImage() {
		return this.image;
	}

	public int getImgWidth() {
		return imgWidth;
	}

	public int getImgHeight() {
		return imgHeight;
	}

	public String getAtImg() {
		return atImg;
	}

	public int getAtImgWidth() {
		return atImgWidth;
	}

	public int getAtImgHeight() {
		return atImgHeight;
	}

	public String getAtSound() {
		return atSound;
	}

	public int getDamage() {
		return damage;
	}

	public int getAtImgId() {
		return atImgId;
	}

	public void setAtImgId(int atImgId) {
		this.atImgId = atImgId;
	}

	@Override
	public String toString() {
		return "Cannon{" +
				"ap='" + ap + '\'' +
				", ep='" + ep + '\'' +
				", image='" + image + '\'' +
				", imgWidth=" + imgWidth +
				", imgHeight=" + imgHeight +
				", atImg='" + atImg + '\'' +
				", atImgWidth=" + atImgWidth +
				", atImgHeight=" + atImgHeight +
				", atSound='" + atSound + '\'' +
				", damage=" + damage +
				'}';
	}
}
