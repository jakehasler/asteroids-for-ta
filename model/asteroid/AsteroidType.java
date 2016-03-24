package edu.byu.cs.superasteroids.model.asteroid;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;

import java.util.Random;

import edu.byu.cs.superasteroids.content.Image;
import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.game.MainContainer;
import edu.byu.cs.superasteroids.model.Moves;
import edu.byu.cs.superasteroids.model.Placed;
import edu.byu.cs.superasteroids.model.world.Level;
import edu.byu.cs.superasteroids.model.world.Viewport;

/** Responsible for assigning the attributes of the Asteroid Type.
 * Updates and Queries the Database
 * 
 * @author jakehasler
 *
 */
public class AsteroidType extends Moves {

	String name;
	String image;
	int imgWidth;
	int imgHeight;
	String type;
	int direction;
	PointF position;
	int imgId;
	int astId;
	boolean alive = true;

	Random rng = new Random();
	private float asteroidSize = .5f;

//	public AsteroidType() {
//		this.position = new PointF(0,0);
//		this.direction = rng.nextInt(181);
//	}


	/** Will Insert all the values from the Table.
	 *
	 *
	 * @param name Name
	 * @param image Image URL
	 * @param imageWidth Width of Image
	 * @param imageHeight Height of Image
	 */
	public AsteroidType(String name, Image image, String type, int astId) {
		super(image, new PointF(randInt(100, 2500), randInt(100, 2500)), new RectF(),randInt(15, 150), randInt(0, 360));
		this.name = name;
		this.astId = astId;
		this.type = type;
		this.position = new PointF(0,0);
		this.direction = rng.nextInt(181);
	}

	private static int randInt(int min, int max) {
		return min + (int)(Math.random() * ((max - min) + 1));
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@Override
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAstId() {
		return astId;
	}

	public void setAstId(int astId) {
		this.astId = astId;
	}

	public void processCollision(AsteroidType a) {
		if (GraphicsUtils.isCollision(this.getScreenRectangle(), a.getScreenRectangle())) {
			a.getHit(1);
			this.getHit(1);
		}
	}

	public void draw(PointF viewPortPosition) {
		DrawingHelper.drawImage((int)this.getId(), this.getScreenPosition().x, this.getScreenPosition().y, direction, MainContainer.getModel().getScaleFactor(), MainContainer.getModel().getScaleFactor(), 255);
		DrawingHelper.drawRectangle(
				this.getScreenRectangle(),
				Color.GREEN,
				255
		);
	}

	public void getHit(int damage) {
		this.alive = false;
	}

	public boolean isAlive() {
		return this.alive;
	}

	public void update(double time){

		PointF pos = this.getPosition();
		RectF rect = this.getRectangle();
		float speed = this.getSpeed();
		double rad = GraphicsUtils.degreesToRadians(getRotation() - 90);

		// Check if out of bounds
		if (pos.x < 0) {
			pos.set(3000, pos.y);
		}

		if (pos.x > 3000) {
			pos.set(0, pos.y);
		}

		if (pos.y < 0) {
			pos.set(pos.x, 3000);
		}

		if (pos.y > 3000) {
			pos.set(pos.x, 0);
		}

		GraphicsUtils.MoveObjectResult result = GraphicsUtils.moveObject(pos, rect, speed, rad, time);

		this.setPosition(result.getNewObjPosition());
		this.generateRectangle();
		int hi = 3;
	}

}
