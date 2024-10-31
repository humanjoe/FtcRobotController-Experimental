package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Arm {

    // Create necessary variables to control 2 motors
    private double firstJointPower;
    private double secondJointPower;

    DcMotor MTR_FIRST_JOINT;
    DcMotor MTR_SECOND_JOINT;
    LinearOpMode bot;

    PIDController pid = new PIDController(1.0, 0.1, 0.01);
    long lastTimePID = 0;

    public Arm(LinearOpMode iBot) {
        // Take the passed in value of gamepad1 and telemetry and assign to class variables.
        bot = iBot;

        // Setup Motors
        MTR_FIRST_JOINT  = bot.hardwareMap.get(DcMotor.class, "first_joint");
        //MTR_FIRST_JOINT.setDirection(DcMotor.Direction.REVERSE);
        MTR_FIRST_JOINT.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MTR_FIRST_JOINT.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //MTR_FIRST_JOINT.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        MTR_SECOND_JOINT = bot.hardwareMap.get(DcMotor.class, "second_joint");
        //MTR_SECOND_JOINT.setDirection(DcMotor.Direction.FORWARD);
        MTR_SECOND_JOINT.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MTR_SECOND_JOINT.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //MTR_SECOND_JOINT.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //ToDo: Reset encoders here for the started "folded" postion.

        firstJointPower = 0.0;
        secondJointPower = 0.0;

        // use braking to slow the motor down faster
        MTR_FIRST_JOINT.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // disables the default velocity control
        // this does NOT disable the encoder from counting,
        // but lets us simply send raw motor power.
        MTR_FIRST_JOINT.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    // Test the PIController code.
    public void TestPID() {
        // position in encoder ticks where we would like the motor to run
        int targetPosition = 100;

        // Set current start time.
        this.lastTimePID = 0;
        // Setup a variable for each drive wheel to save power level for telemetry
        double firstJointPosition;
        double currentEncoderPosition;

        // ToDo: Need to mess with this value.
        pid.setSetPoint(100); // For example, setting a degree for motor to move to

        // Get the current system time and figure out the Delta
        long currentTime = System.nanoTime();
        double deltaTime = (currentTime - lastTimePID);
        lastTimePID = currentTime;

        // Get the current encoder position
        currentEncoderPosition = MTR_FIRST_JOINT.getCurrentPosition();

        // Use 'output' to adjust your system (e.g., power to a heater)
        firstJointPosition = pid.calculate(currentEncoderPosition, deltaTime);

        // Send calculated power to wheels
        MTR_FIRST_JOINT.setPower(firstJointPosition);

        bot.telemetry.addData("Status", "Run Time: " + bot.getRuntime());
        bot.telemetry.addData("Motors", "First (%.2f)", firstJointPosition);
        bot.telemetry.update();
    }

    // Causes the first joint to open.
    public void firstJointExtend() {

    }

    // Close the first joint of the arm.
    public void firstJointContract() {

        //leftFlipMotor.setTargetPosition(-300);
//        rightFlipMotor.setTargetPosition(100);

        // Turn On RUN_TO_POSITION
        // leftFlipMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightFlipMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //leftFlipMotor.setPower(0.25);
 //       rightFlipMotor.setPower(0.10);
    }

    // Causes the second joint to open.
    public void secondJointExtend() {

    }

    // Causes the second joint to close.
    public void secondJointContract() {

    }

    // Move the arm to the pickup position.
    public void moveArmToPickup() {

    }

    // Move the arm to the deploy position.
    public void moveArmToDeploy() {

    }
}
