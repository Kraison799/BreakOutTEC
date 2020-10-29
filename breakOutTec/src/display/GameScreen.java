package display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import constructor.Level;
import game_objects.Player;
import server.ControllerServer;
import server.jsonManager;
import state_machine.StateMachine;
import state_machine.SuperStateMachine;

/**
 * This class run the game and draw the screen for it.
 * @author Victor Castrillo
 *
 */
public class GameScreen extends SuperStateMachine implements KeyListener {
	private Player player;
	private Level level;
	private int levelCounter;
	private int score;
	private BufferedImage bg;
	private Font GMFont = new Font("Arial", Font.PLAIN, 28);
	
	private ControllerServer server;
	private jsonManager json;
	
	public GameScreen(StateMachine stateMachine) {
		super(stateMachine);
		player = new Player(280*3/2-32, 360/16*9*3-16, 64, 8, "Spaceship_1");
		level = new Level(1);
		levelCounter = 1;
		score = 0;
		
		this.setServer();
		
		try {
			URL url = this.getClass().getResource("/images/Background.png");
			bg = ImageIO.read(url);
		} catch(IOException e) {e.printStackTrace();}
	}
	
	public void setServer() {
		this.server = new ControllerServer(this);
		server.start();
	}
	
	public void reset() {
		level = new Level(1);
		player = new Player(280*3/2-25, 360/16*9*3-55, 50, 50, "Spaceship_1");
		levelCounter = 1;
		score = 0;
	}
	
	public void gameOver(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 350*3+10, 200*3+10);
		BufferedImage go = null;
		try {
			URL url = this.getClass().getResource("/images/GameOver.png");
			go = ImageIO.read(url);
		} catch(IOException e) {e.printStackTrace();}
		g.drawImage(go, (350*3+10)/2-go.getWidth(), 100, go.getWidth()*2, go.getHeight()*2, null);
		
		g.setFont(GMFont);
		g.setColor(Color.white);
		g.drawString("Score", (350*3+10)/2-g.getFontMetrics().stringWidth("Score")/2, 450);
		g.drawString(Integer.toString(score), (350*3+10)/2-g.getFontMetrics().stringWidth(Integer.toString(score))/2, 500);
	}
	
	public void winner(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 350*3+10, 200*3+10);
		BufferedImage w = null;
		try {
			URL url = this.getClass().getResource("/images/SpaceInvadersLogo.png");
			w = ImageIO.read(url);
		} catch(IOException e) {e.printStackTrace();}
		g.drawImage(w, (350*3+10)/2-w.getWidth()/2, 100, w.getWidth(), w.getHeight(), null);
	}
	
	public void showInfo(Graphics2D g) {
		g.setFont(GMFont);
		g.setColor(Color.white);
		g.drawString("Level: " + Integer.toString(levelCounter), 280*3+20, 25);
		g.drawString("Score:", 280*3+20, 470);
		g.drawString(Integer.toString(score), 280*3+30, 500);
		
		BufferedImage heart = null;
		try {
			URL url = this.getClass().getResource("/images/Health.png");
			heart = ImageIO.read(url);
		} catch(IOException e) {e.printStackTrace();}
		for(int h = 0; h < player.getLifes(); h++) {
			g.drawImage(heart, 280*3+30+25*h, 570, 20, 20, null);
		}
	}

	public Player getPlayer() {
		return player;
	}

	public Level getLevel() {
		return level;
	}

	public int getLevelCounter() {
		return levelCounter;
	}

	public int getScore() {
		return score;
	}

	@Override
	public void draw(Graphics2D g) {
		if(!this.player.isAlive()) {
			this.gameOver(g);
			return;
		} else if(levelCounter > 3) {
//			this.winner(g);
//			return;
		}
		g.drawImage(bg, 0, 0, 280*3+10, 200*3+10, null);
		g.setColor(Color.gray);
		g.fillRect(280*3+10, 0, 350*3-280*3, 200*3+10);
		this.showInfo(g);
		player.draw(g);
		level.draw(g);
	}

	@Override
	public void update(double delta) {
		if(!player.isAlive()) {
			return;
		} else if(levelCounter > 3) {
//			return;
		}
		if(level.destroy()) {
			++levelCounter;
			level = new Level(levelCounter);
		}
		// Loop to destroy enemies
		
		// Loop to destroy the player
		
		// Loop to get collisions between enemies and the player
		
		// Update for the objects in the screen
		player.update(delta);
		level.update(delta);
	}

	@Override
	public void init(Canvas canvas) {
		canvas.addKeyListener(player);
		canvas.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE) {
			this.reset();
			this.getStateMachine().setState((byte) 0);
		} else if(key == KeyEvent.VK_P) {
			this.getStateMachine().setState((byte) 2);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

}
