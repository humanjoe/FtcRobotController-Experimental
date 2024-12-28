package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/*--------------------------------------------------------------------------------------------------
 * This class create an AR_Arm object that is used to encapsulate all the code used to control and use
 * the 2024-2025 Aerospace Robotics Robot Arm.
 *
 * Instantiate that class for each AR_Arm (Currently just one in our design).
 *
 * Creation Date: 11/3/2024
 ---------------------------------------------------------------------------------------------------
*/
@Config
public class AR_Arm
{
    // These variables are used to customize joint angles for the AR_Arm. All of these
    // variables are available to be adjusted, in real-time, using FTC Dashboard.
    public static int FIRST_JOINT_DEPLOY = -130, SECOND_JOINT_DEPLOY = 160;
    public static int FIRST_JOINT_GRAB = 45,    SECOND_JOINT_GRAB = 160;
    public static int FIRST_JOINT_REST = -20,     SECOND_JOINT_REST = 0;

    // Create a "AR_Joint" instance for each joint of the "AR_Arm".
    private AR_Joint jointFirst;
    //private AR_Joint jointSecond;

    // Variables to save the desired angle of the two AR_JOINTs.
    private int targetFirst;
    //private int targetSecond;

    private LinearOpMode bot;

    // Todo: Temporary variable, make this false before running motors attached to mechanics.
    private boolean tested = true;

    public AR_Arm( LinearOpMode iBot )
    {
        // Take the passed in value and assign to class variables.
        this.bot = iBot;

        // Declare instances of the two joints.
        this.jointFirst = new AR_Joint(this.bot, "first_joint");
        //this.jointSecond = new AR_Joint(this.bot, "second_joint");

        // Set FTC Dashboard Telemetry
        bot.telemetry = new MultipleTelemetry( bot.telemetry, FtcDashboard.getInstance( ).getTelemetry( ) );
    }

    // Move the joints to the desired location.
    public void updatePos( )
    {
        if (tested) {
            this.jointFirst.moveJoint(this.targetFirst);
            //this.jointSecond.moveJoint(this.targetSecond);
        }
    }

    // Set the arm joint angles to a custom value.
    public void setArmCustomPos(int firstJoint, int secondJoint )
    {
        this.targetFirst = firstJoint; //degrees
        //this.targetSecond = secondJoint; //degrees
    }

    // Set the arm joint angles to the preset location for deploying a specimen into the upper hopper.
    public void setArmDeployPos( )
    {
        // Todo: This needs to be carefully tested before we run the code to make sure the motor direction is correct, etc.
        this.targetFirst = FIRST_JOINT_DEPLOY; // Todo: I think these are ticks, not degrees. For the 312 RPM GoBilda, this is 537.7 Ticks per Rev.
        //this.targetSecond = SECOND_JOINT_DEPLOY;
    }

    // Set thee arm joint angles to the preset location for picking up a specimen.
    public void setArmGrabPos( )
    {
        // Todo: This needs to be carefully tested before we run the code to make sure the motor direction is correct, etc.
        this.targetFirst = FIRST_JOINT_GRAB;
        //this.targetSecond = SECOND_JOINT_GRAB;
    }

    // Set thee arm joint angles to the preset folded and resting location.
    public void setArmRestPos( )
    {
        // Todo: This needs to be carefully tested before we run the code to make sure the motor direction is correct, etc.
        this.targetFirst = FIRST_JOINT_REST;
        //this.targetSecond = SECOND_JOINT_REST;

        // Todo: Somehow the power should be set to zero after movement because we don't want to waste battery power holding
        // the arm in the lowered position.
    }
}



