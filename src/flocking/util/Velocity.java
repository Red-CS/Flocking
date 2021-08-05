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


    /**
     * Tests whether or not two Velocity objects are equal
     * 
     * @param obj Object to compare with
     * @return {@code true} if they the same, {@code false} if not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Velocity objVelocity = (Velocity) obj;
        return objVelocity.direction.equals(direction)
            && objVelocity.magnitude == magnitude;
    }

}
