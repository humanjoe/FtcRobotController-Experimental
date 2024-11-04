package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class AR_Arm {
    private AR_Joint jointFirst;
    private AR_Joint jointSecond;

    private int targetFirst;
    private int targetSecond;

    private LinearOpMode bot;

    // Temporary variable
    private boolean tested = true;

    public AR_Arm(LinearOpMode iBot) {
        // Take the passed in value of gamepad1 and telemetry and assign to class variables.
        this.bot = iBot;

        this.jointFirst = new AR_Joint(this.bot, "first_joint");
        this.jointSecond = new AR_Joint(this.bot, "second_joint");
    }

    public void updatePos( ) {
        if (tested) {
            this.jointFirst.moveJoint(this.targetFirst);
            this.jointSecond.moveJoint(this.targetSecond);
        }
    }

    public void setArmCustomPos(int firstJoint, int secondJoint ) {
        this.targetFirst = firstJoint; //degrees
        this.targetSecond = secondJoint; //degrees
    }

    public void setArmDeployPos( ) {
        // Todo: This needs to be carefully tested before we run the code to make sure the motor direction is correct, etc.
        this.targetFirst = 140; //degrees
        this.targetSecond = 160; //degrees
    }

    public void setArmRestPos( ) {
        // Todo: This needs to be carefully tested before we run the code to make sure the motor direction is correct, etc.
        this.targetFirst = 0; //degrees
        this.targetSecond = 0; //degrees

        // Todo: Somehow the power should be set to zero after movement because we don't want to waste battery power holding
        // the arm in the lowered position.
    }
}



