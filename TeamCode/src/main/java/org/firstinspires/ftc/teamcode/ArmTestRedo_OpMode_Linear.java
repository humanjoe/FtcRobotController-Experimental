package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/*--------------------------------------------------------------------------------------------------
 * This is an example OpMode created to show how the AR_Arm Class works, designed for the 2024-2025
 * Aerospace Robotics Robot Arm.
 *
 * Creation Date: 11/3/2024
 ---------------------------------------------------------------------------------------------------
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
                // Set Arm into Deploy position.
                arm.setArmDeployPos();
            }
            if (gamepad1.x) {
                telemetry.addData("Status","GP1:X (setArmRestPos)");
                // Set Arm into Rest position.
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
