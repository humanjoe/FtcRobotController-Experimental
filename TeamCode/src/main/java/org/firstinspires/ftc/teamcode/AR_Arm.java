package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * This class create an AR_Arm object that is used to encapsulate all the code used to control and use
 * the 2024-2025 Aerospace Robotics Robot Arm.
 *
 * Instantiate that class for each AR_Arm (Currently just one in our design).
 *
 * Creation Date: 11/3/2024
 */

@Config
public class AR_Arm
{
    // Currently, the arm's rest is at approx. 43 degree up pointing straight down. That mean gravity is
    // working the most against the arm (horizontal) at -47 from the rest. So to make the angle align
    // with more realistic angles. the rest should be at 43 degree instead of 0.
    public static int FIRST_JOINT_REST_ANGLE = -40; //degrees

    // These variables are used to customize joint angles for the AR_Arm. All of these
    // variables are available to be adjusted, in real-time, using FTC Dashboard.
    public static int FIRST_JOINT_START = -40,      SECOND_JOINT_START = 0;
    public static int FIRST_JOINT_ACTIVE = -68,      SECOND_JOINT_ACTIVE = 65;
    public static int FIRST_JOINT_DEPLOY = -165, SECOND_JOINT_DEPLOY = 190;
    public static int FIRST_JOINT_GRAB = -68,    SECOND_JOINT_GRAB = 140;


    public static double P1 = 0.003, I1 = 0.05, D1 = 0.0001;
    public static double F1 = 0.05;

    public static double P2 = 0.001, I2 = 0.05, D2 = 0.0001;
    public static double F2 = 0.05;

    public static int START = 0;
    public static int ACTIVE = 1;
    public static int GRAB = 2;
    public static int DEPLOY = 3;

    // Create a "AR_Joint" instance for each joint of the "AR_Arm".
    private AR_Joint jointFirst;
    private AR_Joint jointSecond;

    // Variables to save the desired angle of the two AR_JOINTs.
    private int targetFirst;
    private int targetSecond;

    private LinearOpMode bot;

    private int lastState = START;
    private int currentState = START;

    /**
     * Constructor. Gets the arm ready for moving.
     *
     * @param iBot Handle to the LinearOpMode.
     */
    public AR_Arm( LinearOpMode iBot )
    {
        // Take the passed in value and assign to class variables.
        this.bot = iBot;

        // Declare instances of the two joints.
        this.jointFirst = new AR_Joint(this.bot, "first_joint", P1, I1, D1, F1);
        this.jointSecond = new AR_Joint(this.bot, "second_joint", P2, I2, D2, F2);

        // Set FTC Dashboard Telemetry
        bot.telemetry = new MultipleTelemetry( bot.telemetry, FtcDashboard.getInstance( ).getTelemetry( ) );
    }

    /**
     * Return immediately and moves the joints to the desired location.
     */
    public void updateArmPos( )
    {
        // ToDo: I wonder if we need to come up with code to move the joints at different times. For example, maybe we have to move joint 1 20 degrees before moving joint 2 at all.
        // Arm should be tested before adding that code.
        this.jointFirst.moveJoint(this.targetFirst, lastState);
        this.jointSecond.moveJoint(this.targetSecond, lastState);
    }

    /**
     * Return immediately and sets the arm joint angles to a custom value.
     *
     * @param firstJoint The position of the first joint in degrees.
     * @param secondJoint The position of the second joint in degrees.
     */
    public void setArmCustomPos(int firstJoint, int secondJoint )
    {
        this.targetFirst = firstJoint; //degrees
        this.targetSecond = secondJoint; //degrees
    }

    /**
     * Return immediately and sets the arm joint angles to the preset location for deploying a specimen into the upper hopper.
     */
    public void setArmDeployPos( )
    {
        // Todo: This needs to be carefully tested before we run the code to make sure the motor direction is correct, etc.
        this.targetFirst = FIRST_JOINT_DEPLOY;
        this.targetSecond = SECOND_JOINT_DEPLOY;

        lastState = currentState;
        currentState = DEPLOY;
    }

    /**
     * Return immediately and ets the arm joint angles to the preset location for picking up a specimen.
     */
    public void setArmGrabPos( )
    {
        // Todo: This needs to be carefully tested before we run the code to make sure the motor direction is correct, etc.
        this.targetFirst = FIRST_JOINT_GRAB;
        this.targetSecond = SECOND_JOINT_GRAB;

        lastState = currentState;
        currentState = GRAB;
    }

    /**
     * Return immediately and sets the arm joint angles to the preset ready to travel position.
     */
    public void setArmActivePos( )
    {
        // Todo: This needs to be carefully tested before we run the code to make sure the motor direction is correct, etc.
        this.targetFirst = FIRST_JOINT_ACTIVE;
        this.targetSecond = SECOND_JOINT_ACTIVE;

        lastState = currentState;
        currentState = ACTIVE;


        // Todo: Somehow the power should be set to zero after movement because we don't want to waste battery power holding
        // the arm in the lowered position. This will only work if the arm has a rest which it doesn't right now.
    }

    /**
     * Return immediately and sets the arm joint angles to the start position.
     */
    public void setArmStartPos( )
    {
        // Todo: This needs to be carefully tested before we run the code to make sure the motor direction is correct, etc.
        this.targetFirst = FIRST_JOINT_START;
        this.targetSecond = SECOND_JOINT_START;

        lastState = currentState;
        currentState = START;
    }
}