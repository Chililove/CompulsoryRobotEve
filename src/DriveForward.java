import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.*;

public class DriveForward  implements Behavior {
   private boolean suppressed = false;
   private RegulatedMotor leftMotor;
   private RegulatedMotor rightMotor;
	
   public DriveForward(RegulatedMotor left, RegulatedMotor right, int speed) {
	   leftMotor = left;
	   rightMotor = right;
	   leftMotor.setAcceleration(speed);
	   rightMotor.setAcceleration(speed);
   }
   
   public boolean takeControl() {
      return true;
   }

   public void suppress() {
      suppressed = true;
   }

   public void action() {
     suppressed = false;
     leftMotor.forward();
     rightMotor.forward();
     while( !suppressed )
        Thread.yield();
     leftMotor.startSynchronization();
     rightMotor.startSynchronization();
     leftMotor.stop();
     rightMotor.stop();
     leftMotor.endSynchronization();
     rightMotor.endSynchronization();
   }
}