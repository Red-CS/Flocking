package flocking.util;

import flocking.Styles;

/**
 * Angle Class
 * <br>
 * Provides a way to store and convert angle measurements.
 * Default measurement is in degrees.
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 3, 2021
 */
public class Angle {

    private float angle;

    /** Maximum difference required for two Angles to equal each other */
    private final float EQUALS_THRESHOLD = 0.1f;

    /**
     * Angle Constructor
     * <br>
     * Creates a new Angle using degrees. Supports angle measurements less than
     * zero.
     * 
     * @param angle Angle (in degrees) to set
     */
    public Angle(float angle) {
        this.angle = angle % 360;
        fixNegativeAngle();

    }


    /**
     * Angle Constructor
     * <br>
     * Creates an Angle based on x and y components
     * 
     * @param x X component of the angle
     * @param y Y component of the angle
     */
    public Angle(float x, float y) {
        angle = (float) Math.toDegrees(Math.atan2(y, x));
        fixNegativeAngle();
    }


    /**
     * Sets the angle (in degrees) of the current {@code Angle} object
     * 
     * @param angle Angle (in degrees) to set
     */
    public void setAngle(float angle) {
        this.angle = angle;
    }


    /**
     * Returns the angle in degrees
     * 
     * @return the angle in degrees
     */
    public float toDegrees() {
        return angle;
    }


    /**
     * Returns the angle in radians
     * 
     * @return the angle in radians
     */
    public float toRadians() {
        return (float) Math.toRadians(angle);
    }


    /**
     * Handles a negative angle being passed to the constructor.
     * <br>
     * {@link #Angle(float)} <br>
     * {@link #Angle(float, float)}
     */
    private void fixNegativeAngle() {
        // Handle negative angle
        if (angle < 0) {
            angle += 360;
        }
    }


    /**
     * Returns whether or not two Direction objects are equal.
     * Two Angle objects are equal if and only if their angles are the
     * same.
     * 
     * @param obj Object to compare with
     * @return {@code true} if they are equal, {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Angle dir = (Angle) obj;

        return Math.abs(dir.toDegrees() - toDegrees()) <= EQUALS_THRESHOLD;
    }


    /**
     * Returns a String representation of an {@code Angle} object
     * 
     * @return a String representation of an {@code Angle} object
     */
    @Override
    public String toString() {
        return Styles.df.format(angle) + "°";
    }

}
