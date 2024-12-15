package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

enum Mode {
    OFF, RED, ORANGE, YELLOW, SAGE, GREEN, AZURE, BLUE, INDIGO, VIOLET, WHITE,
    CUSTOM,
    POLICE,
    RAINBOW
}

public class AR_Light
{
    // Handle for GoBilda Light
    Servo SRV_GOBILDA_LIGHT;

    // Handle for LinearOpMode
    LinearOpMode bot;

    // Preset Colors for GoBilda RGB Indicator Light
    public static final double GB_CLR_OFF = 0.0;
    public static final double GB_CLR_RED = 0.277;
    public static final double GB_CLR_ORANGE = 0.333;
    public static final double GB_CLR_YELLOW = 0.388;
    public static final double GB_CLR_SAGE = 0.444;
    public static final double GB_CLR_GREEN = 0.500;
    public static final double GB_CLR_AZURE = 0.555;
    public static final double GB_CLR_BLUE = 0.611;
    public static final double GB_CLR_INDIGO = 0.666;
    public static final double GB_CLR_VIOLET = 0.722;
    public static final double GB_CLR_WHITE = 1.0;

    // How quickly the light changes color, in milliseconds
    public static final double FLASH_RATE = 500;

    // The lights current mode
    public Mode currentMode = Mode.OFF;

    // Saves value of custom color
    public double customColor = 0;

    // Name used to identify the light in the Hardware Map
    String lightName;

    // Keeps track of time for flash color changes.
    long lastTime = 0;

    // Remembers current color of light while in flash mode.
    double flashState = 0;

    // Instantiation of the class
    public AR_Light(String iLightName, LinearOpMode iBot) {
        // Take the passed in value of telemetry and assign to class variables.
        bot = iBot;
        lightName = iLightName;

        SRV_GOBILDA_LIGHT = bot.hardwareMap.servo.get( lightName );

        // Turn light off for initialization
        stopLight();
    }

    public void setColor(double value) {
        customColor = value;
    }

    public void updateLight()
    {
        switch( currentMode )
        {
            case OFF:
                SRV_GOBILDA_LIGHT.setPosition(GB_CLR_OFF);
                break;
            case CUSTOM:
                SRV_GOBILDA_LIGHT.setPosition(customColor);
                break;
            case RED:
                SRV_GOBILDA_LIGHT.setPosition(GB_CLR_RED);
                break;
            case ORANGE:
                SRV_GOBILDA_LIGHT.setPosition(GB_CLR_ORANGE);
                break;
            case YELLOW:
                SRV_GOBILDA_LIGHT.setPosition(GB_CLR_YELLOW);
                break;
            case SAGE:
                SRV_GOBILDA_LIGHT.setPosition(GB_CLR_SAGE);
                break;
            case GREEN:
                SRV_GOBILDA_LIGHT.setPosition(GB_CLR_GREEN);
                break;
            case AZURE:
                SRV_GOBILDA_LIGHT.setPosition(GB_CLR_AZURE);
                break;
            case BLUE:
                SRV_GOBILDA_LIGHT.setPosition(GB_CLR_BLUE);
                break;
            case INDIGO:
                SRV_GOBILDA_LIGHT.setPosition(GB_CLR_INDIGO);
                break;
            case VIOLET:
                SRV_GOBILDA_LIGHT.setPosition(GB_CLR_VIOLET);
                break;
            case WHITE:
                SRV_GOBILDA_LIGHT.setPosition(GB_CLR_WHITE);
                break;
            case POLICE:
                if( lastTime <= (System.currentTimeMillis() - FLASH_RATE) )
                {
                   // Change State
                   if (flashState == GB_CLR_BLUE) {
                       SRV_GOBILDA_LIGHT.setPosition(GB_CLR_RED);
                       flashState = GB_CLR_RED;
                   } else {
                       SRV_GOBILDA_LIGHT.setPosition(GB_CLR_BLUE);
                       flashState = GB_CLR_BLUE;
                   }

                   lastTime = System.currentTimeMillis();  //  1500
                }
                break;

            case RAINBOW:
                if( lastTime <= (System.currentTimeMillis() - FLASH_RATE) )
                {
                    // Change State
                    if (flashState == GB_CLR_RED) {
                        SRV_GOBILDA_LIGHT.setPosition(GB_CLR_ORANGE);
                        flashState = GB_CLR_ORANGE;
                    } else if (flashState == GB_CLR_ORANGE){
                        SRV_GOBILDA_LIGHT.setPosition(GB_CLR_YELLOW);
                        flashState = GB_CLR_YELLOW;
                    } else if (flashState == GB_CLR_YELLOW){
                        SRV_GOBILDA_LIGHT.setPosition(GB_CLR_GREEN);
                        flashState = GB_CLR_GREEN;
                    } else if (flashState == GB_CLR_GREEN){
                        SRV_GOBILDA_LIGHT.setPosition(GB_CLR_BLUE);
                        flashState = GB_CLR_BLUE;
                    } else if (flashState == GB_CLR_BLUE){
                        SRV_GOBILDA_LIGHT.setPosition(GB_CLR_INDIGO);
                        flashState = GB_CLR_INDIGO;
                    } else if (flashState == GB_CLR_INDIGO) {
                        SRV_GOBILDA_LIGHT.setPosition(GB_CLR_VIOLET);
                        flashState = GB_CLR_VIOLET;
                    } else {
                        SRV_GOBILDA_LIGHT.setPosition(GB_CLR_RED);
                        flashState = GB_CLR_RED;
                    }

                    lastTime = System.currentTimeMillis();
                }
                break;
        }
    }

    public void stopLight()
    {
        this.currentMode = Mode.OFF;
    }
    public void redLight() {
        this.currentMode = Mode.RED;
    }
    public void orangeLight() {
        this.currentMode = Mode.ORANGE;
    }
    public void yellowLight() {
        this.currentMode = Mode.YELLOW;
    }
    public void sageLight() {
        this.currentMode = Mode.SAGE;
    }
    public void greenLight() {
        this.currentMode = Mode.GREEN;
    }
    public void azureLight() {
        this.currentMode = Mode.AZURE;
    }
    public void blueLight() {
        this.currentMode = Mode.BLUE;
    }
    public void indigoLight() {
        this.currentMode = Mode.INDIGO;
    }
    public void violetLight() {
        this.currentMode = Mode.VIOLET;
    }
    public void whiteLight() {
        this.currentMode = Mode.WHITE;
    }
    public void policeLights()
    {
        this.currentMode = Mode.POLICE;
    }

    public void rainbowLights()
    {
        this.currentMode = Mode.RAINBOW;
    }
}