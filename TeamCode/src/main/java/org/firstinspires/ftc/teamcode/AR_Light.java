package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

enum Mode {
    OFF, RED, ORANGE, YELLOW, SAGE, GREEN, AZURE, BLUE, INDIGO, VIOLET, WHITE,
    CUSTOM,
    FLASH,
    RAINBOW,
    STROBE
}

/**
 * This class is used to control the GoBilda PWM Light.
 */
public class AR_Light
{
    // Handle for GoBilda Light
    private Servo SRV_GOBILDA_LIGHT;

    // Handle for LinearOpMode
    private LinearOpMode bot;

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
    private Mode currentMode = Mode.OFF;

    // Saves value of custom color
    private double customColor = 0;

    // Name used to identify the light in the Hardware Map
    private String lightName;

    // Keeps track of time for flash color changes.
    private long lastTime = 0;

    // Remembers current color of light while in flash mode.
    private double flashState = 0;

    // Variables used to control the strobe light.
    private double color1;
    private double color2;
    private int strobeDuration;
    private int strobeFlashDuration;

    // Instantiation of the class

    /**
     * Constructor. Perform the setup of the Light. The light is accessed like it is a servo, using
     * the setPosition and getPosition methods for control.
     *
     * @param iLightName String used to identify light in Hardware Map.
     * @param iBot Object to OpMode so you can access HardwareMap, etc.
     */
    public AR_Light(String iLightName, LinearOpMode iBot) {
        // Take the passed in value of telemetry and assign to class variables.
        bot = iBot;
        lightName = iLightName;

        SRV_GOBILDA_LIGHT = bot.hardwareMap.servo.get( lightName );

        // Turn light off for initialization
        stopLight();
    }

    /**
     * This method should be called within the OpModes main loop, so as to update the light frequently.
     * When called, the function will perform the light action that is requested within the "currentMode"
     * variable.
     */
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
            case FLASH:
                if( lastTime <= (System.currentTimeMillis() - FLASH_RATE) )
                {
                   // Change State
                   if (flashState == color1) {
                       SRV_GOBILDA_LIGHT.setPosition(color2);
                       flashState = color2;
                   } else {
                       SRV_GOBILDA_LIGHT.setPosition(color1);
                       flashState = color1;
                   }

                   lastTime = System.currentTimeMillis();
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

            case STROBE:
                if( lastTime <= ( System.currentTimeMillis( ) - strobeDuration ) )
                {
                    lastTime = System.currentTimeMillis( );
                    SRV_GOBILDA_LIGHT.setPosition( color1 );
                    if ( lastTime <= ( System.currentTimeMillis( ) - strobeFlashDuration ) )
                    {
                        SRV_GOBILDA_LIGHT.setPosition( color2 );
                    }
                }
                break;
        }
    }

    /**
     * Return immediately and turns the light OFF for use in update phase.
     */
    public void stopLight() {
        this.currentMode = Mode.OFF;
    }

    /**
     * Return immediately and sets the light's color to CUSTOM for use in update phase. The color
     * passed into the value variable will be the color used.
     *
     * @param iColor The value of the custom color. A double between 0 and 1.
     */
    public void customLight(double iColor) {
        customColor = iColor;
        this.currentMode = Mode.CUSTOM;
    }
    /**
     * Return immediately and sets the light's color to RED for use in update phase.
     */
    public void redLight() {
        this.currentMode = Mode.RED;
    }
    /**
     * Return immediately and sets the light's color to ORANGE for use in update phase.
     */
    public void orangeLight() {
        this.currentMode = Mode.ORANGE;
    }
    /**
     * Return immediately and sets the light's color to YELLOW for use in update phase.
     */
    public void yellowLight() {
        this.currentMode = Mode.YELLOW;
    }
    /**
     * Return immediately and sets the light's color to SAGE for use in update phase.
     */
    public void sageLight() {
        this.currentMode = Mode.SAGE;
    }
    /**
     * Return immediately and sets the light's color to GREEN for use in update phase.
     */
    public void greenLight() {
        this.currentMode = Mode.GREEN;
    }
    /**
     * Return immediately and sets the light's color to AZURE for use in update phase.
     */
    public void azureLight() {
        this.currentMode = Mode.AZURE;
    }
    /**
     * Return immediately and sets the light's color to BLUE for use in update phase.
     */
    public void blueLight() {
        this.currentMode = Mode.BLUE;
    }
    /**
     * Return immediately and sets the light's color to INDIGO for use in update phase.
     */
    public void indigoLight() {
        this.currentMode = Mode.INDIGO;
    }
    /**
     * Return immediately and sets the light's color to VIOLET for use in update phase.
     */
    public void violetLight() {
        this.currentMode = Mode.VIOLET;
    }

    /**
     * Return immediately and sets the light's color to WHITE for use in update phase.
     */
    public void whiteLight() {
        this.currentMode = Mode.WHITE;
    }
    /**
     * Return immediately after setting the light to perform a POLICE flash during updates. Colors will alternate between RED and BLUE.
     *
     * @param iColor1 flashing color 1
     * @param iColor2 flashing color 2
     */
    public void flashLights(double iColor1, double iColor2)
    {
        this.currentMode = Mode.FLASH;
        color1 = iColor2;
        color2 = iColor2;
    }   
   /**
     * Return immediately after setting the light to perform a POLICE flash during updates. Colors will alternate between RED and BLUE.
     */
    public void policeLights()
    {
        this.currentMode = Mode.FLASH;
        color1 = GB_CLR_RED;
        color2 = GB_CLR_BLUE;
    }

    /**
     * Return immediately after setting the light to perform a RAINBOW flash during updates. Colors will sequence through RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, and VIOLET. At the end it will return to RED.
     */
    public void rainbowLights()
    {
        this.currentMode = Mode.RAINBOW;
    }

    /**
     * Returns immediately after setting the variables for the Strobe Effect.
     * For, example, giving it the parameters of RED, OFF, 250, 1000 would make the light flash RED for a quarter of a second, then be OFF for the remainder of the 1000 ms (750ms).
     *
     * @param iPrimaryColor This color is the "flash" of the strobe. Takes a double between 0 and 1 or one of the predefined colors like GB_CLR_RED, etc.
     * @param iSecondaryColor This color id used when the "flash" isn't happening. This can be any color, including "off".  Takes a double between 0 and 1 or one of the predefined colors like GB_CLR_RED, etc.
     * @param iDuration The frequency of the "flashes". Milliseconds
     * @param iFlashDuration How long the "flash" lasts. Milliseconds
     */
    public void strobeLights(double iPrimaryColor, double iSecondaryColor, int iDuration, int iFlashDuration)
    {
        this.currentMode = Mode.STROBE;

        color1 = iPrimaryColor;
        color2 = iSecondaryColor;
        strobeDuration = iDuration;
        strobeFlashDuration = iFlashDuration;
    }
}