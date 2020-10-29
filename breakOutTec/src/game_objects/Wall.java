package game_objects;

import java.awt.Graphics2D;

import adt.LinkedList;
import adt.List;
import state_machine.Timer;

/**
 * This class gives the basic structure to all the enemy lines.
 * @author Victor Castrillo
 *
 */
public class Wall implements Drawable {
	private List<Block> line1, line2;
	private int posX, posY, lvl;
	private Timer timer;
	
	public Wall(int posX, int posY, int lvl) {
		this.posX = posX;
		this.posY = posY;
		this.lvl = lvl;
		
		this.line1 = new LinkedList<Block>();
		this.line2 = new LinkedList<Block>();
		
		this.setNewLines();
		
		this.timer = new Timer();
	}

	public List<Block> getLine1() {
		return line1;
	}

	public void setLine1(List<Block> line1) {
		this.line1 = line1;
	}

	public List<Block> getLine2() {
		return line2;
	}

	public void setLine2(List<Block> line2) {
		this.line2 = line2;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Timer getTimer() {
		return timer;
	}
	
	public void setNewLines() {
		this.line1.clear();
		this.line2.clear();

		for (int i = 0; i < 14; i++) {
			this.line1.add(new Block((this.posX + (-7+i)*54), this.posY, 48, 8, this.lvl));
			this.line2.add(new Block((this.posX + (-7+i)*54), this.posY + 16, 48, 8, this.lvl));
		}
	}

	@Override
	public void draw(Graphics2D g) {
		for (int i = 0; i < 14; i++) {
			this.line1.get(i).draw(g);
			this.line2.get(i).draw(g);
		}
	}

	@Override
	public void update(double delta) {
		for (int i = 0; i < this.line1.size(); i++) {
			this.line1.get(i).update(delta);
			this.line2.get(i).update(delta);
		}
	}
}
