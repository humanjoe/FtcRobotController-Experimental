package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
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
    public static double p = 0.003, i = 0, d = 0;
    public static double f = 0.05;

    // This variable need to be customized for the motor being used.
    private final double ticksPerDegree = 537.7 / 360;  // For GoBilda 312 RPM Motor

    LinearOpMode bot;
    private DcMotor motor;
    private String jointName;

    public AR_PIDController(LinearOpMode iBot, DcMotor iMotor, String jointName) {
        this.bot = iBot;
        this.motor = iMotor;
        this.jointName = jointName;

        // Create PID Controller
        controller = new PIDController( p, i, d );

        // Set FTC Dashboard Telemetry
        bot.telemetry = new MultipleTelemetry( bot.telemetry, FtcDashboard.getInstance( ).getTelemetry( ) );
    }

    public void loop(int target ) {
        this.controller.setPID( p, i, d );

        // Todo: Get rid of when testing is finished.
        if( target > 0 ) {
            this.bot.telemetry.addData("Status", "Stopping Loop" );
        }

        int arm_pos = this.motor.getCurrentPosition( );
        double pid = this.controller.calculate( arm_pos, target );
        double ff = Math.cos( Math.toRadians( target / ticksPerDegree ) ) * f;
        double power = pid + ff;

        this.motor.setPower( power );

        this.bot.telemetry.addData("Power: ",this.jointName + ": " + power );
        this.bot.telemetry.addData("Pos:", arm_pos );
        this.bot.telemetry.addData("Target:", target );
    }
}
