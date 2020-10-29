package game_objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
	private Timer timer;
	
	private LinkedList<Ball> balls = new LinkedList<Ball>();

	public Player(int posX, int posY, int width, int height, String spriteName) {
		super(posX, posY, width, height, 5, spriteName);
		this.right = false;
		this.left = false;
		this.alive = true;
		this.lifes = 5;
		
		this.balls = new LinkedList<Ball>();
		
		this.timer = new Timer();
	}
	
	public void newBall() {
		Ball ball = new Ball(this.getPosX()+22, this.getPosY()-30, 10, 30, -6, "MissileSprite");
		balls.add(ball);
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
	
	@ Override
	public void update(double delta) {
		for(int r = 0; r < balls.size(); r++) {
			balls.get(r).update(delta);
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
