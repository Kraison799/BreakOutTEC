package state_machine;

/**
 * This class is used to run an event according to the time given.
 * @author Victor Castrillo
 *
 */
public class Timer {
	private long prevTime;
	
	public Timer() {
		prevTime = System.currentTimeMillis();
	}
	
	public void resetTimer() {
		prevTime = System.currentTimeMillis();
	}
	
	public boolean timerEvent(int timer) {
		if(System.currentTimeMillis()-getPrevTime() > timer) {
			resetTimer();
			return true;
		}
		
		return false;
	}

	public long getPrevTime() {
		return prevTime;
	}

	public void setPrevTime(long prevTime) {
		this.prevTime = prevTime;
	}
	
	public boolean isTimerReady(int timer) {
		if(System.currentTimeMillis()-getPrevTime() > timer) {
			return true;
		}
		
		return false;
	}
}
