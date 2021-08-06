package flocking.util;

import flocking.Styles;

/**
 * Flocking Class
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 3, 2021
 */
public class Angle {

    private float angle;
    private final float EQUALS_THRESHOLD = 0.1f;

    /**
     * Direction Constructor
     * Creates a new Direction using degrees
     */
    public Angle(float angle) {
        this.angle = angle % 360;
    }


    public Angle(float x, float y) {
        // TODO Add case for x = 0 and y = 0
        angle = (float) Math.atan(y / x);
    }


    /**
     * @param newAngle
     */
    public void setAngle(float newAngle) {
        angle = newAngle;
    }


    /**
     * Returns the angle in degrees
     * 
     * @return the direction
     */
    public float toDegrees() {
        return angle;
    }


    public float toRadians() {
        return (float) Math.toRadians(angle);
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
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Angle dir = (Angle) obj;

        return Math.abs(dir.toDegrees() - toDegrees()) <= EQUALS_THRESHOLD;
    }


    @Override
    public String toString() {
        return Styles.df.format(angle) + "°";
    }

}
