package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class AR_Joint
{
    private LinearOpMode bot;
    private String motorName;

    private DcMotor jointMotor;
    private AR_PIDController newPID;

    public AR_Joint(LinearOpMode iBot, String jointName) {
        this.bot = iBot;
        //this.motorName = jointName;

        this.jointMotor = bot.hardwareMap.dcMotor.get( jointName );

        this.newPID = new AR_PIDController(this.bot, jointMotor, jointName);
    }

    public void moveJoint(int target) {

        this.newPID.loop(target);

    }

}
