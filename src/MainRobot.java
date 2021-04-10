import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class MainRobot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Brick brick = BrickFinder.getDefault();
		RegulatedMotor left = new EV3LargeRegulatedMotor(brick.getPort(("A")));
    	RegulatedMotor right = new EV3LargeRegulatedMotor(brick.getPort(("C")));
    	int speed = 500;
		
		Behavior b1 = new DriveForward(left, right, speed);
		Behavior b2 = new Sensor(brick.getPort("S3"), left, right, speed);
		Behavior b3 = new Stop();
		Behavior [] bArray = {b1, b2, b3};
		Arbitrator arby = new Arbitrator(bArray);
	      arby.go();
	}
}
