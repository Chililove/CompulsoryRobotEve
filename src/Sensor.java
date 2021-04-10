import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RangeFinderAdapter;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.*;
import lejos.hardware.Sound;
import lejos.utility.*;


public class Sensor implements Behavior {
	
	  private EV3UltrasonicSensor sonar;
	  private boolean suppressed = false;
	  private RangeFinderAdapter rf;
	  private RegulatedMotor leftMotor;
	  private RegulatedMotor rightMotor;
	  
	  public Sensor(Port port, RegulatedMotor left, RegulatedMotor right, int speed) {
		  sonar = new EV3UltrasonicSensor(port);
		  rf = new RangeFinderAdapter(sonar.getDistanceMode());
		  leftMotor = left;
		   rightMotor = right;
		   leftMotor.setAcceleration(speed);
		   rightMotor.setAcceleration(speed);
	  }
	  
	@Override
	public boolean takeControl() {
		if(rf.getRange() < 25)
			return true;
		return false;
	}

	@Override
	public void action() {
		int randomInt = -360 + (int) (Math.random() * ((360 - (-360))) +1);
		int randomInt2 = -360 + (int) (Math.random() * ((360 - (-360))) +1);
		suppressed = false;
		Sound.beep();
		leftMotor.backward();
		rightMotor.backward();
		Delay.msDelay(1000);
	    leftMotor.rotate(randomInt, true);
	    rightMotor.rotate(randomInt2, true);

	       while( leftMotor.isMoving() && !suppressed )
	         Thread.yield();

	       leftMotor.stop();
	       rightMotor.stop();
		
	}

	@Override
	public void suppress() {
		suppressed = true;
		
	}

	
	
}
