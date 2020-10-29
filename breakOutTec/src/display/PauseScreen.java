package display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import state_machine.StateMachine;
import state_machine.SuperStateMachine;

/**
 * This class creates a pause menu that stop the game until the user decide to play it.
 * @author Victor Castrillo
 *
 */
public class PauseScreen extends SuperStateMachine implements KeyListener {
	private Font titleFont = new Font("Arial", Font.PLAIN, 64);
	private Font enterFont = new Font("Arial", Font.PLAIN, 32);
	private String title = "Pause";
	private String enter = "Press Enter to continue";

	public PauseScreen(StateMachine stateMachine) {
		super(stateMachine);
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

	@Override
	public void draw(Graphics2D g) {
		g.setFont(titleFont);
		int titleWidth = g.getFontMetrics().stringWidth(title);
		g.setColor(Color.black);
		g.drawString(title, (280*3/2)-(titleWidth/2)+2, 300+2);
		g.setColor(Color.white);
		g.drawString(title, (280*3/2)-(titleWidth/2), 300);
		
		g.setFont(enterFont);
		int enterWidth = g.getFontMetrics().stringWidth(enter);
		g.setColor(Color.white);
		g.drawString(enter, (280*3/2)-(enterWidth/2), 500);
	}

	@Override
	public void update(double delta) {}

	@Override
	public void init(Canvas canvas) {
		canvas.addKeyListener(this);
	}
}
