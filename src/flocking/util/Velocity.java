package flocking.util;

/**
 * Velocity Class
 * Provides an Angular-based Velocity implementation
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 4, 2021
 */
public class Velocity {

    private Angle direction;
    private float magnitude;

    /**
     * Velocity Constructor
     */
    public Velocity(Angle direction, float magnitude) {
        this.direction = direction;
        this.magnitude = magnitude;
    }


    /**
     * @return the direction
     */
    public Angle getDirection() {
        return direction;
    }


    /**
     * @param direction the direction to set
     */
    public void setDirection(Angle direction) {
        this.direction = direction;
    }


    /**
     * @return the magnitude
     */
    public float getMagnitude() {
        return magnitude;
    }


    /**
     * @param magnitude the magnitude to set
     */
    public void setMagnitude(float magnitude) {
        this.magnitude = magnitude;
    }

}
