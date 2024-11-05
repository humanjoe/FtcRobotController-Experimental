package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/*--------------------------------------------------------------------------------------------------
 * This class create a JOINT object that is used to encapsulate all the code used to control and use
 * the 2024-2025 Aerospace Robotics Robot Arm Joint.
 *
 * Instantiate that class for each JOINT in the ARM object.
 *
 * Creation Date: 11/3/2024
 ---------------------------------------------------------------------------------------------------
*/
public class AR_Joint
{
    private LinearOpMode bot;
    private String motorName;

    // Joint Motor
    private DcMotor jointMotor;

    // PID Controller for Joint
    private AR_PIDController newPID;

    public AR_Joint(LinearOpMode iBot, String jointName) {
        this.bot = iBot;
        //this.motorName = jointName;

        // Attach variable to motor hardware.
        this.jointMotor = bot.hardwareMap.dcMotor.get( jointName );

        // Instantiate new PID Controller for this joint.
        this.newPID = new AR_PIDController(this.bot, jointMotor, jointName);
    }

    // Move joint to the desired target.
    public void moveJoint(int target) {

        this.newPID.loop(target);
    }
}
