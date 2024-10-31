package org.firstinspires.ftc.teamcode;

import static java.lang.Math.round;

public class PIDController {

    private double kp; // Proportional gain
    private double ki; // Integral gain
    private double kd; // Derivative gain

    private double setPoint; // Desired target value
    private double integral; // Integral sum
    private double lastError; // Previous error

    /**
     * construct PID controller
     * @param kp Proportional coefficient
     * @param ki Integral coefficient
     * @param kd Derivative coefficient
     */
    public PIDController(double kp, double ki, double kd) {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
        this.integral = 0;  // How is this configurable?
        this.lastError = 0;

        // reference = someValue; Was in other example. Not sure what to do with it.
    }

    public void setSetPoint(double setPoint) {
        this.setPoint = setPoint;
    }

    /**
     * update the PID controller output
     * @param currentPosition
     * @param deltaTime
     * @return the command to our motor, I.E. motor position
     */
    public double calculate(double currentPosition, double deltaTime)
    {
        // Calculate error
        double error = setPoint - currentPosition; //90 - 59.95

        // Proportional term
        double proportional = kp * error; //50 * 1 = 50     60 * 1 = 60

        // Integral term
        integral += error * deltaTime; // 50 * 2 = 100    60 * 2 = 120
        double integralTerm = ki * integral; //.1 * 100 = 10     .1 * 120 = 12

        // Derivative term
        double derivative = (error - lastError) / deltaTime; // ((50 - 40) / 2 ) = 5     60 - 50 / 2 = 5
        double derivativeTerm = kd * derivative; //.5 * 0.01 = 0.05

        // Compute the output
        double output = proportional + integralTerm + derivativeTerm; //50 + 10 + -0.05 = 59.95  // 60 +12 + .05 71.95

        // Store error for next calculation
        this.lastError = error; //50

        return output;  // This should be used to set the arm power.
    }
}