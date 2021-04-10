import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;
import lejos.hardware.motor.Motor;
import lejos.robotics.subsumption.Behavior;

public class Stop implements Behavior, KeyListener{

	private boolean wantToStop = false;
	 private boolean suppressed = false;
	
	public Stop() {
		Button.ESCAPE.addKeyListener(this);
	}
	
	@Override
	public boolean takeControl() {
		if(wantToStop)
			return true;
		return false;
	}

	@Override
	public void action() {
		Motor.A.stop();
		Motor.C.stop();
		
	}

	@Override
	public void suppress() {
		suppressed = true;
		
	}
	@Override
	public void keyPressed(Key k) {
		wantToStop = true;
		
	}
	@Override
	public void keyReleased(Key k) {
		wantToStop = true;
		
	}

	
}
