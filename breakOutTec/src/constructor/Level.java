package constructor;

import java.awt.Graphics2D;
import java.util.Random;

import adt.LinkedList;
import game_objects.Block;
import game_objects.Drawable;
import game_objects.Wall;

/**
 * This class creates the current enemy line and the next enemy line and it also operate them.
 * @author Victor Castrillo
 *
 */
public class Level implements Drawable {
	private Wall easy, medium, hard, imp;
	private int lvl;
	
	public Level(int lvl) {
		this.createWave();
		this.lvl = lvl;
	}
	
	public Wall getEasy() {
		return easy;
	}

	public Wall getMedium() {
		return medium;
	}

	public Wall getHard() {
		return hard;
	}

	public Wall getImp() {
		return imp;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	
	public LinkedList<Block> getBlocks() {
		LinkedList<Block> blocks = new LinkedList<Block>();
		
		for (int i = 0; i < 14; i++) {
			blocks.add(this.easy.getLine1().get(i));
			blocks.add(this.easy.getLine2().get(i));
			blocks.add(this.medium.getLine1().get(i));
			blocks.add(this.medium.getLine2().get(i));
			blocks.add(this.hard.getLine1().get(i));
			blocks.add(this.hard.getLine2().get(i));
			blocks.add(this.imp.getLine1().get(i));
			blocks.add(this.imp.getLine2().get(i));
		}
		
		return blocks;
	}

	public void createWave() {
		this.easy = new Wall(280*3/2, 128, 1);
		this.medium = new Wall(280*3/2, 98, 2);
		this.hard = new Wall(280*3/2, 64, 3);
		this.imp = new Wall(280*3/2, 32, 4);
		Random rand = new Random();
		this.easy.getLine1().get(rand.nextInt(14)).setEffect();
		this.easy.getLine2().get(rand.nextInt(14)).setEffect();
		this.medium.getLine1().get(rand.nextInt(14)).setEffect();
		this.medium.getLine2().get(rand.nextInt(14)).setEffect();
		this.hard.getLine1().get(rand.nextInt(14)).setEffect();
		this.hard.getLine2().get(rand.nextInt(14)).setEffect();
		this.imp.getLine1().get(rand.nextInt(14)).setEffect();
		this.imp.getLine2().get(rand.nextInt(14)).setEffect();
	}
	
	public boolean destroy() {
		for (int i = 0; i < 14; i++) {
			if(!this.easy.getLine1().get(i).destroy() || !this.easy.getLine2().get(i).destroy() || !this.medium.getLine1().get(i).destroy() || !this.medium.getLine2().get(i).destroy() || !this.hard.getLine1().get(i).destroy() || !this.hard.getLine2().get(i).destroy() || !this.imp.getLine1().get(i).destroy() || !this.imp.getLine2().get(i).destroy()) {
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
		this.imp.draw(g);
	}

	@Override
	public void update(double delta) {
		this.easy.update(delta);
		this.medium.update(delta);
		this.hard.update(delta);
		this.imp.update(delta);
	}
}
