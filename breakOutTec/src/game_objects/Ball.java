package game_objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

/**
 * This class creates "bullets" that will be shoot by the enemies or the player.
 * @author Victor Castrillo
 *
 */
public class Ball extends MoveableObject implements Drawable {
	private int vecX;
	private int vecY;
	
	public Ball(int posX, int posY, int width, int height, int vecX, int speed, String spriteName) {
		super(posX, posY, width, height, speed, spriteName);
		this.vecX = vecX;
		this.vecY = 1;
	}
	
	public void changeDirX() {
		Random rand = new Random();
		this.vecX = rand.nextInt(11) -5;
	}
	public void changeDirY() {
		this.vecY = -this.vecY;
		if (this.vecX == 0) {
			++this.vecX;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.white);
		g.fillOval(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		g.drawOval(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
	}

	@Override
	public void update(double delta) {
		this.setPosX((int) this.getPosX()+this.vecX);
		this.setPosY(this.getPosY()+this.vecY*this.getSpeed());
		this.setRect();
		
		if (this.getPosX() <= 0 && this.vecX < 0 || this.getPosX() >= 280*3-this.getWidth() && this.vecX > 0) {
			this.changeDirX();
		} 
		if (this.getPosY() <= 0 && this.vecY >= 0) {
			this.changeDirY();
		}
	}
}
