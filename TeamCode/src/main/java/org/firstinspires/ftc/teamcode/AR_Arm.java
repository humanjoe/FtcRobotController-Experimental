package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/*--------------------------------------------------------------------------------------------------
 * This class create an ARM object that is used to encapsulate all the code used to control and use
 * the 2024-2025 Aerospace Robotics Robot Arm.
 *
 * Instantiate that class for each ARM (Currently just one in our design).
 *
 * Creation Date: 11/3/2024
 ---------------------------------------------------------------------------------------------------
*/
public class AR_Arm {
    // Create a "JOINT" instance for each joint of the "ARM".
    private AR_Joint jointFirst;
    private AR_Joint jointSecond;

    // Variables to save the desired angle of the two JOINTs.
    private int targetFirst;
    private int targetSecond;

    private LinearOpMode bot;

    // Temporary variable
    private boolean tested = true;

    public AR_Arm( LinearOpMode iBot ) {
        // Take the passed in value and assign to class variables.
        this.bot = iBot;

        // Declare instances of the two joints.
        this.jointFirst = new AR_Joint(this.bot, "first_joint");
        this.jointSecond = new AR_Joint(this.bot, "second_joint");
    }

    // Move the joints to the desired location.
    public void updatePos( ) {
        if (tested) {
            this.jointFirst.moveJoint(this.targetFirst);
            this.jointSecond.moveJoint(this.targetSecond);
        }
    }

    // Set the arm joint angles to a custom value.
    public void setArmCustomPos(int firstJoint, int secondJoint ) {
        this.targetFirst = firstJoint; //degrees
        this.targetSecond = secondJoint; //degrees
    }

    // Set thee arm joint angles to the preset location for deploying a specimen in the upper hopper.
    public void setArmDeployPos( ) {
        // Todo: This needs to be carefully tested before we run the code to make sure the motor direction is correct, etc.
        this.targetFirst = 135; // I think these are ticks, not degrees. For the 312 RPM GoBilda, this is 537.7 Ticks per Rev.
        this.targetSecond = 160; //degrees
    }

    // Set thee arm joint angles to the preset location for picking up a specimen.
    public void setArmGrabPos( ) {
        // Todo: This needs to be carefully tested before we run the code to make sure the motor direction is correct, etc.
        this.targetFirst = 45; //degrees
        this.targetSecond = 160; //degrees
    }

    // Set thee arm joint angles to the preset folded and resting location.
    public void setArmRestPos( ) {
        // Todo: This needs to be carefully tested before we run the code to make sure the motor direction is correct, etc.
        this.targetFirst = 0; //degrees
        this.targetSecond = 0; //degrees

        // Todo: Somehow the power should be set to zero after movement because we don't want to waste battery power holding
        // the arm in the lowered position.
    }
}



