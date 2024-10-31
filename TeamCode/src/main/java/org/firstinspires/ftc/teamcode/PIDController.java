package org.firstinspires.ftc.teamcode;

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
        double error = setPoint - currentPosition;

        // Proportional term
        double proportional = kp * error;

        // Integral term
        integral += error * deltaTime;
        double integralTerm = ki * integral;

        // Derivative term
        double derivative = (error - lastError) / deltaTime;
        double derivativeTerm = kd * derivative;

        // Compute the output
        double output = proportional + integralTerm + derivativeTerm;

        // Store error for next calculation
        this.lastError = error;

        return output;  // This should be used to set the arm power.
    }
}