package game_objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import state_machine.Timer;

/**
 * This class creates an enemy to be added in the enemy line.
 * @author Victor Castrillo
 *
 */
public class Block extends MoveableObject implements Drawable {
	private int resistance, lvl, effect;
	private Timer timer;

	public Block(int posX, int posY, int width, int height, int resistance) {
		super(posX, posY, width, height, 0, "Invader_1");
		this.lvl = resistance;
		this.resistance = resistance;
		this.effect = -1;
		
		this.setRect();
	}
	
	public int getEffect() {
		return effect;
	}

	public void setEffect() {
		Random rand = new Random();
		this.effect = rand.nextInt(5);
	}
	
	public void eraseEffect() {
		this.effect = -1;
	}
	
	public boolean destroy() {
		return (this.resistance <= 0);
	}
	
	public void hit() {
		--this.resistance;
	}

	public int getResistance() {
		return resistance;
	}

	@Override
	public void draw(Graphics2D g) {
		if (!this.destroy()) {
			if (this.lvl == 1) {
				g.setColor(Color.green);
			} else if (this.lvl == 2) {
				g.setColor(Color.orange);
			} else if (this.lvl == 3) {
				g.setColor(Color.red);
			}
			g.drawRect(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
			g.fillRect(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
		}
	}

	@Override
	public void update(double delta) {}
}
