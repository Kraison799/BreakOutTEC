package display;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import state_machine.StateMachine;

/**
 * This is the class that creates the window and draw the screen according to the current state.
 * @author Victor Castrillo
 *
 */
public class Display extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private final int WIDTH = 350, HEIGHT = 200, SCALE = 3;
	
	private boolean running = false;
	private Thread thread;
	
	private int FPS;
	private static StateMachine state;
	private JFrame frame;
	
	public Display() {
		this.setSize(WIDTH*SCALE, HEIGHT*SCALE);
		this.setFocusable(true);
		
		state = new StateMachine(this);
		state.setState((byte) 0);
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void toGame() {
		this.setSize(WIDTH*SCALE + 275, HEIGHT*SCALE);
		this.getFrame().pack();
		state.setState((byte) 1);
	}
	
	public static void main(String args[]) {
		Display display = new Display();
		JFrame frame = new JFrame();
		frame.add(display);
		frame.pack();
		
		frame.setTitle("Space Battle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
		display.setFrame(frame);
		display.start();
	}
	
	public void draw(BufferStrategy bs) {
		do {
			do {
				Graphics2D g = (Graphics2D) bs.getDrawGraphics();
				
				state.draw(g);
				
				g.dispose();
			} while(bs.contentsRestored());
			bs.show();
		} while(bs.contentsLost());
	}
	
	public void update(double delta) {
		state.update(delta);
	}
	
	public synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch(InterruptedException e) {e.printStackTrace();}
	}
	
	@Override
	public void run() {
		long timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
		int frames = 0;
		
		this.createBufferStrategy(3);
		BufferStrategy bs = this.getBufferStrategy();
		
		while(running) {
			long now = System.nanoTime();
			long updates = now - lastTime;
			lastTime = now;
			double delta = updates / (double) OPTIMAL_TIME;
			
			frames++;
			
			if(System.currentTimeMillis()-timer > 1000) {
				timer += 1000;
				FPS = frames;
				frames = 0;
				
				System.out.println(FPS+" FPS");
			}
			
			draw(bs);
			update(delta);
			
			try {
				Thread.sleep((lastTime-System.nanoTime()+OPTIMAL_TIME)/1000000);
			} catch(Exception e) {}
		}
	}
}
