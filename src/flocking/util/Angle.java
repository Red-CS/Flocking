package flocking.util;

import flocking.Styles;

/**
 * Flocking Class
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Aug 3, 2021
 */
public class Angle {

    private double angle;

    /**
     * Direction Constructor
     * Creates a new Direction using degrees
     */
    public Angle(double angle) {
        this.angle = angle % 360;
    }


    public Angle(double x, double y) {
        // TODO Add case for x = 0 and y = 0
        angle = Math.atan(y / x);
    }


    /**
     * Returns the angle in degrees
     * 
     * @return the direction
     */
    public double toDegrees() {
        return angle;
    }


    /**
     * @param newAngle
     */
    public void setAngle(double newAngle) {
        angle = newAngle;
    }


    public double toRadians() {
        return Math.toRadians(angle);
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

        return dir.toDegrees() == toDegrees();
    }


    @Override
    public String toString() {
        return Styles.df.format(angle) + "°";
    }

}
