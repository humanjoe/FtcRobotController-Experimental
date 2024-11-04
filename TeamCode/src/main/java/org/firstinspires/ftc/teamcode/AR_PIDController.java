package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/*
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When a selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

@Config
public class AR_PIDController
{
    LinearOpMode bot;

    private PIDController controller;
    public static double p = 0.003, i = 0, d = 0;
    public static double f = 0.05;

    private final double ticksPerDegree = 537.7 / 360;  // For GoBilda 312 RPM Motor

    private DcMotor motor;
    private String jointName;

    public AR_PIDController(LinearOpMode iBot, DcMotor iMotor, String jointName) {
        this.bot = iBot;
        this.motor = iMotor;
        this.jointName = jointName;

        controller = new PIDController( p, i, d );
        bot.telemetry = new MultipleTelemetry( bot.telemetry, FtcDashboard.getInstance( ).getTelemetry( ) );
    }

    // Todo: this code should loop but doesn't right now
    public void loop(int target ) {
        this.controller.setPID( p, i, d );

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
