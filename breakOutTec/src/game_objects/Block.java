package game_objects;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * This class creates an enemy to be added in the enemy line.
 * @author Victor Castrillo
 *
 */
public class Block extends MoveableObject implements Drawable {
	private int resistance, lvl;

	public Block(int posX, int posY, int width, int height, int resistance) {
		super(posX, posY, width, height, 0, "Invader_1");
		this.lvl = resistance;
		this.resistance = resistance*2;
		
		this.setRect();
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
