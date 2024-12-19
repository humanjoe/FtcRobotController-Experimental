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
@TeleOp(name="ArmTestRedo with Lights: Linear OpMode", group="Linear OpMode")
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
        AR_Light light;

        // Instantiate Arm & Light class
        arm = new AR_Arm(this);
        light = new AR_Light("status_light", this );

        // Wait for the game to start (driver presses START)
        waitForStart();
        if (isStopRequested()) return;
        runtime.reset();

        // Run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // ===== CHECK FOR INPUTS FROM GAMEPADS, ETC. ==========================================
            // We should perform all our user input checks here. Every loop, we should determine if the
            // user has input anything.
            if (gamepad1.a) {
                telemetry.addData("Status","GP1:A (Light: Police)");
                light.policeLights();
            }
            if (gamepad1.b) {
                telemetry.addData("Status","GP1:B (setArmDeployPos)");
                // Set Arm into Deploy position.
                arm.setArmDeployPos();
                light.customLight(AR_Light.GB_CLR_ORANGE);
            }
            if (gamepad1.x) {
                telemetry.addData("Status","GP1:X (setArmRestPos)");
                // Set Arm into Rest position.
                arm.setArmRestPos( );
                light.customLight(AR_Light.GB_CLR_SAGE);
            }
            if (gamepad1.y) {
                telemetry.addData("Status","GP1:Y (setArmGrabPos)");
                // Set Arm into GRAB position.
                arm.setArmGrabPos( );
                light.customLight(AR_Light.GB_CLR_AZURE);
            }
            if (gamepad1.dpad_down) {
                telemetry.addData("Status","GP1:DPD (Light: Strobe)");
                light.strobeLights(AR_Light.GB_CLR_GREEN, AR_Light.GB_CLR_OFF, 1000, 250);
            }

            // ===== RUN ROBOT MECHANICAL UPDATES ==================================================
            // This section is for code that needs to run every loop, even if there is not any user
            // input. For example, since some of our Arm movement controls are "Push a button and
            // forget", it is important that the Arm can update (PID Controller, etc.) even when
            // someone is not pressing a control.
            arm.updatePos();
            light.updateLight();

            // ===== TELEMETRY =====================================================================
            // Show the elapsed game time and other data needed.
            telemetry.addData("Status","Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}
