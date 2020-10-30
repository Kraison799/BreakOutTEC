package game_objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import adt.LinkedList;
import state_machine.Timer;

/**
 * This is the object that will be controlled by the user.
 * @author Victor Castrillo
 *
 */
public class Player extends MoveableObject implements KeyListener, Drawable {
	private boolean right, left, alive, sizeD, sizeH, spdP, spdM;
	private int lifes;
	
	private LinkedList<Ball> balls = new LinkedList<Ball>();

	public Player(int posX, int posY, int width, int height, String spriteName) {
		super(posX, posY, width, height, 5, spriteName);
		this.right = false;
		this.left = false;
		this.alive = true;
		this.lifes = 5;
		
		Random rand = new Random();
		
		this.balls = new LinkedList<Ball>();
		this.balls.add(new Ball(this.getPosX(), this.getPosY()-30, 16, 16, rand.nextInt(11) -5, -6, "MissileSprite"));
	}
	
	public boolean isSizeD() {
		return sizeD;
	}

	public void setSizeD(boolean sizeD) {
		this.sizeD = sizeD;
	}

	public boolean isSizeH() {
		return sizeH;
	}

	public void setSizeH(boolean sizeH) {
		this.sizeH = sizeH;
	}

	public boolean isSpdP() {
		return spdP;
	}

	public void setSpdP(boolean spdP) {
		this.spdP = spdP;
	}

	public boolean isSpdM() {
		return spdM;
	}

	public void setSpdM(boolean spdM) {
		this.spdM = spdM;
	}

	public void newBall() {
		Random rand = new Random();
		balls.add(new Ball(this.getPosX(), this.getPosY()-30, 16, 16, rand.nextInt(11) -5, -6, "MissileSprite"));
	}
	
	public LinkedList<Ball> getBalls() {
		return balls;
	}
	
	public void eraseBall() {
		if (this.balls.size() <= 1) {
			return;
		} else {
			this.balls.remove(1);
		}
	}
	
	public int getLifes() {
		return lifes;
	}
	
	public void loseLife() {
		if(lifes >= 1) {
			--lifes;
		} else if(lifes == 0) {
			this.alive = false;
		}
	}

	public boolean isAlive() {
		return alive;
	}
	
	public int getRealWidth() {
		if (this.sizeD) {
			return this.getWidth()*2;
		} else if (this.sizeH) {
			return this.getWidth()/2;
		} else {
			return this.getWidth();
		}
	}

	@ Override
	public void draw(Graphics2D g) {
		g.setColor(Color.black);
		g.drawRect(this.getPosX(), this.getPosY(), this.getRealWidth(), this.getHeight());
		g.setColor(Color.white);
		g.fillRect(this.getPosX(), this.getPosY(), this.getRealWidth(), this.getHeight());
		for(int r = 0; r < balls.size(); r++) {
			balls.get(r).draw(g);
		}
	}
	
	@Override
	public void setRect() {
		this.setRect(new Rectangle(this.getPosX(), this.getPosY(), this.getRealWidth(), this.getHeight()));
	}
	
	@ Override
	public void update(double delta) {
		this.setRect();
		
		for(int r = 0; r < balls.size(); r++) {
			this.balls.get(r).update(delta);
			if (this.balls.get(r).isColliding(this)) {
				this.balls.get(r).changeDirY();
			}
			
			if (this.balls.get(r).getPosY() >= 200*3+10) {
				this.balls.remove(r);
//				this.loseLife();
				if (this.balls.size() == 0) {
					this.newBall();
				}
			}
		}
		
		if(right && !left && this.getPosX() < 280*3-this.getRealWidth()+10) {
			this.setPosX(this.getPosX()+this.getSpeed());
		} else if(!right && left && this.getPosX() > 0) {
			this.setPosX(this.getPosX()-this.getSpeed());
		}
		this.setRect();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT) {
			right = true;
		} else if(key == KeyEvent.VK_LEFT) {
			left = true;
		} else if(key == KeyEvent.VK_B) {
			this.newBall();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT) {
			right = false;
		} else if(key == KeyEvent.VK_LEFT) {
			left = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}
