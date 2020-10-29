package game_objects;

import java.awt.Graphics2D;

/**
 * This class creates "bullets" that will be shoot by the enemies or the player.
 * @author Victor Castrillo
 *
 */
public class Ball extends MoveableObject implements Drawable {
	public Ball(int posX, int posY, int width, int height, int speed, String spriteName) {
		super(posX, posY, width, height, speed, spriteName);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(this.getSprite(), this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight(), null);
	}

	@Override
	public void update(double delta) {
		this.setPosY(this.getPosY()+this.getSpeed());
		this.setRect();
	}
}
