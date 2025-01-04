package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * This class creates a PID Controller to use with each joint.
 *
 * Instantiate this class for each JOINT in the ARM object.
 *
 * Creation Date: 11/3/2024
 */
@Config
public class AR_PIDController
{
    private PIDController controller;

    // These variables are used to customize the PID Controller for the application. All of these
    // variables are available to be adjusted, in real-time, using FTC Dashboard.
    public static double p, i, d;
    public static double f;

    // This variable need to be customized for the motor being used. PPR (Pulses Per Revolution) is
    // available from the motor manufacturer.
    //private final double ticksPerDegree = 537.7 / 360;  // For GoBilda 312 RPM Motor
    private final double ticksPerDegree = 5281.1 / 360;  // For GoBilda 30 RPM Motor

    LinearOpMode bot;
    private DcMotor motor;
    private String jointName;

    /**
     * Constructor. Perform the setup of the PID Controller.
     * // ToDo: We are passing in the PID values here but I still think there needs to be some customizations for our particular usage with double joints, built off of each other.
     *
     * @param iBot Object to OpMode so you can access HardwareMap, etc.
     * @param iMotor Motor object that is being used in the joint.
     * @param iJointName Name of Object to OpMode so you can access HardwareMap, etc.
     * @param iP Passed in p value.
     * @param iI Passed in i value
     * @param iD Passed in d value
     * @param iF Passed in f value
     */
    public AR_PIDController(LinearOpMode iBot, DcMotor iMotor, String iJointName, double iP, double iI, double iD, double iF)
    {
        this.bot = iBot;
        this.motor = iMotor;
        this.jointName = iJointName;

        p = iP;
        i = iI;
        d = iD;
        f = iF;

        // Create PID Controller
        controller = new PIDController( p, i, d );
    }

    /**
     * This function takes a target value and moves the joint to that position.
     *
     * @param target Value that the joint should move to.
     */
    public void loop(int target ) // Input in degrees
    {
        this.controller.setPID( p, i, d );

        int armPos = this.motor.getCurrentPosition( );       // armPos is in Ticks

        // Original Method
        double pid = this.controller.calculate( armPos, target * ticksPerDegree );  // target is in degrees
        double ff = Math.cos( Math.toRadians( target * ticksPerDegree ) ) * f;  // Here we are passing Ticks to the toRadians function.

      // ToDo: Maybe we should convert to degrees, then after all computations (including radians) convert back to "ticks" before we send to motor. Thinking about it, this is the next test I would do.
        // Convert to degrees, then perform calculations
        //double pid = this.controller.calculate( armPos / ticksPerDegree , target );
        //double ff = Math.cos( Math.toRadians( target ) ) * f;

        double power = pid + ff;  //pid is based off of ticks,  ff is based off of ticks

        this.motor.setPower( power );  // ToDo: Something to try, maybe multiple "power" by a factor (0.8 for example) to artificially slow the motor down a small bit.

        //this.bot.telemetry.addData("Power"," (" + this.jointName + ") " + power ); // Degrees
        //this.bot.telemetry.addData("Position", " (" + this.jointName + ") " + armPos / ticksPerDegree ); // Degrees
        //this.bot.telemetry.addData("Target", " (" + this.jointName + ") " + target );

        this.bot.telemetry.addData(this.jointName + "Pos ", armPos / ticksPerDegree ); // Degrees
        this.bot.telemetry.addData(this.jointName + "Target ", target );
    }
}