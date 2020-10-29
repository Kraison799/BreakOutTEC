package state_machine;

import java.awt.Canvas;
import java.awt.Graphics2D;

/**
 * This is the basic structure for the states that creates a screen to be draw.
 * @author Victor Castrillo
 *
 */
public abstract class SuperStateMachine {
	private StateMachine stateMachine;
	
	public SuperStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}
	
	public StateMachine getStateMachine() {
		return this.stateMachine;
	}
	
	public abstract void draw(Graphics2D g);
	public abstract void update(double delta);
	public abstract void init(Canvas canvas);
}
