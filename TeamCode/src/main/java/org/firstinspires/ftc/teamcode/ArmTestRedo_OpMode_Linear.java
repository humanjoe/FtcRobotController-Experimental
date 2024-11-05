package org.firstinspires.ftc.teamcode;
/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

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

@TeleOp(name="ArmTestRedo: Linear OpMode", group="Linear OpMode")
public class ArmTestRedo_OpMode_Linear extends LinearOpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException
    {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        AR_Arm arm;

        // Wait for the game to start (driver presses START)
        waitForStart();
        runtime.reset();

        // Instantiate Arm class
        arm = new AR_Arm(this);

        // Run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // ===== CHECK FOR INPUTS FROM GAMEPADS, ETC. ==========================================
            // We should perform all our user input checks here. Every loop, we should determine if the
            // user has input anything.
            if (gamepad1.b) {
                telemetry.addData("Status","GP1:B (setArmDeployPos)");
                // Set Arm into UP position.
                arm.setArmDeployPos();
            }
            if (gamepad1.x) {
                telemetry.addData("Status","GP1:X (setArmRestPos)");
                // Set Arm into DOWN position.
                arm.setArmRestPos( );
            }
            if (gamepad1.y) {
                telemetry.addData("Status","GP1:Y (setArmGrabPos)");
                // Set Arm into GRAB position.
                arm.setArmGrabPos( );
            }

            // ===== RUN ROBOT MECHANICAL UPDATES ==================================================
            // This section is for code that needs to run every loop, even if there is not any user
            // input. For example, since some of our Arm movement controls are "Push a button and
            // forget", it is important that the Arm can update (PID Controller, etc.) even when
            // someone is not pressing a control.
            arm.updatePos();

            // ===== TELEMETRY =====================================================================
            // Show the elapsed game time and other data needed.
            telemetry.addData("Status","Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}
