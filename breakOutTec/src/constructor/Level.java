package constructor;

import java.awt.Graphics2D;

import game_objects.Drawable;
import game_objects.Wall;

/**
 * This class creates the current enemy line and the next enemy line and it also operate them.
 * @author Victor Castrillo
 *
 */
public class Level implements Drawable {
	private Wall easy, medium, hard;
	private int lvl;
	
	public Level(int lvl) {
		this.createWave();
		this.lvl = lvl;
	}
	
	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public void createWave() {
		this.easy = new Wall(280*3/2, 96, 3);
		this.medium = new Wall(280*3/2, 64, 2);
		this.hard = new Wall(280*3/2, 32, 1);
	}
	
	public boolean destroy() {
		for (int i = 0; i < 14; i++) {
			if(!this.easy.getLine1().get(i).destroy() || !this.easy.getLine2().get(i).destroy() || !this.medium.getLine1().get(i).destroy() || !this.medium.getLine2().get(i).destroy() || !this.hard.getLine1().get(i).destroy() || !this.hard.getLine2().get(i).destroy()) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void draw(Graphics2D g) {
		this.easy.draw(g);
		this.medium.draw(g);
		this.hard.draw(g);
	}

	@Override
	public void update(double delta) {
		this.easy.update(delta);
		this.medium.update(delta);
		this.hard.update(delta);
	}
}
