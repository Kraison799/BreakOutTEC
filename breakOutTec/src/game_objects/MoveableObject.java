package game_objects;

import java.awt.Rectangle;

/**
 * This class its the basic structure for every movable object in the game.
 * @author Victor Castrillo
 *
 */
public abstract class MoveableObject extends GameObject {
	private int speed;
	private boolean collide;
	private Rectangle rect;

	public MoveableObject(int posX, int posY, int width, int height, int speed, String spriteName) {
		super(posX, posY, width, height, spriteName);
		this.speed = speed;
		this.collide = false;
		this.rect = new Rectangle(posX, posY, width, height);
	}

	public int getSpeed() {
		return speed;
	}
	
	public Rectangle getRect() {
		return rect;
	}

	public void setRect() {
		this.rect = new Rectangle(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
	}
	
	public boolean isColliding(MoveableObject other) {
		collide = other.getRect().intersects(this.getRect());
		return collide;
	}
}
