package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/*--------------------------------------------------------------------------------------------------
 * This class creates a PID Controller to use with each joint.
 *
 * Instantiate this class for each JOINT in the ARM object.
 *
 * Creation Date: 11/3/2024
 ---------------------------------------------------------------------------------------------------
*/
@Config
public class AR_PIDController
{
    private PIDController controller;

    // These variables are used to customize the PID Controller for the application. All of these
    // variables are available to be adjusted, in real-time, using FTC Dashboard.
    public static double p = 0.0007, i = 0.05, d = 0.0001;
    public static double f = 0.05;

    // This variable need to be customized for the motor being used. PPR (Pulses Per Revolution) is
    // available from the motor manufacturer.
    //private final double ticksPerDegree = 537.7 / 360;  // For GoBilda 312 RPM Motor
    private final double ticksPerDegree = 5281.1 / 360;  // For GoBilda 30 RPM Motor

    LinearOpMode bot;
    private DcMotor motor;
    private String jointName;

    public AR_PIDController(LinearOpMode iBot, DcMotor iMotor, String jointName)
    {
        this.bot = iBot;
        this.motor = iMotor;
        this.jointName = jointName;

        // Create PID Controller
        controller = new PIDController( p, i, d );
    }

    public void loop(int target ) // Input in degrees
    {
        this.controller.setPID( p, i, d );


        int armPos = this.motor.getCurrentPosition( );

        // Original Method
        double pid = this.controller.calculate( armPos, target * ticksPerDegree );
        double ff = Math.cos( Math.toRadians( target * ticksPerDegree ) ) * f;

        // ToDo: Maybe we should convert to degrees, then after all computations (including radians) convert back to "ticks" before we send to motor. Thinking about it, this is the next test I would do.
        // Convert to degrees, then perform calculations
        //double pid = this.controller.calculate( armPos / ticksPerDegree , target );
        //double ff = Math.cos( Math.toRadians( target ) ) * f;

        double power = pid + ff;

        this.motor.setPower( power );

        //this.bot.telemetry.addData("Power"," (" + this.jointName + ") " + power ); // Degrees
        //this.bot.telemetry.addData("Position", " (" + this.jointName + ") " + armPos / ticksPerDegree ); // Degrees
        //this.bot.telemetry.addData("Target", " (" + this.jointName + ") " + target );

        this.bot.telemetry.addData("Pos ", armPos / ticksPerDegree ); // Degrees
        this.bot.telemetry.addData("Target ", target );

        this.bot.telemetry.addData("Position", " (" + this.jointName + ") " + armPos ); // Degrees
    }
}