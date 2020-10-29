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

import state_machine.StateMachine;
import state_machine.SuperStateMachine;

/**
 * This class run the menu and draw the screen for it.
 * @author Victor Castrillo
 *
 */
public class MenuScreen extends SuperStateMachine implements KeyListener {
	private Font titleFont = new Font("Arial", Font.PLAIN, 64);
	private Font startFont = new Font("Arial", Font.PLAIN, 32);
	private String title = "Space Battle";
	private String start = "Press Enter";
	
	private BufferedImage bg;

	public MenuScreen(StateMachine stateMachine) {
		super(stateMachine);
		
		try {
			URL url = this.getClass().getResource("/images/BackgroundMenu.png");
			bg = ImageIO.read(url);
		} catch(IOException e) {e.printStackTrace();}
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(bg, 0, 0, 350*3+10, 200*3+10, null);
		
		g.setFont(titleFont);
		int titleWidth = g.getFontMetrics().stringWidth(title);
		g.setColor(Color.black);
		g.drawString(title, (350*3/2)-(titleWidth/2)+2, 300+2);
		g.setColor(Color.white);
		g.drawString(title, (350*3/2)-(titleWidth/2), 300);
		
		g.setFont(startFont);
		int startWidth = g.getFontMetrics().stringWidth(title);
		g.setColor(Color.white);
		g.drawString(start, (350*3/2)-(startWidth/2), 500);
	}

	@Override
	public void update(double delta) {}

	@Override
	public void init(Canvas canvas) {
		canvas.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ENTER) {
			this.getStateMachine().setState((byte) 1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}
